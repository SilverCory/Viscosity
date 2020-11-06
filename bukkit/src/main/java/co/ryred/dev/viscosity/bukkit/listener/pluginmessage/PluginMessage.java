package co.ryred.dev.viscosity.bukkit.listener.pluginmessage;

import lombok.Data;

import java.util.UUID;

@Data
public class PluginMessage {
    UUID playerUUID;
    String channel;
    byte[] data; // TODO base64? This could get mangled in JSON?
}
