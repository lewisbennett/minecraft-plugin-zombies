package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import java.util.UUID;

public class InventoryPickupItemListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onInventoryPickupItemListener(InventoryPickupItemEvent event) {

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getItem().getCustomName()));
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null)
            event.setCancelled(true);
    }
    //endregion
}
