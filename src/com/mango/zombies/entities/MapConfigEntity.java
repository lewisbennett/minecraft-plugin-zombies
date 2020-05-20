package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Sound;

public class MapConfigEntity {

    //region Constant Values
    public static final int DEFAULT_DEFAULT_MYSTERY_BOX_COST = 950;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_COST = 5000;

    public static final Sound DEFAULT_DEFAULT_ROUND_END_SOUND = Sound.AMBIENT_CAVE;
    public static final Sound DEFAULT_DEFAULT_ROUND_START_SOUND = Sound.AMBIENT_CAVE;
    //endregion

    //region Fields
    @Expose private int defaultMysteryBoxCost = DEFAULT_DEFAULT_MYSTERY_BOX_COST;
    @Expose private int defaultPackAPunchCost = DEFAULT_DEFAULT_PACK_A_PUNCH_COST;

    @Expose private Sound defaultRoundEndSound = DEFAULT_DEFAULT_ROUND_END_SOUND;
    @Expose private Sound defaultRoundStartSound = DEFAULT_DEFAULT_ROUND_START_SOUND;
    //endregion

    //region Getters/Setters
    /**
     * Gets the default mystery box cost.
     */
    public int getDefaultMysteryBoxCost() {
        return defaultMysteryBoxCost;
    }

    /**
     * Sets the default mystery box cost.
     */
    public void setDefaultMysteryBoxCost(int defaultMysteryBoxCost) {
        this.defaultMysteryBoxCost = defaultMysteryBoxCost;
    }

    /**
     * Gets the default Pack-A-Punch cost.
     */
    public int getDefaultPackAPunchCost() {
        return defaultPackAPunchCost;
    }

    /**
     * Sets the default Pack-A-Punch cost.
     */
    public void setDefaultDefaultPackAPunchCost(int defaultPackAPunchCost) {
        this.defaultPackAPunchCost = defaultPackAPunchCost;
    }

    /**
     * Gets the default round end sound.
     */
    public Sound getDefaultRoundEndSound() {
        return defaultRoundEndSound;
    }

    /**
     * Sets the default round end sound.
     */
    public void setDefaultRoundEndSound(Sound defaultRoundEndSound) {
        this.defaultRoundEndSound = defaultRoundEndSound;
    }

    /**
     * Gets the default round start sound.
     */
    public Sound getDefaultRoundStartSound() {
        return defaultRoundStartSound;
    }

    /**
     * Sets the default round start sound.
     */
    public void setDefaultRoundStartSound(Sound defaultRoundStartSound) {
        this.defaultRoundStartSound = defaultRoundStartSound;
    }
    //endregion
}
