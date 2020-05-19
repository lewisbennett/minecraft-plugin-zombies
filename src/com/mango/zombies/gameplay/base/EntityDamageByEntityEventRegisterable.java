package com.mango.zombies.gameplay.base;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public interface EntityDamageByEntityEventRegisterable {

    //region Event Handlers
    /**
     * Called when an entity is damaged by another entity.
     * @param event The event.
     */
    void onEntityDamageByEntity(EntityDamageByEntityEvent event);
    //endregion
}
