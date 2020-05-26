package com.mango.zombies.gameplay;

import com.mango.zombies.Log;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GameplayPlayer {

    //region Fields
    private double health;

    private float walkSpeed;

    private GameMode gameMode;

    private int points;

    private ItemStack[] inventory;

    private Location location;

    private final UUID playerUuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets the player.
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(playerUuid);
    }

    /**
     * Gets the player's points.
     */
    public int getPoints() {
        return points;
    }
    //endregion

    //region Public Methods
    /**
     * Gives the player points.
     */
    public void addPoints(int points) {
        this.points += points;

        // update scoreboard
        Log.information("Points: " + this.points);
    }

    /**
     * Applies the player state based on the last cache.
     */
    public void applyPlayerState() {

        Player player = getPlayer();

        player.setGameMode(gameMode);
        player.setHealth(health);
        player.teleport(location);
        player.setWalkSpeed(walkSpeed);

        player.getInventory().setContents(inventory);
    }

    /**
     * Saves the players current state.
     */
    public void cachePlayerState() {

        Player player = getPlayer();

        gameMode = player.getGameMode();
        health = player.getHealth();
        location = player.getLocation();
        walkSpeed = player.getWalkSpeed();

        ItemStack[] itemStacks = player.getInventory().getContents();
        inventory = new ItemStack[itemStacks.length];

        System.arraycopy(itemStacks, 0, inventory, 0, itemStacks.length);
    }

    /**
     * Removes points from the player.
     */
    public void removePoints(int points) {
        this.points -= points;
    }
    //endregion

    //region Constructors
    public GameplayPlayer(UUID playerUuid) {
        this.playerUuid = playerUuid;
    }
    //endregion
}
