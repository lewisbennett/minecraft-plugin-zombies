package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.UUID;

public class PlayerEggThrowListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerThrowEgg(PlayerEggThrowEvent event) {

        String eggCustomName = event.getEgg().getCustomName();

        if (eggCustomName == null || eggCustomName.isEmpty())
            return;

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(eggCustomName);
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null && !PluginCore.getWeaponConfig().isEggProjectileHatchingEnabled())
            event.setHatching(false);
    }
    //endregion
}
