package co.ryred.dev.viscosity.bungee.config;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class ConfigHelper {

    private final Plugin plugin;
    private final String fileName;
    @Getter
    private final File saveFile;
    @Getter
    private Configuration config;

    public ConfigHelper(Plugin plugin, String fileName) throws IOException {
        this.plugin = plugin;
        this.saveFile = new File(plugin.getDataFolder(), fileName);
        this.fileName = fileName;
        loadOrDefault();
    }

    private void loadOrDefault() throws IOException {
        if (loadConfig()) return;
        this.config = getDefaultConfig();
        saveConfig();
    }

    protected Configuration getDefaultConfig() {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(plugin.getResourceAsStream(fileName));
    }

    public void saveConfig() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, saveFile);
    }

    public boolean loadConfig() throws IOException {
        if (saveFile.exists() && saveFile.isFile()) {
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(saveFile);
            return true;
        }
        return false;
    }
}
