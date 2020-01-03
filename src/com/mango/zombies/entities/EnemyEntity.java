package com.mango.zombies.entities;

import com.mango.zombies.serializers.EnemyEntityJsonSerializer;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.Random;

public class EnemyEntity {

    //region Constant Values
    public static final int DEFAULT_DESPAWN_TIME = 5;
    public static final int DEFAULT_MAX_HEALTH = -1;
    public static final int DEFAULT_ROUND_ONE_HEALTH = 5;

    public static final EnemyEntityJsonSerializer SERIALIZER = new EnemyEntityJsonSerializer();

    public static final String DESPAWN_TIME_JSON_TAG = "despawn_time";
    public static final String ENTITY_TYPE_JSON_TAG = "entity_type";
    public static final String ID_JSON_TAG = "id";
    public static final String MAX_HEALTH_JSON_TAG = "max_health";
    public static final String ROUND_ONE_HEALTH_JSON_TAG = "round_one_health";
    //endregion

    //region Fields
    private int despawnTime = DEFAULT_DESPAWN_TIME, maxHealth = DEFAULT_MAX_HEALTH, roundOneHealth = DEFAULT_ROUND_ONE_HEALTH;
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

    /**
     * Gets the round one health.
     */
    public int getRoundOneHealth() {
        return roundOneHealth;
    }

    /**
     * Sets the round one health.
     */
    public void setRoundOneHealth(int roundOneHealth) {
        this.roundOneHealth = roundOneHealth;
    }
    //endregion

    //region Constructors
    public EnemyEntity() {
    }

    public EnemyEntity(String id, EntityType entityType, int roundOneHealth) {
        this();

        this.id = id;
        this.entityType = entityType;
        this.roundOneHealth = roundOneHealth;
    }
    //endregion
}
