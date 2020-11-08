package co.ryred.dev.viscosity.api.netty;

import co.ryred.dev.viscosity.api.Viscosity;
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
        super.handlerAdded(ctx);
        System.out.println("handler added.");
        viscosity.getConnectionManager().registerConnection(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        System.out.println("handler active.");
        viscosity.getConnectionManager().registerConnection(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("handler inactive.");
        viscosity.getConnectionManager().unregisterConnection(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("handler read.");
        if (!(msg instanceof WebSocketFrame)) {
            // TODO LOG??
            ctx.write(new CloseWebSocketFrame(
                    WebSocketCloseStatus.INTERNAL_SERVER_ERROR,
                    "msg not instance of WebsSocketFrame"
            ));
            ctx.close();
            return;
        }

        WebSocketFrame frame = ((WebSocketFrame) msg);
        if (frame instanceof BinaryWebSocketFrame) {
            // Binary frame not supported.
            ctx.write(new CloseWebSocketFrame(
                    WebSocketCloseStatus.INVALID_MESSAGE_TYPE,
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
            System.out.println("Ponged");
            this.viscosity.getConnectionManager().pong(ctx.channel(), ((PongWebSocketFrame) frame));
            return;
        }

        // Handle close TODO
        if (frame instanceof CloseWebSocketFrame) {
            System.out.println("CloseWebSocketFrame Received : ");
            System.out.println("ReasonText :" + ((CloseWebSocketFrame) msg).reasonText());
            System.out.println("StatusCode : " + ((CloseWebSocketFrame) msg).statusCode());
            ctx.close();
            return;
        }

        // Should probably die here.
        System.out.println("Unsupported WebSocketFrame");
    }
}