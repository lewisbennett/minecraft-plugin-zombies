package com.mango.zombies.gameplay;

import com.mango.zombies.Main;
import com.mango.zombies.PluginCore;
import com.mango.zombies.Time;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.gameplay.base.BaseGameplayLiving;
import com.mango.zombies.schema.DamagerType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

public class GameplayEnemy extends BaseGameplayLiving {

    //region Fields
    private boolean hasBeenSpawned;

    private final EnemyEntity enemyEntity;

    private Location spawnLocation;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether this entity can bleed out
     */
    public boolean canBleedOut() {
        return true;
    }

    /**
     * Gets whether this entity can heal after being damaged.
     */
    public boolean canHeal() {
        return false;
    }

    /**
     * Gets the chance the entity has of bleeding out once the threshold has been met.
     */
    public int getBleedOutChance() {
        return enemyEntity.getBleedOutChance();
    }

    /**
     * Gets the health requirement before the entity can begin to bleed out.
     */
    public int getBleedOutThreshold() {
        return (int)Math.round(getInitialHealth() * enemyEntity.getBleedOutHealthMultiplier());
    }

    /**
     * Gets the entity's initial health.
     */
    public int getInitialHealth() {
        return PluginCore.getGameplayService().calculateHealthForRound(getGameplaySession() == null ? 1 : getGameplaySession().getGamemode().getCurrentRound(), enemyEntity.getRoundMultiplier(), enemyEntity.getMaxHealth());
    }

    /**
     * Gets the duration that the entity should be nuked for, in seconds.
     */
    public int getNukeDuration() {
        return (int)Math.round(Math.random() * 10);
    }

    /**
     * Gets the enemy's spawn location.
     */
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /**
     * Sets the enemy's spawn location.
     */
    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
    //endregion

    //region Public Methods
    /**
     * Despawns the enemy.
     */
    public void despawn() {

        if (getLivingEntity() != null)
            getLivingEntity().remove();
    }

    /**
     * Downs the entity.
     * @param downerEntity The entity that downed this entity.
     * @param damagerType The type of damage that was dealt.
     */
    @Override
    public void down(BaseGameplayLiving downerEntity, DamagerType damagerType) {

        super.down(downerEntity, damagerType);

        getLivingEntity().damage(100);

        unregister();
    }

    /**
     * Immobilises the entity.
     */
    public void immobilise() {
    }

    /**
     * Mobilises the entity.
     */
    public void mobilise() {
    }

    /**
     * Spawns the enemy.
     */
    public void spawn() {

        if (spawnLocation == null || hasBeenSpawned || enemyEntity.getEntityType().getEntityClass() == null)
            return;

        World world = spawnLocation.getWorld();

        if (world == null)
            return;

        Entity entity = world.spawn(spawnLocation, enemyEntity.getEntityType().getEntityClass());

        if (!(entity instanceof LivingEntity)) {
            entity.remove();
            return;
        }

        hasBeenSpawned = true;

        setLivingEntity((LivingEntity)entity);

        register();

        Main instance = Main.getInstance();

        instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, this::despawn_runnable, Time.fromMinutes(enemyEntity.getDespawnTime()).totalTicks());
    }
    //endregion

    //region Runnables
    private void despawn_runnable() {

        if (hasBeenDamaged())
            return;

        getLivingEntity().remove();

        if (getGameplaySession() != null)
            getGameplaySession().getGamemode().onEnemyDespawned(this);
    }
    //endregion

    //region Constructors
    public GameplayEnemy(EnemyEntity enemyEntity) {
        this(null, enemyEntity);
    }

    public GameplayEnemy(GameplaySession gameplaySession, EnemyEntity enemyEntity) {
        super(gameplaySession);

        this.enemyEntity = enemyEntity;
    }
    //endregion
}
