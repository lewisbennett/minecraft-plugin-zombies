package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Material;

public class GameplayConfigEntity {

    //region Constant Values
    public static final int DEFAULT_LOBBY_COUNTDOWN = 1;

    public static final Material DEFAULT_ZOMBIE_CURE_ITEM = Material.REDSTONE_BLOCK;
    //endregion

    //region Fields
    @Expose private int lobbyCountdown = DEFAULT_LOBBY_COUNTDOWN;

    @Expose private Material zombieCureItem = DEFAULT_ZOMBIE_CURE_ITEM;
    //endregion

    //region Getters/Setters
    /**
     * Gets the lobby countdown.
     */
    public int getLobbyCountdown() {
        return lobbyCountdown;
    }

    /**
     * Sets the lobby countdown.
     */
    public void setLobbyCountdown(int lobbyCountdown) {
        this.lobbyCountdown = lobbyCountdown;
    }

    /**
     * Gets the item used as zombie cures.
     */
    public Material getZombieCureItem() {
        return zombieCureItem;
    }

    /**
     * Sets the item used as zombie cures.
     */
    public void setDefaultZombieCureItem(Material zombieCureItem) {
        this.zombieCureItem = zombieCureItem;
    }
    //endregion
}
