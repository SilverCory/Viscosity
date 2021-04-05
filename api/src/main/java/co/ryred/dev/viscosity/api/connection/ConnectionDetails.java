package co.ryred.dev.viscosity.api.connection;

import io.netty.util.AttributeKey;
import lombok.*;

import java.net.SocketAddress;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Setter(value = AccessLevel.PACKAGE)
@RequiredArgsConstructor
public class ConnectionDetails {
    public static final AttributeKey<ConnectionDetails> ATTRIBUTE_KEY = AttributeKey.valueOf("ConnectionDetails");
    public static final AttributeKey<String> ATTRIBUTE_KEY_SERVER_NAME = AttributeKey.valueOf("ServerName");

    private final UUID connectionID;
    private final SocketAddress remoteAddress;
    private final long connectionTime;
    private final String serverName;

    private long lastPingTime;
    private long lastPongTime;
    private long latency;

    public String toString() {
        return new StringBuilder("ConnectionDetails (").append(serverName).append(")\n")
                .append("\tconnectionID: ").append(connectionID).append("\n")
                .append("\tremoteAddress: ").append(remoteAddress).append("\n")
                .append("\tconnectionTime: ").append(connectionTime).append("\n")
                .append("\tlastPingTime: ").append(lastPingTime).append("\n")
                .append("\tlastPongTime: ").append(lastPongTime).append("\n")
                .append("\tlatency: ").append(latency).append("ms")
                .toString();
    }
}
