package com.mango.zombies.services.base;

import com.mango.zombies.gameplay.base.GameplayRegisterable;

import java.util.List;
import java.util.UUID;

public interface GameplayService {

    //region Getters/Setters
    /**
     * Gets the currently active registerables.
     */
    List<GameplayRegisterable> getRegisterables();
    //endregion

    //region Public Methods
    /**
     * Calculates the health for an enemy on a particular round.
     * @param round The round.
     * @param roundMultiplier The round multiplier.
     * @param maxHealth The maximum health that can be achieved in a game.
     */
    int calculateHealthForRound(int round, double roundMultiplier, int maxHealth);

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
