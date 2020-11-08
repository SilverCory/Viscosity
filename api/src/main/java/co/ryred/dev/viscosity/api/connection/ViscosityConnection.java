package co.ryred.dev.viscosity.api.connection;

import co.ryred.dev.viscosity.api.frame.Frame;
import co.ryred.dev.viscosity.api.gson.GSONUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Closeable;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class ViscosityConnection implements Closeable {
    private final ConnectionDetails connectionDetails;
    private final Channel channel;

    public void send(String identifier, Object obj) {
        String json = GSONUtils.getGSON().toJson(new Frame(identifier, GSONUtils.getGSON().toJsonTree(obj)));
        channel.writeAndFlush(new TextWebSocketFrame(json));
    }

    void sendFrame(WebSocketFrame frame) {
        channel.writeAndFlush(frame);
    }

    public void ping() {
        long pingTime = System.currentTimeMillis();
        sendFrame(new PingWebSocketFrame(
                Unpooled.wrappedBuffer(String.valueOf(pingTime).getBytes())
        ));
        connectionDetails.setLastPingTime(pingTime);
    }

    @Override
    public void close() {
        if (!channel.isWritable()) return;
        channel.writeAndFlush(new CloseWebSocketFrame(
                WebSocketCloseStatus.NORMAL_CLOSURE,
                "Connection closed."
        ));
        channel.close();
    }
}


