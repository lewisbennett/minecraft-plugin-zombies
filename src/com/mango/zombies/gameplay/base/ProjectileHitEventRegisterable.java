package com.mango.zombies.gameplay.base;

import org.bukkit.event.entity.ProjectileHitEvent;

public interface ProjectileHitEventRegisterable {

    //region Event Handlers
    /**
     * Called when a projectile hits something.
     * @param event The event.
     */
    void onProjectileHit(ProjectileHitEvent event);
    //endregion
}
