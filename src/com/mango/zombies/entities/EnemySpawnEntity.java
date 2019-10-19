package com.mango.zombies.entities;

import com.mango.zombies.serializers.EnemySpawnEntityJsonSerializer;
import org.bukkit.Location;

public class EnemySpawnEntity extends LocationEntity {

    public static final String ENEMY_JSON_TAG = "enemy";
    public static final EnemySpawnEntityJsonSerializer SERIALIZER = new EnemySpawnEntityJsonSerializer();

    private EnemyEntity enemy;

    /**
     * Gets the enemy to spawn.
     */
    public EnemyEntity getEnemy() {
        return enemy;
    }

    /**
     * Sets the enemy to spawn.
     */
    public void setEnemy(EnemyEntity enemy) {
        this.enemy = enemy;
    }

    public EnemySpawnEntity() {
        super();
    }

    public EnemySpawnEntity(Location location, EnemyEntity enemy) {
        super(location);

        this.enemy = enemy;
    }
}
