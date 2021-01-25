package co.ryred.dev.viscosity.bukkit;

import co.ryred.dev.viscosity.api.Viscosity;
import co.ryred.dev.viscosity.bukkit.listener.pluginmessage.PluginMessageListener;
import co.ryred.dev.viscosity.bukkit.netty.BukkitNettyInjector;
import org.bukkit.plugin.java.JavaPlugin;

public class ViscosityPlugin extends JavaPlugin {

    private ViscosityBukkit viscosity;
    private BukkitNettyInjector injector;

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
        this.viscosity = new ViscosityBukkit(getLogger(), "HELLO WORLD");
        this.injector = new BukkitNettyInjector(viscosity);
        this.injector.inject();

        this.viscosity.getFrameBus().register(this, new PluginMessageListener(this));
    }

    @Override
    public void onDisable() {
        this.injector.close();
        this.viscosity.close();
        if (this.viscosity == Viscosity.getAPI()) {
            Viscosity.setAPI(null);
        }
    }

}
