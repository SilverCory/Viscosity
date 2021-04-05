package co.ryred.dev.viscosity.bungee;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.bungee.config.ViscosityConfiguration;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.util.logging.Level;

public class ViscosityPlugin extends Plugin {

    private boolean failed = false;
    @Getter
    private ViscosityConfiguration config;
    private ViscosityBungee viscosity;

    @Override
    public void onLoad() {
        try {
            this.config = new ViscosityConfiguration(this);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Unable to load/save defaults of configuration!", e);
            failed = true;
        }

        this.viscosity = new ViscosityBungee(getLogger(), serverName -> getConfig().getAuthKeys().get(serverName));
    }

    @Override
    public void onEnable() {
        if (failed) {
            return;
        }
    }

    @Override
    public void onDisable() {
        this.viscosity.close();
        if (this.viscosity == Viscosity.getAPI()) {
            Viscosity.setAPI(null);
        }
    }
}
