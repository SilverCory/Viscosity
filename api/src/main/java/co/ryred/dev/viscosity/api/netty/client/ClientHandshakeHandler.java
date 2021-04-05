package co.ryred.dev.viscosity.api.netty.client;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.api.connection.ConnectionDetails;
import co.ryred.dev.viscosity.api.netty.WebSocketHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.netty.util.CharsetUtil;

import java.util.logging.Level;

public class ClientHandshakeHandler extends SimpleChannelInboundHandler<Object> {

    private final Viscosity viscosity;
    private final WebSocketClientHandshaker handshaker;
    private final String serverName;
    private ChannelPromise channelFuture;

    public ClientHandshakeHandler(Viscosity viscosity, String serverName, WebSocketClientHandshaker handshaker) {
        this.viscosity = viscosity;
        this.serverName = serverName;
        this.handshaker = handshaker;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        this.channelFuture = ctx.newPromise();
        ctx.channel().attr(ConnectionDetails.ATTRIBUTE_KEY_SERVER_NAME).set(this.serverName);
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        handshaker.handshake(ctx.channel()).sync();
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        Channel ch = ctx.channel();
        if (!handshaker.isHandshakeComplete()) {
            try {
                handshaker.finishHandshake(ch, (FullHttpResponse) msg);
                ctx.pipeline().replace(this, "websocketHandler", new WebSocketHandler(viscosity));
            } catch (WebSocketHandshakeException e) {
                viscosity.getLogger().log(
                        Level.SEVERE,
                        "Error occurred handshaking websocket for \"" + serverName + "\"",
                        e
                );
                this.channelFuture.setFailure(e);
            }
            return;
        }

        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException(
                    "Unexpected FullHttpResponse (getStatus=" + response.getStatus() +
                            ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }
}
