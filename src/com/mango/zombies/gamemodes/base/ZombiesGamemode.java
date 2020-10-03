package com.mango.zombies.gamemodes.base;

import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.BaseGameplayLiving;
import com.mango.zombies.gameplay.base.GameplayDrop;
import com.mango.zombies.helper.SoundUtil;
import com.mango.zombies.schema.DamagerType;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
     * Called when an enemy despawns.
     * @param gameplayEnemy The despawned enemy.
     */
    public void onEnemyDespawned(GameplayEnemy gameplayEnemy) { }

    /**
     * Called when an entity is damaged.
     * @param damagedEntity The entity that was damaged.
     * @param damagerEntity The entity that damaged the entity.
     */
    public void onEntityDamaged(BaseGameplayLiving damagedEntity, BaseGameplayLiving damagerEntity) {
    }

    /**
     * Called when an entity is downed.
     * @param downedEntity The entity that was downed.
     * @param downerEntity The entity that downed the entity.
     * @param damagerType The type of damage dealt to the entity.
     */
    public void onEntityDowned(BaseGameplayLiving downedEntity, BaseGameplayLiving downerEntity, DamagerType damagerType) {
    }
    //endregion

    //region Lifecycle
    public void prepare() {

        // Set the starting values of the player just in case they changed between joining the session and now.
        for (GameplayPlayer gameplayPlayer : gameplaySession.getPlayers()) {

            Player player = gameplayPlayer.getPlayer();

            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);

            player.setHealth(20);
            player.setFoodLevel(20);
        }
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
