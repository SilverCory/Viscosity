package co.ryred.dev.viscosity.api.netty;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.api.netty.utils.WebSocketCloseStatus;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.*;

/**
 * WebSocketHandler will take control of established websocket connections.
 * Replies to pings and will pass text frames to the framebus.
 */
public class WebSocketHandler extends ChannelInboundHandlerAdapter {

    private final Viscosity viscosity;

    public WebSocketHandler(Viscosity viscosity) {
        this.viscosity = viscosity;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        viscosity.getConnectionManager().registerConnection(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        viscosity.getConnectionManager().registerConnection(ctx.channel());
        // This shouldn't get called? O_o
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        viscosity.getConnectionManager().unregisterConnection(ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (!(msg instanceof WebSocketFrame)) {
            viscosity.getLogger().warning("Unsupported message received: " + msg.getClass());
            ctx.write(new CloseWebSocketFrame(
                    WebSocketCloseStatus.INTERNAL_SERVER_ERROR.code(),
                    "msg not instance of WebsSocketFrame"
            ));
            ctx.close();
            return;
        }

        WebSocketFrame frame = ((WebSocketFrame) msg);
        if (frame instanceof BinaryWebSocketFrame) {
            // Binary frame not supported.
            ctx.write(new CloseWebSocketFrame(
                    WebSocketCloseStatus.INVALID_MESSAGE_TYPE.code(),
                    "binary frame not supported."
            ));
            ctx.close();
            return;
        }

        // Text frame sent to framebus.
        if (frame instanceof TextWebSocketFrame) {
            this.viscosity.getFrameBus().processFrame(((TextWebSocketFrame) frame));
            return;
        }

        // Reply to ping.
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // Process pong.
        if (frame instanceof PongWebSocketFrame) {
            this.viscosity.getConnectionManager().pong(ctx.channel(), ((PongWebSocketFrame) frame));
            return;
        }

        // Handle close TODO
        if (frame instanceof CloseWebSocketFrame) {
            viscosity.getLogger().warning("CloseWebSocketFrame Received : ");
            viscosity.getLogger().warning("ReasonText :" + ((CloseWebSocketFrame) msg).reasonText());
            viscosity.getLogger().warning("StatusCode : " + ((CloseWebSocketFrame) msg).statusCode());
            ctx.close();
            return;
        }

        // Should probably die here.
        System.out.println("Unsupported WebSocketFrame");
    }
}