package co.ryred.dev.viscosity.api.netty.client;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.api.connection.ViscosityConnection;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class testMain {

    public static void main(String[] args) throws Throwable {
        EventLoopGroup group = new NioEventLoopGroup();
        Viscosity v = new MockViscosity(Logger.getAnonymousLogger());
        new WebSocketClient(v, "MOCKVISCOSITY", new InetSocketAddress("127.0.0.1", 25565), group);

//        AtomicInteger a = new AtomicInteger(1);
//        new Thread(() -> {
//            while (true) {
//                ViscosityConnection conn = v.getConnectionManager().getConnection("MOCKVISCOSITY");
//                if (conn == null) {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("Connecting... (50ms) #" + a.getAndIncrement());
//                    continue;
//                }
//                System.out.println("Connected!");
//                return;
//            }
//        }).start();

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            ViscosityConnection conn = v.getConnectionManager().getConnection("MOCKVISCOSITY");
            String msg = console.readLine();
            if (msg == null || msg.trim().equals("")) continue;

            String cmd = msg.toLowerCase().trim();
            switch (cmd) {
                case "exit":
                    System.out.println("Shutting down...");
                    v.getConnectionManager().close();
                    group.shutdownGracefully();
                    return;
                case "ping":
                    v.getConnectionManager().ping();
                    System.out.println("Pinged");
                    break;
                case "status":
                    v.getConnectionManager().getConnectionByDetails().keySet().forEach(System.out::println);
                    break;
                default: {
                    if (conn == null) {
                        System.out.println("Not connected :(");
                        break;
                    }
                    String[] parts = msg.split(" ", 2);
                    if (parts.length == 2) {
                        conn.send(parts[0], parts[1]);
                        break;
                    }

                    System.out.println("Invalid...");
                }
            }
        }
    }


    static class MockViscosity extends Viscosity {
        public MockViscosity(Logger logger) {
            super(logger, serverName -> "HELLO WORLD");
        }
    }
}
