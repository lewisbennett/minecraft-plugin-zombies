package com.mango.zombies.gameplay.base;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public interface InventoryClickEventRegisterable {

    //region Event Handlers
    /**
     * Called when an inventory is clicked.
     * @param event The event caused by the click.
     */
    void onInventoryClick(InventoryClickEvent event);
    //endregion
}
