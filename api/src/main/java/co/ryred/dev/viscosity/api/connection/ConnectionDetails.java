package co.ryred.dev.viscosity.api.connection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.SocketAddress;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class ConnectionDetails {
    private final UUID connectionID;
    private final SocketAddress remoteAddress;
    private final long connectionTime;
}
