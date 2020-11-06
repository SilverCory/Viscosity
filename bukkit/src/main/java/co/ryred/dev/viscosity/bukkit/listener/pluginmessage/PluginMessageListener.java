package co.ryred.dev.viscosity.bukkit.listener.pluginmessage;

import co.ryred.dev.viscosity.api.frame.FrameHandler;
import co.ryred.dev.viscosity.api.frame.FrameListener;
import co.ryred.dev.viscosity.bukkit.ViscosityPlugin;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PluginMessageListener implements FrameListener {
    private final ViscosityPlugin plugin;

    public PluginMessageListener(ViscosityPlugin plugin) {
        this.plugin = plugin;
    }

    @FrameHandler(frameIdentifier = "PluginMessage")
    public void handlePluginMessageFrame(PluginMessage message) {
        Player player;
        if (message.playerUUID != null) {
            player = plugin.getServer().getPlayer(message.playerUUID);
            if (player == null) {
                player = new FakePlayer(plugin.getServer().getOfflinePlayer(message.playerUUID));
            }
        } else {
            // Unfortunately plugin messages REQUIRE a player...
            // As long as this is just for system use and has nothing to do with the
            // player connection used to transport the message than this is fine.
            //
            // If a player is required, they should be online.
            player = new FakePlayer(
                    plugin.getServer().getOfflinePlayer(
                            UUID.nameUUIDFromBytes("069a79f4-44e9-4726-a5be-fca90e38aaf5".getBytes())
                    )
            );
        }

        this.plugin.getServer().getMessenger().dispatchIncomingMessage(
                player,
                message.channel,
                message.data
        );
    }
}
