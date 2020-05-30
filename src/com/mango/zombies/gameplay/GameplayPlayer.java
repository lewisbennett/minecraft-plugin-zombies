package com.mango.zombies.gameplay;

import com.mango.zombies.Log;
import com.mango.zombies.entities.PlayerStateEntity;
import com.mango.zombies.gamemodes.StandardGamemode;
import com.mango.zombies.gameplay.base.BaseGameplayLiving;
import org.bukkit.entity.Player;

public class GameplayPlayer extends BaseGameplayLiving {

    //region Fields
    private GameplayLoadout loadout;

    private int points;

    private PlayerStateEntity playerState;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether this entity can bleed out
     */
    public boolean canBleedOut() {
        return getGameplaySession().getGamemode() instanceof StandardGamemode && getGameplaySession().getPlayers().length > 1;
    }

    /**
     * Gets whether this entity can heal after being damaged.
     */
    public boolean canHeal() {
        return true;
    }

    /**
     * Gets the chance the entity has of bleeding out once the threshold has been met.
     */
    public int getBleedOutChance() {
        return 100;
    }

    /**
     * Gets the health requirement before the entity can begin to bleed out.
     */
    public int getBleedOutThreshold() {
        return (int)Math.round(getInitialHealth() * 0.33);
    }

    /**
     * Gets the entity's initial health.
     */
    public int getInitialHealth() {
        return 100;
    }

    /**
     * Gets the player's loadout.
     */
    public GameplayLoadout getLoadout() {
        return loadout;
    }

    /**
     * Sets the player's loadout.
     */
    public void setLoadout(GameplayLoadout loadout) {
        this.loadout = loadout;
    }

    /**
     * Gets the duration that the entity should be nuked for, in seconds.
     */
    public int getNukeDuration() {
        return (int)Math.round(Math.random() * 10);
    }

    /**
     * Gets the player.
     */
    public Player getPlayer() {
        return (Player)getLivingEntity();
    }
    //endregion

    //region Event Handlers
    /**
     * Called when this gameplay registerabled is unregistered.
     */
    @Override
    public void onUnregistered() {

        super.onRegistered();

        restorePlayerState();
    }
    //endregion

    //region Public Methods
    /**
     * Gives points to the player.
     */
    public void addPoints(int points) {
        this.points += points;

        Log.information("Points: " + this.points);
    }

    /**
     * Caches the player's state.
     */
    public void cachePlayerState() {
        playerState = PlayerStateEntity.cachePlayerState(getPlayer());
    }

    /**
     * Immobilises the entity.
     */
    public void immobilise() {
        getPlayer().setWalkSpeed(0);
    }

    /**
     * Mobilises the entity.
     */
    public void mobilise() {
        getPlayer().setWalkSpeed(0.5f);
    }

    /**
     * Removes points from the player.
     */
    public void removePoints(int points) {

        if (this.points - points < 0)
            this.points = 0;
        else
            this.points -= points;

        Log.information("Points: " + this.points);
    }

    /**
     * Restores the player's state.
     */
    public void restorePlayerState() {
        playerState.applyPlayerState();
    }
    //endregion

    //region Constructors
    public GameplayPlayer(GameplaySession gameplaySession, Player player) {
        super(gameplaySession);

        setLivingEntity(player);
    }
    //endregion
}
