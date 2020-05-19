package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.ProjectileHitEventRegisterable;
import com.mango.zombies.schema.ProjectileConfigComponent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.UUID;

public class GameplayProjectile implements GameplayRegisterable, ProjectileHitEventRegisterable {

    //region Fields
    private HashMap<String, Object> configuration = new HashMap<String, Object>();

    private UUID uuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets the projectile configuration.
     */
    public HashMap<String, Object> getConfiguration() {
        return configuration;
    }

    /**
     * Gets the UUID of this gameplay registerable.
     */
    public UUID getUUID() {
        return uuid;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when a projectile hits something.
     * @param event The event.
     */
    public void onProjectileHit(ProjectileHitEvent event) {

        if (!(event.getHitEntity() instanceof LivingEntity))
            return;

        LivingEntity impactEntity = (LivingEntity)event.getHitEntity();

        GameplayRegisterable gameplayRegisterable = PluginCore.getGameplayService().findRegisterableByUUID(impactEntity.getUniqueId());

        if (!(gameplayRegisterable instanceof GameplayEnemy))
            return;

        GameplayEnemy gameplayEnemy = (GameplayEnemy)gameplayRegisterable;

        if (configuration.containsKey(ProjectileConfigComponent.DAMAGE))
            gameplayEnemy.damage((int)configuration.get(ProjectileConfigComponent.DAMAGE));

        PluginCore.getGameplayService().unregister(this);
    }
    //endregion

    //region Constructors
    public GameplayProjectile() {
        uuid = UUID.randomUUID();
    }
    //endregion
}
