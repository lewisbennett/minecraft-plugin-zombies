package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.serializers.EnemyEntityJsonSerializer;
import org.bukkit.entity.EntityType;

public class EnemyEntity {

    //region Constant Values
    public static final EnemyEntityJsonSerializer SERIALIZER = new EnemyEntityJsonSerializer();

    public static final String DESPAWN_TIME_JSON_TAG = "despawnTime";
    public static final String ENTITY_TYPE_JSON_TAG = "entityType";
    public static final String ID_JSON_TAG = "id";
    public static final String MAX_HEALTH_JSON_TAG = "maxHealth";
    public static final String ROUND_MULTIPLIER_JSON_TAG = "roundMultiplier";
    //endregion

    //region Fields
    private double roundMultiplier;

    private int despawnTime;
    private int maxHealth;

    private EntityType entityType;

    private String id;
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
