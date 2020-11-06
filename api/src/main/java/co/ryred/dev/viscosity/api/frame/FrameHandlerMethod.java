package co.ryred.dev.viscosity.api.frame;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@AllArgsConstructor
public class FrameHandlerMethod {

    @Getter
    private final Object listener;
    @Getter
    private final Method method;

    public void invoke(Object contents) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        method.invoke(listener, contents);
    }
}