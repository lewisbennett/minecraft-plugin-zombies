package com.mango.zombies.services;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gamemodes.StandardGamemode;
import com.mango.zombies.gamemodes.TurnedGamemode;
import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.schema.Gamemode;
import com.mango.zombies.services.base.GameplayService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StockGameplayService implements GameplayService {

    //region Constant Values
    public static final String GAMEMODE_DOES_NOT_EXIST_ERROR = "Session not created. %s is not a valid gamemode.";
    public static final String MAP_DISABLED_ERROR = "Session not created. %s is currently disabled.";
    public static final String MAP_ID_DOES_NOT_EXIST_ERROR = "Session not created. %s does not exist.";
    public static final String MAP_DOES_NOT_SUPPORT_TURNED_ERROR = "Session not created. %s does not support Turned.";
    public static final String SESSION_ALREADY_EXISTS_ERROR = "A session for %s already exists. Join using /joinsession %s";
    //endregion

    //region Fields
    private final List<BaseGameplayRegisterable> gameplayRegisterables = new ArrayList<BaseGameplayRegisterable>();
    //endregion

    //region Getters/Setters
    /**
     * Gets the currently active registerables.
     */
    public BaseGameplayRegisterable[] getRegisterables() {
        return gameplayRegisterables.toArray(new BaseGameplayRegisterable[0]);
    }
    //endregion

    //region Public Methods
    /**
     * Registers an object for use in game.
     * @param gameplayRegisterable The object to register.
     */
    public void addRegisterable(BaseGameplayRegisterable gameplayRegisterable) {

        gameplayRegisterables.add(gameplayRegisterable);

        gameplayRegisterable.onRegistered();
    }

    /**
     * Calculates how many enemies to spawn in a round.
     * @param playerCount The number of players in the game.
     * @param round The round.
     */
    public int calculateEnemiesForRound(int playerCount, int round) {
        return (int)Math.round((playerCount * 0.000058) * Math.pow(round, 3) + (playerCount * 0.074032) * Math.pow(round, 2) + (playerCount * 0.718119) * round + 14.738699 * (round < 10 ? 0.45 : 1));
    }

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
     * Calculates the spawn rate for a given round.
     * @param round The round.
     */
    public double calculateSpawnRateForRound(int round) {
        return Math.max(round <= 1 ? 2 : Math.pow(0.95, round - 1), 0.08);
    }

    /**
     * Creates a session for a map.
     * @param map The map to create the session for.
     */
    public GameplaySession createSession(MapEntity map, String gamemode) {

        if (!map.isEnabled())
            throw new IllegalStateException(String.format(MAP_DISABLED_ERROR, map.getName()));

        GameplaySession session = null;

        for (BaseGameplayRegisterable queryRegisterable : gameplayRegisterables) {

            if (!(queryRegisterable instanceof GameplaySession))
                continue;

            GameplaySession querySession = (GameplaySession)queryRegisterable;

            if (querySession.getMap().getId().equals(map.getId())) {
                session = querySession;
                break;
            }
        }

        if (session != null)
            throw new IllegalStateException(String.format(SESSION_ALREADY_EXISTS_ERROR, map.getName(), map.getId()));

        ZombiesGamemode zombiesGamemode;

        switch (gamemode) {

            case Gamemode.TURNED:

                if (map.getTurnedGamemodeConfig().getZombieCureSpawns().length < 1)
                    throw new IllegalStateException(String.format(MAP_DOES_NOT_SUPPORT_TURNED_ERROR, map.getName()));

                zombiesGamemode = new TurnedGamemode();

                break;

            case Gamemode.STANDARD:
                zombiesGamemode = new StandardGamemode();
                break;

            default:
                throw new IllegalStateException(String.format(GAMEMODE_DOES_NOT_EXIST_ERROR, gamemode));
        }

        session = new GameplaySession(map, zombiesGamemode);

        zombiesGamemode.setGameplaySession(session);

        gameplayRegisterables.add(session);

        return session;
    }

    /**
     * Creates a session for a map.
     * @param mapId The ID of the map to create the session for.
     */
    public GameplaySession createSession(String mapId, String gamemode) {

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(mapId)) {
                map = queryMap;
                break;
            }
        }

        if (map == null)
            throw new IllegalStateException(String.format(MAP_ID_DOES_NOT_EXIST_ERROR, mapId));

        return createSession(map, gamemode);
    }

    /**
     * Finds a registerable by its UUID, if any.
     * @param registerableUuid The UUID of the registerable to find.
     */
    public BaseGameplayRegisterable findRegisterableByUUID(UUID registerableUuid) {

        for (BaseGameplayRegisterable queryRegisterable : getRegisterables()) {

            if (queryRegisterable.getUUID().equals(registerableUuid))
                return queryRegisterable;
        }

        return null;
    }

    /**
     * Unregisters an object from in game use.
     * @param gameplayRegisterable The object to unregister.
     */
    public void removeRegisterable(BaseGameplayRegisterable gameplayRegisterable) {

        gameplayRegisterables.remove(gameplayRegisterable);

        gameplayRegisterable.onUnregistered();
    }
    //endregion
}
