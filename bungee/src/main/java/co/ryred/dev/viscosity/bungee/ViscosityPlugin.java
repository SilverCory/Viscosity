package co.ryred.dev.viscosity.bungee;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.bungee.config.ViscosityConfiguration;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.util.logging.Level;

public class ViscosityPlugin extends Plugin {

    private boolean failed = false;
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
    }

    @Override
    public void onEnable() {
        this.viscosity = new ViscosityBungee(getLogger(), "HELLO WORLD");
    }

    @Override
    public void onDisable() {
        this.viscosity.close();
        if (this.viscosity == Viscosity.getAPI()) {
            Viscosity.setAPI(null);
        }
    }

}
