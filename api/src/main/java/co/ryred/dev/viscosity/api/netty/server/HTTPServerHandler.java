package co.ryred.dev.viscosity.api.netty.server;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.api.connection.ConnectionDetails;
import co.ryred.dev.viscosity.api.netty.WebSocketHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;

public class HTTPServerHandler extends ChannelInboundHandlerAdapter {

    private final Viscosity viscosity;
    WebSocketServerHandshaker handshaker;

    public HTTPServerHandler(Viscosity viscosity) {
        this.viscosity = viscosity;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (!(msg instanceof HttpRequest)) {
            ctx.close();
            return;
        }

        HttpRequest httpRequest = (HttpRequest) msg;
        HttpHeaders headers = httpRequest.headers();

        String serverName = headers.get("Server-Name");
        if (serverName == null || serverName.trim().isEmpty()) {
            // TODO log bad servername.
            viscosity.getLogger().warning("Attempted connection had bad server name: " + serverName);
            ctx.close();
            return;
        }
        ctx.channel().attr(ConnectionDetails.ATTRIBUTE_KEY_SERVER_NAME).set(serverName.trim());

        String token = headers.get(HttpHeaderNames.AUTHORIZATION);
        if (!token.equals(viscosity.getAuthToken().get(serverName))) {
            viscosity.getLogger().warning("Attempted connection had bad auth token: " + token);
            ctx.close();
            return;
        }

        if ("Upgrade".equalsIgnoreCase(headers.get(HttpHeaderNames.CONNECTION)) &&
                "WebSocket".equalsIgnoreCase(headers.get(HttpHeaderNames.UPGRADE))) {
            ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler(viscosity));
            handleHandshake(ctx, httpRequest);
        }
    }

    /* Do the handshaking for WebSocket request */
    protected void handleHandshake(ChannelHandlerContext ctx, HttpRequest req) {
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                getWebSocketURL(req),
                null,
                true
        );

        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    protected String getWebSocketURL(HttpRequest req) {
        return "ws://" + req.headers().get("Host") + req.uri();
    }
}
