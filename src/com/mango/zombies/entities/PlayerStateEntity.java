package com.mango.zombies.entities;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerStateEntity {

    //region Fields
    private boolean isFlying;

    private double health;

    private float walkSpeed;

    private GameMode gameMode;

    private int foodLevel;

    private ItemStack[] inventory;

    private Location location;

    private UUID playerUuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether the player was flying.
     */
    public boolean isFlying() {
        return isFlying;
    }

    /**
     * Sets whether the player was flying.
     */
    public void setFlying(boolean isFlying) {
        this.isFlying = isFlying;
    }

    /**
     * Gets the player's gamemode.
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Sets the player's gamemode.
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Gets the player's health.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Sets the player's health.
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Gets the player's inventory.
     */
    public ItemStack[] getInventory() {
        return inventory;
    }

    /**
     * Sets the player's inventory.
     */
    public void setInventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the player's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the player's location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the player's UUID.
     */
    public UUID getPlayerUUID() {
        return playerUuid;
    }

    /**
     * Sets the player's UUID.
     */
    public void setPlayerUUID(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    /**
     * Gets the player's saturation.
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * Sets the player's saturation.
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * Gets the player's walk speed.
     */
    public float getWalkSpeed() {
        return walkSpeed;
    }

    /**
     * Sets the player's walk speed.
     */
    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }
    //endregion

    //region Public Methods
    /**
     * Applies the player state.
     */
    public void applyPlayerState() {

        Player player = Bukkit.getPlayer(playerUuid);

        if (player == null)
            return;

        player.setGameMode(gameMode);
        player.setHealth(health);
        player.setFlying(isFlying);
        player.teleport(location);
        player.setSaturation(foodLevel);
        player.setWalkSpeed(walkSpeed);

        player.getInventory().setContents(inventory);
    }
    //endregion

    //region Public Static Methods
    /**
     * Caches a player state within an entity.
     */
    public static PlayerStateEntity cachePlayerState(Player player) {

        PlayerStateEntity playerState = new PlayerStateEntity();

        playerState.setFlying(player.isFlying());
        playerState.setGameMode(player.getGameMode());
        playerState.setHealth(player.getHealth());
        playerState.setLocation(player.getLocation());
        playerState.setPlayerUUID(player.getUniqueId());
        playerState.setFoodLevel(player.getFoodLevel());
        playerState.setWalkSpeed(player.getWalkSpeed());

        ItemStack[] itemStacks = player.getInventory().getContents();
        ItemStack[] inventory = new ItemStack[itemStacks.length];

        System.arraycopy(itemStacks, 0, inventory, 0, itemStacks.length);

        playerState.setInventory(inventory);

        return playerState;
    }
    //endregion
}
