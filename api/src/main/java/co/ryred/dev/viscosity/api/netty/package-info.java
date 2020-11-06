/**
 * Various netty decoders to handle http/websockets using netty.
 * This means we can inject these handlers into the existing netty
 * instances and listen for HTTP requests before minecraft ones.
 * <p>
 * Various parts of this code are taken from <a href="https://github.com/MineWeb/ServerBridge">ServerBridge</a>.
 */
package co.ryred.dev.viscosity.api.netty;