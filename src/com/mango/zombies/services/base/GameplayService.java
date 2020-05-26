package com.mango.zombies.services.base;

import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.GameplayRegisterable;

import java.util.UUID;

public interface GameplayService {

    //region Getters/Setters
    /**
     * Gets the currently active registerables.
     */
    GameplayRegisterable[] getRegisterables();
    //endregion

    //region Public Methods
    /**
     * Calculates how many enemies to spawn in a round.
     * @param playerCount The number of players in the game.
     * @param round The round.
     */
    int calculateEnemiesForRound(int playerCount, int round);

    /**
     * Calculates the health for an enemy on a particular round.
     * @param round The round.
     * @param roundMultiplier The round multiplier.
     * @param maxHealth The maximum health that can be achieved in a game.
     */
    int calculateHealthForRound(int round, double roundMultiplier, int maxHealth);

    /**
     * Calculates the spawn rate for a given round.
     * @param round The round.
     */
    double calculateSpawnRateForRound(int round);

    /**
     * Creates a session for a map.
     * @param map The map to create the session for.
     */
    GameplaySession createSession(MapEntity map, String gamemode);

    /**
     * Creates a session for a map.
     * @param mapId The ID of the map to create the session for.
     */
    GameplaySession createSession(String mapId, String gamemode);

    /**
     * Finds a registerable by its UUID, if any.
     * @param registerableUuid The UUID of the registerable to find.
     */
    GameplayRegisterable findRegisterableByUUID(UUID registerableUuid);

    /**
     * Registers an object for use in game.
     * @param gameplayRegisterable The object to register.
     */
    void register(GameplayRegisterable gameplayRegisterable);

    /**
     * Unregisters an object from in game use.
     * @param gameplayRegisterable The object to unregister.
     */
    void unregister(GameplayRegisterable gameplayRegisterable);
    //endregion
}
