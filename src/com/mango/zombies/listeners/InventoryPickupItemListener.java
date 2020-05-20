package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import java.util.UUID;

public class InventoryPickupItemListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onInventoryPickupItemListener(InventoryPickupItemEvent event) {

        String itemCustomName = event.getItem().getCustomName();

        if (itemCustomName == null || itemCustomName.isEmpty())
            return;

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(itemCustomName);
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null)
            event.setCancelled(true);
    }
    //endregion
}
