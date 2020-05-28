package com.mango.zombies.gamemodes.base;

import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.GameplayDrop;
import com.mango.zombies.helper.SoundUtil;
import org.bukkit.Sound;

public abstract class ZombiesGamemode {

    //region Fields
    private GameplaySession gameplaySession;

    private int currentRound;
    //endregion

    //region Getters/Setters
    /**
     * Gets the gameplay session.
     */
    public GameplaySession getGameplaySession() {
        return gameplaySession;
    }

    /**
     * Sets the gameplay session.
     */
    public void setGameplaySession(GameplaySession gameplaySession) {
        this.gameplaySession = gameplaySession;
    }

    /**
     * Gets the minimum number of players needed to begin the gamemode
     */
    public abstract int getMinimumPlayers();

    /**
     * Gets the name of the gamemode.
     */
    public abstract String getName();

    /**
     * Gets the current round.
     */
    public int getCurrentRound() {
        return currentRound;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when a drop is picked up.
     * @param gameplayPlayer The player who picked up the drop.
     * @param gameplayDrop The picked up drop.
     */
    public void onDropPickedUp(GameplayPlayer gameplayPlayer, GameplayDrop gameplayDrop) {

        Sound sound = gameplayDrop.getSound();

        if (sound != null) {

            for (GameplayPlayer queryPlayer : getGameplaySession().getPlayers())
                SoundUtil.playInfiniteSound(queryPlayer.getPlayer(), sound);
        }
    }

    /**
     * Called when an enemy is damaged.
     * @param gameplayPlayer The player that damaged the enemy.
     * @param gameplayEnemy The damaged enemy.
     */
    public void onEnemyDamaged(GameplayPlayer gameplayPlayer, GameplayEnemy gameplayEnemy) { }

    /**
     * Called when an enemy despawns.
     * @param gameplayEnemy The despawned enemy.
     */
    public void onEnemyDespawned(GameplayEnemy gameplayEnemy) { }

    /**
     * Called when an enemy is killed.
     * @param gameplayPlayer The player that killed the enemy.
     * @param gameplayEnemy The killed enemy.
     */
    public void onEnemyKilled(GameplayPlayer gameplayPlayer, GameplayEnemy gameplayEnemy, String weaponServiceType) { }
    //endregion

    //region Lifecycle
    public void prepare() {
    }

    public void startRound(int round) {
        currentRound = round;
    }

    public void endRound() {
    }

    public void endGame() {
    }
    //endregion
}
