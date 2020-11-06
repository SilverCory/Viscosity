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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Hi there!"));
        if (msg instanceof WebSocketFrame) {
            System.out.println("This is a WebSocket frame");
            System.out.println("Client Channel : " + ctx.channel());
            if (msg instanceof BinaryWebSocketFrame) {
                // Eh we can do this another day..
            } else if (msg instanceof TextWebSocketFrame) {
                this.viscosity.getFrameBus().processFrame(((TextWebSocketFrame) msg));
            } else if (msg instanceof PingWebSocketFrame) {
                // TODO ping?
            } else if (msg instanceof PongWebSocketFrame) {
                // TODO pong?
            } else if (msg instanceof CloseWebSocketFrame) {
                System.out.println("CloseWebSocketFrame Received : ");
                System.out.println("ReasonText :" + ((CloseWebSocketFrame) msg).reasonText());
                System.out.println("StatusCode : " + ((CloseWebSocketFrame) msg).statusCode());
            } else {
                System.out.println("Unsupported WebSocketFrame");
            }
        }
    }
}