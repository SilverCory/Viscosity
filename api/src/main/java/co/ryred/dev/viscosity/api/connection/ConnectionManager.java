package co.ryred.dev.viscosity.api.connection;

import co.ryred.dev.viscosity.api.Viscosity;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import lombok.Getter;

import java.io.Closeable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class ConnectionManager implements Closeable {

    private final Viscosity viscosity;

    @Getter
    private final Map<ConnectionDetails, ViscosityConnection> connectionByDetails = new HashMap<>();

    public ConnectionManager(Viscosity viscosity) {
        this.viscosity = viscosity;
    }

    public void registerConnection(Channel ch) throws Exception {
        String serverName = ch.attr(ConnectionDetails.ATTRIBUTE_KEY_SERVER_NAME).get();
        if (serverName == null) throw new IllegalArgumentException("servername is null");
        ConnectionDetails details = new ConnectionDetails(
                UUID.randomUUID(),
                ch.remoteAddress(),
                System.currentTimeMillis(),
                serverName
        );
        ViscosityConnection connection = new ViscosityConnection(details, ch);
        ViscosityConnection oldCon = connectionByDetails.put(details, connection);
        if (oldCon != null && ch.equals(oldCon.getChannel())) oldCon.close(); // Shut down the old connection.

        ch.attr(ConnectionDetails.ATTRIBUTE_KEY).set(details);
    }

    public void unregisterConnection(Channel ch) {
        ConnectionDetails connectionDetails = ch.attr(ConnectionDetails.ATTRIBUTE_KEY).get();
        if (connectionDetails == null) {
            // BLERGH.
            return;
        }

        ViscosityConnection conn = connectionByDetails.remove(connectionDetails);
        if (conn != null) {
            conn.close();
        }
    }

    public ViscosityConnection getConnection(String serverName) {
        Map.Entry<ConnectionDetails, ViscosityConnection> entry = connectionByDetails.entrySet().stream()
                .filter(e -> e.getKey().getServerName().equals(serverName))
                .findAny()
                .orElse(null);

        if (entry == null) return null;
        return entry.getValue();
    }

    public void ping() {
        connectionByDetails.forEach((key, value) -> value.ping());
    }

    public void pong(Channel ch, PongWebSocketFrame frame) {
        long now = System.currentTimeMillis();
        ConnectionDetails connectionDetails = ch.attr(ConnectionDetails.ATTRIBUTE_KEY).get();
        if (connectionDetails == null) {
            // BLERGH.
            return;
        }

        long pingTime = Long.parseLong(frame.content().toString(Charset.defaultCharset()));
        if (pingTime != connectionDetails.getLastPingTime()) {
            viscosity.getLogger().warning("Received pong where last ping time is incorrect!");
            viscosity.getLogger().warning("Got: " + pingTime + ", expected: " + connectionDetails.getLastPingTime());
            return;
        }

        connectionDetails.setLatency(now - pingTime);
        connectionDetails.setLastPongTime(now);
    }

    @Override
    public void close() {
        for (ViscosityConnection con : connectionByDetails.values()) {
            try {
                con.close();
            } catch (Exception ex) {
                viscosity.getLogger().log(Level.WARNING, "Unable to close connection to " + con.getConnectionDetails().getServerName(), ex);
            }
        }
    }
}
