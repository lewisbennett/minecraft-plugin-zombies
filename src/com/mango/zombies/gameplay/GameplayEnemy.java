package com.mango.zombies.gameplay;

import com.mango.zombies.Main;
import com.mango.zombies.PluginCore;
import com.mango.zombies.Time;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.listeners.ProjectileHitListener;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class GameplayEnemy {

    //region Fields
    private EnemyEntity enemyEntity;
    private LivingEntity entity;
    private boolean hasBeenDamaged, hasBeenSpawned;
    private int health;
    private Location spawnLocation;
    //endregion

    //region Getters/Setters
    /**
     * Gets the entity.
     */
    public LivingEntity getEntity() {
        return entity;
    }

    /**
     * Gets the health of the enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the enemy.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the spawn location.
     */
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /**
     * Sets the spawn location.
     */
    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
    //endregion

    //region Public Methods
    /**
     * Damages the enemy.
     */
    public void damage(int damage) {

        if (entity == null)
            return;

        hasBeenDamaged = true;

        health -= damage;

        if (health > 0)
            return;

        entity.damage(100);
        ProjectileHitListener.getGameplayEnemies().remove(this);
    }

    /**
     * Spawns the enemy into the world.
     */
    public void spawn() {

        if (spawnLocation == null || hasBeenSpawned)
            return;

        World world = Main.getInstance().getServer().getWorld(PluginCore.getConfig().getWorldName());

        if (world == null)
            return;

        Entity entity = world.spawn(spawnLocation, enemyEntity.getEntityType().getEntityClass());

        if (!(entity instanceof LivingEntity)) {
            entity.remove();
            return;
        }

        hasBeenSpawned = true;

        this.entity = (LivingEntity)entity;
        ProjectileHitListener.getGameplayEnemies().add(this);

        Main instance = Main.getInstance();

        instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, this::despawn_runnable, Time.fromMinutes(enemyEntity.getDespawnTime()).totalTicks());
    }
    //endregion

    //region Runnables
    private void despawn_runnable() {

        if (!hasBeenDamaged)
            entity.remove();
    }
    //endregion

    //region Constructors
    public GameplayEnemy(EnemyEntity enemyEntity) {
        this.enemyEntity = enemyEntity;
    }
    //endregion
}
