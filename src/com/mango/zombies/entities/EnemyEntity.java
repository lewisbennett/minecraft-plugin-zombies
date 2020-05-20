package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import com.mango.zombies.PluginCore;
import org.bukkit.entity.EntityType;

public class EnemyEntity {

    //region Fields
    @Expose private double roundMultiplier;

    @Expose private int despawnTime;
    @Expose private int maxHealth;

    @Expose private EntityType entityType;

    @Expose private String id;
    //endregion

    //region Getters/Setters
    /**
     * Gets the despawn time in minutes.
     */
    public int getDespawnTime() {
        return despawnTime;
    }

    /**
     * Sets the despawn time. Only values between 1 and 20 minutes are accepted.
     */
    public void setDespawnTime(int despawnTime) {

        if (despawnTime > 0 && despawnTime <= 20)
            this.despawnTime = despawnTime;
    }

    /**
     * Gets the entity type.
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * Sets the entity type.
     */
    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    /**
     * Gets the ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the round multiplier.
     */
    public double getRoundMultiplier() {
        return roundMultiplier;
    }

    /**
     * Sets the round multiplier.
     */
    public void setRoundMultiplier(double roundMultiplier) {
        this.roundMultiplier = roundMultiplier;
    }

    /**
     * Gets the maximum health that this enemy can achieve in a game.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum health that this enemy can achieve in a game.
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    //endregion

    //region Constructors
    public EnemyEntity() {
    }

    public EnemyEntity(String id, EntityType entityType) {
        this();

        this.id = id;
        this.entityType = entityType;

        EnemyConfigEntity config = PluginCore.getEnemyConfig();

        despawnTime = config.getDefaultDespawnTime();
        maxHealth = config.getDefaultMaxHealth();
        roundMultiplier = config.getDefaultRoundMultiplier();
    }
    //endregion
}
