package co.ryred.dev.viscosity.bungee.config;

import co.ryred.dev.viscosity.bungee.ViscosityPlugin;
import net.md_5.bungee.config.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.UUID;

public class ViscosityConfiguration extends ConfigHelper {

    private HashMap<SocketAddress, String> authKeys = new HashMap<>();

    public ViscosityConfiguration(ViscosityPlugin plugin) throws IOException {
        super(plugin, "config.yml");
    }

    @Override
    public boolean loadConfig() throws IOException {
        boolean ret = super.loadConfig();

        authKeys = getConfig().get("auth-keys", authKeys);

        return ret;
    }

    @Override
    protected Configuration getDefaultConfig() {
        Configuration ret = new Configuration();

        // AuthKeys default.
        HashMap<SocketAddress, String> authKeys = new HashMap<>();
        authKeys.put(new InetSocketAddress("127.0.0.1", 25565), UUID.randomUUID().toString());
        ret.set("auth-keys", authKeys);

        return ret;
    }
}
