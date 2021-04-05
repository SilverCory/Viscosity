package co.ryred.dev.viscosity.bungee.config;

import co.ryred.dev.viscosity.bungee.ViscosityPlugin;
import lombok.Getter;
import net.md_5.bungee.config.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ViscosityConfiguration extends ConfigHelper {

    @Getter
    private HashMap<String, String> authKeys = new HashMap<>();

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
        HashMap<String, String> authKeys = new HashMap<>();
        authKeys.put("hub", UUID.randomUUID().toString());
        authKeys.put("creative", UUID.randomUUID().toString());
        authKeys.put("survival", UUID.randomUUID().toString());
        ret.set("auth-keys", authKeys);

        return ret;
    }
}
