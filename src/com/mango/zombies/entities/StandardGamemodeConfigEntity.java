package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class StandardGamemodeConfigEntity {

    //region Fields
    @Expose private final List<LockedLocationEntity> enemySpawns = new ArrayList<LockedLocationEntity>();
    @Expose private final List<LockedLocationEntity> playerSpawns = new ArrayList<LockedLocationEntity>();

    @Expose private final List<String> enemyBlacklist = new ArrayList<String>();
    @Expose private final List<String> enemyWhitelist = new ArrayList<String>();
    //endregion

    //region Getters/Setters
    /**
     * Gets the enemy blacklist.
     */
    public String[] getEnemyBlacklist() {
        return enemyBlacklist.toArray(new String[0]);
    }

    /**
     * Gets the locations where enemies can spawn in the map.
     */
    public LockedLocationEntity[] getEnemySpawns() {
        return enemySpawns.toArray(new LockedLocationEntity[0]);
    }

    /**
     * Gets the enemy whitelist.
     */
    public String[] getEnemyWhitelist() {
        return enemyWhitelist.toArray(new String[0]);
    }

    /**
     * Gets the locations where players can spawn in the map.
     */
    public LockedLocationEntity[] getPlayerSpawns() {
        return playerSpawns.toArray(new LockedLocationEntity[0]);
    }
    //endregion

    //region Public Methods
    /**
     * Adds an entry to the enemy blacklist.
     * @param enemyId The ID of the enemy to add.
     */
    public void addEnemyBlacklistEntry(String enemyId) {
        enemyBlacklist.add(enemyId);
    }

    /**
     * Adds an entry to the enemy whitelist.
     * @param enemyId The ID of the enemy to add.
     */
    public void addEnemyWhitelistEntry(String enemyId) {
        enemyWhitelist.add(enemyId);
    }

    /**
     * Adds an enemy spawn location.
     * @param location The enemy spawn location.
     */
    public void addEnemySpawnLocation(LockedLocationEntity location) {
        enemySpawns.add(location);
    }

    /**
     * Adds a player spawn location.
     * @param location The player spawn location.
     */
    public void addPlayerSpawnLocation(LockedLocationEntity location) {
        playerSpawns.add(location);
    }

    /**
     * Removes an entry from the enemy blacklist.
     * @param enemyId The ID of the enemy to remove.
     */
    public void removeEnemyBlacklistEntry(String enemyId) {
        enemyBlacklist.remove(enemyId);
    }

    /**
     * Removes an entry from the enemy whitelist.
     * @param enemyId The ID of the enemy to remove.
     */
    public void removeEnemyWhitelistEntry(String enemyId) {
        enemyWhitelist.remove(enemyId);
    }

    /**
     * Removes an enemy spawn location.
     * @param location The enemy spawn location to remove.
     */
    public void removeEnemySpawnLocation(LockedLocationEntity location) {
        enemySpawns.remove(location);
    }

    /**
     * Removes a player spawn location.
     * @param location The player spawn location to remove.
     */
    public void removePlayerSpawnLocation(LockedLocationEntity location) {
        playerSpawns.remove(location);
    }
    //endregion
}
