package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import java.util.UUID;

public class PlayerEggThrowListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerThrowEgg(PlayerEggThrowEvent event) {

        UUID projectileConfigUuid = null;

        try {
            projectileConfigUuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getEgg().getCustomName()));
        } catch (Exception ignored) { }

        if (projectileConfigUuid != null && PluginCore.getGameplayService().findRegisterableByUUID(projectileConfigUuid) != null && !PluginCore.getWeaponConfig().isEggProjectileHatchingEnabled())
            event.setHatching(false);
    }
    //endregion
}
