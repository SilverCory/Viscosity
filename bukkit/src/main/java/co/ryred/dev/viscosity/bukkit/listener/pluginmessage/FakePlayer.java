package co.ryred.dev.viscosity.bukkit.listener.pluginmessage;

import org.bukkit.*;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.*;

/**
 * This is a hack, do not actually use this lol.
 */
@SuppressWarnings("ALL")
public class FakePlayer implements Player {

    private final OfflinePlayer offlinePlayer;

    public FakePlayer(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
    }

    @Override
    public String getDisplayName() {
        return offlinePlayer.getName();
    }

    @Override
    public void setDisplayName(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public String getPlayerListName() {
        return offlinePlayer.getName();
    }

    @Override
    public void setPlayerListName(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public String getPlayerListHeader() {
        return null;
    }

    @Override
    public void setPlayerListHeader(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public String getPlayerListFooter() {
        return null;
    }

    @Override
    public void setPlayerListFooter(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void setPlayerListHeaderFooter(String s, String s1) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public Location getCompassTarget() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void setCompassTarget(Location location) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public InetSocketAddress getAddress() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean isConversing() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void acceptConversationInput(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void sendRawMessage(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void sendRawMessage(UUID uuid, String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void kickPlayer(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void chat(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean performCommand(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public Location getLocation(Location location) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public Vector getVelocity() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void setVelocity(Vector vector) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public double getHeight() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public double getWidth() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public BoundingBox getBoundingBox() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean isOnGround() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public World getWorld() {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public void setRotation(float v, float v1) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean teleport(Location location) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean teleport(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");
    }

    @Override
    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getEntityId() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getFireTicks() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setFireTicks(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getMaxFireTicks() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isDead() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendMessage(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendMessage(String[] strings) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendMessage(UUID uuid, String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendMessage(UUID uuid, String[] strings) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Server getServer() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isPersistent() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setPersistent(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getPassenger() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean setPassenger(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public List<Entity> getPassengers() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean addPassenger(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean removePassenger(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean eject() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getFallDistance() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setFallDistance(float v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public UUID getUniqueId() {
        return offlinePlayer.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setTicksLived(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playEffect(EntityEffect entityEffect) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public EntityType getType() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isInsideVehicle() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean leaveVehicle() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getVehicle() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isCustomNameVisible() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setCustomNameVisible(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isGlowing() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setGlowing(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isInvulnerable() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setInvulnerable(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSilent() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSilent(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasGravity() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setGravity(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getPortalCooldown() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setPortalCooldown(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Set<String> getScoreboardTags() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean addScoreboardTag(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean removeScoreboardTag(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public BlockFace getFacing() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Pose getPose() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSneaking() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSneaking(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSprinting() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSprinting(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void saveData() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void loadData() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSleepingIgnored() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSleepingIgnored(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isOnline() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isBanned() {
        return offlinePlayer.isBanned();
    }

    @Override
    public boolean isWhitelisted() {
        return offlinePlayer.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean b) {
        offlinePlayer.setWhitelisted(b);
    }

    @Override
    public Player getPlayer() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public long getFirstPlayed() {
        return offlinePlayer.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return offlinePlayer.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return offlinePlayer.hasPlayedBefore();
    }

    @Override
    public Location getBedSpawnLocation() {
        return offlinePlayer.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic);
    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, i);

    }

    @Override
    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, i);
    }

    @Override
    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, i);
    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material);
    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, material, i);
    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, material, i);
    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        offlinePlayer.setStatistic(statistic, material, i);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        offlinePlayer.decrementStatistic(statistic, entityType);
    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return offlinePlayer.getStatistic(statistic, entityType);
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
        offlinePlayer.incrementStatistic(statistic, entityType, i);
    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
        offlinePlayer.decrementStatistic(statistic, entityType, i);
    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int i) {
        offlinePlayer.decrementStatistic(statistic, entityType, i);
    }

    @Override
    public void setBedSpawnLocation(Location location, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playNote(Location location, byte b, byte b1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playNote(Location location, Instrument instrument, Note note) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playSound(Location location, Sound sound, float v, float v1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playSound(Location location, String s, float v, float v1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playSound(Location location, Sound sound, SoundCategory soundCategory, float v, float v1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playSound(Location location, String s, SoundCategory soundCategory, float v, float v1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void stopSound(Sound sound) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void stopSound(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void stopSound(Sound sound, SoundCategory soundCategory) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void stopSound(String s, SoundCategory soundCategory) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendBlockChange(Location location, Material material, byte b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendBlockChange(Location location, BlockData blockData) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendSignChange(Location location, String[] strings, DyeColor dyeColor) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendMap(MapView mapView) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void updateInventory() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setPlayerTime(long l, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public long getPlayerTime() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public long getPlayerTimeOffset() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isPlayerTimeRelative() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void resetPlayerTime() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public WeatherType getPlayerWeather() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setPlayerWeather(WeatherType weatherType) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void resetPlayerWeather() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void giveExp(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void giveExpLevels(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getExp() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setExp(float v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getLevel() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setLevel(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getTotalExperience() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setTotalExperience(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendExperienceChange(float v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendExperienceChange(float v, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getExhaustion() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setExhaustion(float v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getSaturation() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSaturation(float v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getFoodLevel() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setFoodLevel(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean getAllowFlight() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setAllowFlight(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void hidePlayer(Player player) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void hidePlayer(Plugin plugin, Player player) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void showPlayer(Player player) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void showPlayer(Plugin plugin, Player player) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean canSee(Player player) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isFlying() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setFlying(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getFlySpeed() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setFlySpeed(float v) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getWalkSpeed() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setWalkSpeed(float v) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setTexturePack(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setResourcePack(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setResourcePack(String s, byte[] bytes) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Scoreboard getScoreboard() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isHealthScaled() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setHealthScaled(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getHealthScale() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setHealthScale(double v) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getSpectatorTarget() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSpectatorTarget(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendTitle(String s, String s1) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendTitle(String s, String s1, int i, int i1, int i2) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void resetTitle() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, Location location, int i, double v, double v1, double v2, double v3, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void spawnParticle(Particle particle, double v, double v1, double v2, int i, double v3, double v4, double v5, double v6, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public AdvancementProgress getAdvancementProgress(Advancement advancement) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getClientViewDistance() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public String getLocale() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void updateCommands() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void openBook(ItemStack itemStack) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Spigot spigot() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PlayerInventory getInventory() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Inventory getEnderChest() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public MainHand getMainHand() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean setWindowProperty(InventoryView.Property property, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView getOpenInventory() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView openInventory(Inventory inventory) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView openWorkbench(Location location, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView openEnchanting(Location location, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void openInventory(InventoryView inventoryView) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView openMerchant(Villager villager, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public InventoryView openMerchant(Merchant merchant, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void closeInventory() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public ItemStack getItemInHand() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setItemInHand(ItemStack itemStack) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public ItemStack getItemOnCursor() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setItemOnCursor(ItemStack itemStack) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasCooldown(Material material) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getCooldown(Material material) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setCooldown(Material material, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getSleepTicks() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean sleep(Location location, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void wakeup(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Location getBedLocation() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public GameMode getGameMode() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setGameMode(GameMode gameMode) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isBlocking() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isHandRaised() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getExpToLevel() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public float getAttackCooldown() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean discoverRecipe(NamespacedKey namespacedKey) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int discoverRecipes(Collection<NamespacedKey> collection) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean undiscoverRecipe(NamespacedKey namespacedKey) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int undiscoverRecipes(Collection<NamespacedKey> collection) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasDiscoveredRecipe(NamespacedKey namespacedKey) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Set<NamespacedKey> getDiscoveredRecipes() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getShoulderEntityLeft() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setShoulderEntityLeft(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getShoulderEntityRight() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setShoulderEntityRight(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean dropItem(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getEyeHeight() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getEyeHeight(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Location getEyeLocation() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public List<Block> getLineOfSight(Set<Material> set, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Block getTargetBlock(Set<Material> set, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Block getTargetBlockExact(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Block getTargetBlockExact(int i, FluidCollisionMode fluidCollisionMode) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public RayTraceResult rayTraceBlocks(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public RayTraceResult rayTraceBlocks(double v, FluidCollisionMode fluidCollisionMode) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getRemainingAir() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setRemainingAir(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getMaximumAir() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setMaximumAir(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getArrowCooldown() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setArrowCooldown(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getArrowsInBody() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setArrowsInBody(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getMaximumNoDamageTicks() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getLastDamage() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setLastDamage(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public int getNoDamageTicks() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setNoDamageTicks(int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Player getKiller() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> collection) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType potionEffectType) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void removePotionEffect(PotionEffectType potionEffectType) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasLineOfSight(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean getRemoveWhenFarAway() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setRemoveWhenFarAway(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public EntityEquipment getEquipment() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean getCanPickupItems() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setCanPickupItems(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isLeashed() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean setLeashHolder(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isGliding() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setGliding(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSwimming() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setSwimming(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isRiptiding() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isSleeping() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setAI(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasAI() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void attack(Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void swingMainHand() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void swingOffHand() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isCollidable() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setCollidable(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Set<UUID> getCollidableExemptions() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> T getMemory(MemoryKey<T> memoryKey) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T> void setMemory(MemoryKey<T> memoryKey, T t) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public EntityCategory getCategory() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isInvisible() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setInvisible(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void damage(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void damage(double v, Entity entity) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getHealth() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setHealth(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getAbsorptionAmount() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setAbsorptionAmount(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public double getMaxHealth() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setMaxHealth(double v) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void resetMaxHealth() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public String getCustomName() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setCustomName(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasMetadata(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isPermissionSet(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasPermission(String s) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean hasPermission(Permission permission) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void recalculatePermissions() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public boolean isOp() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void setOp(boolean b) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        throw new UnsupportedOperationException("Viscosity fake player.");

    }
}
