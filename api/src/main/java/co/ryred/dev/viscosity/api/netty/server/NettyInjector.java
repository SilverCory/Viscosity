package co.ryred.dev.viscosity.api.netty.server;

import io.netty.channel.Channel;

public abstract class NettyInjector {
    protected boolean injected;
    protected boolean closed;

    /**
     * Inject into the connection class.
     */
    public abstract void inject();


    /**
     * Invoked when a channel is ready to be injected.
     *
     * @param channel - the channel to inject.
     */
    protected abstract void injectChannel(Channel channel);

}