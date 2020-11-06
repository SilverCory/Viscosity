package co.ryred.dev.viscosity.api.frame;

import co.ryred.dev.viscosity.api.gson.ByteArrayToBase64TypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameBus {

    public static Gson GSON = new GsonBuilder().
            enableComplexMapKeySerialization().
            registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).
            serializeNulls().
            create();

    private final Map<Object, FrameListener> listenerByOwner = new HashMap<>();
    private final Map<String, Map<Byte, Map<Object, Method[]>>> byListenerAndPriority = new HashMap<>();
    private final Map<String, FrameHandlerMethod[]> byFrameBaked = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();
    private final Logger logger;

    public FrameBus() {
        this(null);
    }

    public FrameBus(Logger logger) {
        this.logger = (logger == null) ? Logger.getLogger(Logger.GLOBAL_LOGGER_NAME) : logger;
    }

    public void processFrame(TextWebSocketFrame textWebSocketFrame) {
        Frame identifierFrame = GSON.fromJson(textWebSocketFrame.text(), Frame.class);
        post(identifierFrame);
    }

    protected void post(Frame frame) {
        FrameHandlerMethod[] handlers = byFrameBaked.get(frame.getIdentifier());

        if (handlers != null) {
            for (FrameHandlerMethod method : handlers) {
                try {
                    Class<?> frameType = method.getMethod().getParameterTypes()[0];
                    Object contents = GSON.fromJson(frame.getContents(), frameType);
                    method.invoke(contents);
                } catch (IllegalAccessException ex) {
                    throw new Error("Method became inaccessible: " + frame, ex);
                } catch (IllegalArgumentException ex) {
                    throw new Error("Method rejected target/argument: " + frame, ex);
                } catch (InvocationTargetException ex) {
                    logger.log(Level.WARNING, MessageFormat.format("Error dispatching Frame {0} to listener {1}", frame, method.getListener()), ex.getCause());
                }
            }
        }
    }

    private Map<String, Map<Byte, Set<Method>>> findHandlers(FrameListener listener) {
        Map<String, Map<Byte, Set<Method>>> handler = new HashMap<>();
        for (Method m : listener.getClass().getDeclaredMethods()) {
            FrameHandler annotation = m.getAnnotation(FrameHandler.class);
            if (annotation != null) {
                Class<?>[] params = m.getParameterTypes();
                if (params.length != 1) {
                    logger.log(Level.INFO, "Method {0} in class {1} annotated with {2} does not have single argument", new Object[]
                            {
                                    m, listener.getClass(), annotation
                            });
                    continue;
                }
                Map<Byte, Set<Method>> prioritiesMap = handler.get(annotation.frameIdentifier());
                if (prioritiesMap == null) {
                    prioritiesMap = new HashMap<>();
                    handler.put(annotation.frameIdentifier(), prioritiesMap);
                }
                Set<Method> priority = prioritiesMap.get(annotation.priority());
                if (priority == null) {
                    priority = new HashSet<>();
                    prioritiesMap.put(annotation.priority(), priority);
                }
                priority.add(m);
            }
        }
        return handler;
    }

    public void register(Object owner, FrameListener listener) throws IllegalArgumentException {
        if (owner == null) throw new IllegalArgumentException("register owner can not be null");

        Map<String, Map<Byte, Set<Method>>> handler = findHandlers(listener);
        lock.lock();
        try {
            for (Map.Entry<String, Map<Byte, Set<Method>>> e : handler.entrySet()) {
                Map<Byte, Map<Object, Method[]>> prioritiesMap = byListenerAndPriority.get(e.getKey());
                if (prioritiesMap == null) {
                    prioritiesMap = new HashMap<>();
                    byListenerAndPriority.put(e.getKey(), prioritiesMap);
                }
                for (Map.Entry<Byte, Set<Method>> entry : e.getValue().entrySet()) {
                    Map<Object, Method[]> currentPriorityMap = prioritiesMap.get(entry.getKey());
                    if (currentPriorityMap == null) {
                        currentPriorityMap = new HashMap<>();
                        prioritiesMap.put(entry.getKey(), currentPriorityMap);
                    }
                    Method[] baked = new Method[entry.getValue().size()];
                    currentPriorityMap.put(listener, entry.getValue().toArray(baked));
                }
                bakeHandlers(e.getKey());
            }
        } finally {
            lock.unlock();
        }

        listenerByOwner.put(owner, listener);
    }

    public void unregister(Object owner) {
        FrameListener listener = listenerByOwner.get(owner);
        if (listener == null) {
            return;
        }

        Map<String, Map<Byte, Set<Method>>> handler = findHandlers(listener);
        lock.lock();
        try {
            for (Map.Entry<String, Map<Byte, Set<Method>>> e : handler.entrySet()) {
                Map<Byte, Map<Object, Method[]>> prioritiesMap = byListenerAndPriority.get(e.getKey());
                if (prioritiesMap != null) {
                    for (Byte priority : e.getValue().keySet()) {
                        Map<Object, Method[]> currentPriority = prioritiesMap.get(priority);
                        if (currentPriority != null) {
                            currentPriority.remove(listener);
                            if (currentPriority.isEmpty()) {
                                prioritiesMap.remove(priority);
                            }
                        }
                    }
                    if (prioritiesMap.isEmpty()) {
                        byListenerAndPriority.remove(e.getKey());
                    }
                }
                bakeHandlers(e.getKey());
            }
        } finally {
            lock.unlock();
        }

        listenerByOwner.remove(owner);
    }

    /**
     * Shouldn't be called without first locking the writeLock; intended for use
     * only inside {@link #register(Object, FrameListener) register(Object, FrameListener)} or
     * {@link #unregister(Object) unregister(Object)}.
     *
     * @param frameIdentifier Frame identifier
     */
    private void bakeHandlers(String frameIdentifier) {
        Map<Byte, Map<Object, Method[]>> handlersByPriority = byListenerAndPriority.get(frameIdentifier);
        if (handlersByPriority != null) {
            List<FrameHandlerMethod> handlersList = new ArrayList<>(handlersByPriority.size() * 2);

            // Either I'm really tired, or the only way we can iterate between Byte.MIN_VALUE and Byte.MAX_VALUE inclusively,
            // with only a byte on the stack is by using a do {} while() format loop.
            byte value = Byte.MIN_VALUE;
            do {
                Map<Object, Method[]> handlersByListener = handlersByPriority.get(value);
                if (handlersByListener != null) {
                    for (Map.Entry<Object, Method[]> listenerHandlers : handlersByListener.entrySet()) {
                        for (Method method : listenerHandlers.getValue()) {
                            FrameHandlerMethod ehm = new FrameHandlerMethod(listenerHandlers.getKey(), method);
                            handlersList.add(ehm);
                        }
                    }
                }
            } while (value++ < Byte.MAX_VALUE);
            byFrameBaked.put(frameIdentifier, handlersList.toArray(new FrameHandlerMethod[handlersList.size()]));
        } else {
            byFrameBaked.remove(frameIdentifier);
        }
    }
}