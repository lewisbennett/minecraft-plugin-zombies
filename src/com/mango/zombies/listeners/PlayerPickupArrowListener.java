package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.UUID;

public class PlayerPickupArrowListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerPickupArrow(PlayerPickupArrowEvent event) {

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getArrow().getCustomName()));
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null)
            event.setCancelled(true);
    }
    //endregion
}
