package co.ryred.dev.viscosity.api.frame;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.net.SocketAddress;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class FrameDetails {
    private final UUID connectionID;
    private final SocketAddress remoteAddress;
    private final long connectionTime;
    @Setter
    private boolean cancelled = false;
}
