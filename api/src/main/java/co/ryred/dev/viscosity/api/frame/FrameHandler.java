package co.ryred.dev.viscosity.api.frame;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameHandler {
    /**
     * Define the priority of the frame handler.
     * <p>
     * Frame handlers are called in order of priority:
     * <ol>
     * <li>LOWEST</li>
     * <li>LOW</li>
     * <li>NORMAL</li>
     * <li>HIGH</li>
     * <li>HIGHEST</li>
     * </ol>
     *
     * @return handler priority
     */
    byte priority() default FramePriority.NORMAL;

    String frameIdentifier();
}
