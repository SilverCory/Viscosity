package co.ryred.dev.viscosity.api.frame;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FramePriority {
    public static final byte LOWEST = -64;
    public static final byte LOW = -32;
    public static final byte NORMAL = 0;
    public static final byte HIGH = 32;
    public static final byte HIGHEST = 64;
}