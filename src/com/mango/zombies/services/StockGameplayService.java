package com.mango.zombies.services;

import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.services.base.GameplayService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockGameplayService implements GameplayService {

    //region Fields
    private List<GameplayRegisterable> gameplayRegisterables = new ArrayList<GameplayRegisterable>();
    //endregion

    //region Getters/Setters
    /**
     * Gets the currently active registerables.
     */
    public List<GameplayRegisterable> getRegisterables() {
        return new ArrayList<GameplayRegisterable>(gameplayRegisterables);
    }
    //endregion

    //region Public Methods
    /**
     * Calculates the health for an enemy on a particular round.
     * @param round The round.
     * @param roundMultiplier The round multiplier.
     * @param maxHealth The maximum health that can be achieved in a game.
     */
    public int calculateHealthForRound(int round, double roundMultiplier, int maxHealth) {

        // Calculates the health for rounds 1-9 or round 9 if provided round is 10+.
        int health = (int)Math.round((Math.min(round, 9) * roundMultiplier) * 100 + 50);

        // Increase by 10% per round for rounds 10+.
        if (round > 9)
            health = (int)(Math.round(health * Math.pow(1.1, (round - 9) * roundMultiplier)));

        return maxHealth > 0 ? Math.min(health, maxHealth) : health;
    }

    /**
     * Finds a registerable by its UUID, if any.
     * @param registerableUuid The UUID of the registerable to find.
     */
    public GameplayRegisterable findRegisterableByUUID(UUID registerableUuid) {

        for (GameplayRegisterable queryRegisterable : getRegisterables()) {

            if (queryRegisterable.getUUID().equals(registerableUuid))
                return queryRegisterable;
        }

        return null;
    }

    /**
     * Registers an object for use in game.
     * @param gameplayRegisterable The object to register.
     */
    public void register(GameplayRegisterable gameplayRegisterable) {
        gameplayRegisterables.add(gameplayRegisterable);
    }

    /**
     * Unregisters an object from in game use.
     * @param gameplayRegisterable The object to unregister.
     */
    public void unregister(GameplayRegisterable gameplayRegisterable) {
        gameplayRegisterables.remove(gameplayRegisterable);
    }
    //endregion
}
