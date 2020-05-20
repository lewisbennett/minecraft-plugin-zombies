package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.UUID;

public class PlayerPickupArrowListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerPickupArrow(PlayerPickupArrowEvent event) {

        String arrowCustomName = event.getArrow().getCustomName();

        if (arrowCustomName == null || arrowCustomName.isEmpty())
            return;

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(arrowCustomName);
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null)
            event.setCancelled(true);
    }
    //endregion
}
