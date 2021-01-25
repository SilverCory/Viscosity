package co.ryred.dev.viscosity.bukkit.netty;

import co.ryred.dev.viscosity.api.netty.server.HTTPDecoder;
import co.ryred.dev.viscosity.api.netty.server.NettyInjector;
import co.ryred.dev.viscosity.api.netty.utils.BootstrapList;
import co.ryred.dev.viscosity.bukkit.ViscosityBukkit;
import com.comphenix.protocol.reflect.FuzzyReflection;
import com.comphenix.protocol.reflect.VolatileField;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.google.common.collect.Lists;
import io.netty.channel.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BukkitNettyInjector extends NettyInjector {

    private final ViscosityBukkit viscosity;
    protected List<VolatileField> bootstrapFields = Lists.newArrayList();
    protected volatile Collection<Object> networkManagers;

    public BukkitNettyInjector(ViscosityBukkit viscosity) {
        this.viscosity = viscosity;
    }

    public synchronized void inject() {
        if (injected) throw new IllegalStateException("Cannot inject twice.");
        try {
            FuzzyReflection fuzzyServer = FuzzyReflection.fromClass(MinecraftReflection.getMinecraftServerClass());
            Method serverConnectionMethod = fuzzyServer.getMethodByParameters("getServerConnection", MinecraftReflection.getServerConnectionClass(), new Class[]{});

            // Get the server connection
            Object server = fuzzyServer.getSingleton();
            Object serverConnection = serverConnectionMethod.invoke(server);

            // Handle connected channels
            final ChannelInboundHandler endInitProtocol = new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    try {
                        // This can take a while, so we need to stop the main thread from interfering
                        synchronized (networkManagers) {
                            injectChannel(channel);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            // This is executed before Minecraft's channel handler
            final ChannelInboundHandler beginInitProtocol = new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    // Our only job is to add init protocol
                    channel.pipeline().addLast(endInitProtocol);
                }
            };

            // Add our handler to newly created channels
            final ChannelHandler connectionHandler = new ChannelInboundHandlerAdapter() {
                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    Channel channel = (Channel) msg;

                    // Prepare to initialize ths channel
                    channel.pipeline().addFirst(beginInitProtocol);
                    ctx.fireChannelRead(msg);
                }
            };

            // Get the current NetworkMananger list
            this.networkManagers = getNetworkManagers(serverConnection);

            // Insert ProtocolLib's connection interceptor
            this.bootstrapFields = getBootstrapFields(serverConnection);

            for (VolatileField field : bootstrapFields) {
                final List<Object> list = (List<Object>) field.getValue();

                // We don't have to override this list
                if (list == this.networkManagers) {
                    continue;
                }

                // Synchronize with each list before we attempt to replace them.
                field.setValue(new BootstrapList(list, connectionHandler));
            }

            injected = true;

        } catch (Exception e) {
            throw new RuntimeException("Unable to inject channel futures.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Object> getNetworkManagers(Object serverConnection) throws IllegalAccessException {
        Field networkManagersField = getFirstFieldWithListOfNetworkManagers(serverConnection);
        List<Object> networkManagers = new ArrayList<Object>();

        if (networkManagersField != null) {
            networkManagers = (List<Object>) networkManagersField.get(serverConnection);
        }
        return networkManagers;
    }


    private Field getFirstFieldWithListOfNetworkManagers(Object serverConnection) {
        Field networkManagersField = null;
        Class<?> networkManagerClass;
        try {
            networkManagerClass = Class.forName(MinecraftReflection.getNetworkManagerClass().getCanonicalName());
            for (Field declaredField : serverConnection.getClass().getDeclaredFields()) {
                boolean fieldIsAList = declaredField.getType() == List.class;
                if (fieldIsAList) {
                    Type typeOfFirstListElement = ((ParameterizedType) declaredField.getGenericType()).getActualTypeArguments()[0];
                    if (typeOfFirstListElement == networkManagerClass) {
                        networkManagersField = declaredField;
                        networkManagersField.setAccessible(true);
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return networkManagersField;

    }

    @Override
    protected void injectChannel(Channel channel) {
        channel.pipeline().addFirst(new HTTPDecoder(viscosity));
    }

    /**
     * Retrieve a list of every field with a list of channel futures.
     *
     * @param serverConnection - the connection.
     * @return List of fields.
     */
    protected List<VolatileField> getBootstrapFields(Object serverConnection) {
        List<VolatileField> result = Lists.newArrayList();

        // Find and (possibly) proxy every list
        for (Field field : FuzzyReflection.fromObject(serverConnection, true).getFieldListByType(List.class)) {
            VolatileField volatileField = new VolatileField(field, serverConnection, true).toSynchronized();

            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) volatileField.getValue();

            if (list.size() == 0 || list.get(0) instanceof ChannelFuture) {
                result.add(volatileField);
            }
        }
        return result;
    }

    /**
     * Clean up any remaning injections.
     */
    public synchronized void close() {
        if (closed) {
            return;
        }

        closed = true;

        for (VolatileField field : bootstrapFields) {
            Object value = field.getValue();

            // Undo the processed channels, if any
            if (value instanceof BootstrapList) {
                ((BootstrapList) value).close();
            }
            field.revertValue();
        }
    }
}
