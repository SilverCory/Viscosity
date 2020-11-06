package co.ryred.dev.viscosity.api.netty;

import co.ryred.dev.viscosity.api.Viscosity;
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

        if (msg instanceof HttpRequest) {

            HttpRequest httpRequest = (HttpRequest) msg;
            HttpHeaders headers = httpRequest.headers();

            if (!viscosity.getAuthToken().equals(headers.get(HttpHeaderNames.AUTHORIZATION))) {
                // TODO log auth attempt.
                ctx.close(); // I think this is the best way to close?
            }

            if ("Upgrade".equalsIgnoreCase(headers.get(HttpHeaderNames.CONNECTION)) &&
                    "WebSocket".equalsIgnoreCase(headers.get(HttpHeaderNames.UPGRADE))) {
                ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler(viscosity));
                handleHandshake(ctx, httpRequest);
            }
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
