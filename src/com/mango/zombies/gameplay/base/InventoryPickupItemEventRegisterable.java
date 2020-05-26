package com.mango.zombies.gameplay.base;

import org.bukkit.event.inventory.InventoryPickupItemEvent;

public interface InventoryPickupItemEventRegisterable {

    //region Event Handlers
    /**
     * Called when an inventory picks up an item.
     * @param event The event caused by the pickup.
     */
    void onInventoryPickUpItem(InventoryPickupItemEvent event);
    //endregion
}
