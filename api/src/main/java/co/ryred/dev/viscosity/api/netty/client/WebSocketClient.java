package co.ryred.dev.viscosity.api.netty.client;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.api.netty.utils.HTTPHeaderNames;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.net.InetSocketAddress;
import java.net.URI;

public class WebSocketClient {

    public WebSocketClient(Viscosity viscosity, String serverName, InetSocketAddress addr, EventLoopGroup eventLoopGroup) throws Throwable {

        HttpHeaders headers = new DefaultHttpHeaders();
        headers.add(HTTPHeaderNames.AUTHORIZATION, viscosity.getAuthToken().get(serverName));
        headers.add("Server-Name", serverName);

        System.out.println(addr.getHostString() + ":" + addr.getPort());

        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(
                new URI("ws://" + addr.getHostString() + ":" + addr.getPort()),
                WebSocketVersion.V13,
                null,
                false,
                headers
        );

        ChannelFuture result = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(eventLoopGroup)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(
                                new HttpClientCodec(),
                                new HttpObjectAggregator(65536),
                                new ClientHandshakeHandler(viscosity, serverName, handshaker)
                        );
                    }
                })
                .connect(addr)
                .sync();

        if (!result.isSuccess()) throw result.cause();
    }

}
