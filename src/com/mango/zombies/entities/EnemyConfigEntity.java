package com.mango.zombies.entities;

import com.mango.zombies.serializers.EnemyConfigEntityJsonSerializer;

public class EnemyConfigEntity {

    //region Constant Values
    public static final double DEFAULT_DEFAULT_ROUND_MULTIPLIER = 1;

    public static final EnemyConfigEntityJsonSerializer SERIALIZER = new EnemyConfigEntityJsonSerializer();

    public static final int DEFAULT_DEFAULT_DESPAWN_TIME = 5;
    public static final int DEFAULT_DEFAULT_MAX_HEALTH = -1;

    public static final String DEFAULT_ROUND_MULTIPLIER_JSON_TAG = "defaultRoundMultiplier";

    public static final String DEFAULT_DESPAWN_TIME_JSON_TAG = "defaultDespawnTime";
    public static final String DEFAULT_MAX_HEALTH_JSON_TAG = "defaultMaxHealth";
    //endregion

    //region Fields
    private double defaultRoundMultiplier = DEFAULT_DEFAULT_ROUND_MULTIPLIER;

    private int defaultDespawnTime = DEFAULT_DEFAULT_DESPAWN_TIME;
    private int defaultMaxHealth = DEFAULT_DEFAULT_MAX_HEALTH;
    //endregion

    //region Getters/Setters
    /**
     * Gets the default round multiplier
     */
    public double getDefaultRoundMultiplier() {
        return defaultRoundMultiplier;
    }

    /**
     * Sets the default round multiplier.
     */
    public void setDefaultRoundMultiplier(double defaultRoundMultiplier) {
        this.defaultRoundMultiplier = defaultRoundMultiplier;
    }

    /**
     * Gets the default despawn time.
     */
    public int getDefaultDespawnTime() {
        return defaultDespawnTime;
    }

    /**
     * Sets the default despawn time.
     */
    public void setDefaultDespawnTime(int defaultDespawnTime) {
        this.defaultDespawnTime = defaultDespawnTime;
    }

    /**
     * Gets the default max health.
     */
    public int getDefaultMaxHealth() {
        return defaultMaxHealth;
    }

    /**
     * Sets the default max health.
     */
    public void setDefaultMaxHealth(int defaultMaxHealth) {
        this.defaultMaxHealth = defaultMaxHealth;
    }
    //endregion
}
