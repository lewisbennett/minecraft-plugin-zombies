package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TurnedGamemodeConfigEntity {

    //region Fields
    @Expose private final List<LocationEntity> playerSpawns = new ArrayList<LocationEntity>();
    @Expose private final List<LocationEntity> zombieCureSpawns = new ArrayList<LocationEntity>();

    @Expose private final List<LoadoutEntity> loadouts = new ArrayList<LoadoutEntity>();
    //endregion

    //region Getters/Setters
    /**
     * Gets the loadouts.
     */
    public LoadoutEntity[] getLoadouts() {
        return loadouts.toArray(new LoadoutEntity[0]);
    }

    /**
     * Gets the locations where players can spawn in the map.
     */
    public LocationEntity[] getPlayerSpawns() {
        return playerSpawns.toArray(new LocationEntity[0]);
    }

    /**
     * Gets the zombie cure spawn locations.
     */
    public LocationEntity[] getZombieCureSpawns() {
        return zombieCureSpawns.toArray(new LocationEntity[0]);
    }
    //endregion

    //region Public Methods
    /**
     * Adds a loadout.
     */
    public void addLoadout(LoadoutEntity loadout) {
        loadouts.add(loadout);
    }

    /**
     * Adds a player spawn location.
     * @param location The player spawn location.
     */
    public void addPlayerSpawnLocation(LocationEntity location) {
        playerSpawns.add(location);
    }

    /**
     * Adds a zombie cure spawn location.
     */
    public void addZombieCureSpawn(LocationEntity location) {
        zombieCureSpawns.add(location);
    }

    /**
     * Removes a loadout.
     */
    public void removeLoadout(LoadoutEntity loadout) {
        loadouts.remove(loadout);
    }

    /**
     * Removes a player spawn location.
     * @param location The player spawn location to remove.
     */
    public void removePlayerSpawnLocation(LocationEntity location) {
        playerSpawns.remove(location);
    }

    /**
     * Removes a zombie cure spawn location.
     */
    public void removeZombieCureLocation(LocationEntity location) {
        zombieCureSpawns.remove(location);
    }
    //endregion
}
