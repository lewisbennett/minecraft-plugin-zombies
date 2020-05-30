package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.gameplay.base.PlayerInteractEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class PlayerInteractListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(PlayerInteractEvent event) {

        UUID registerableUuid = HiddenStringUtils.extractUuidFromItemStack(event.getItem());

        if (registerableUuid == null)
            return;

        BaseGameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(registerableUuid);

        if (registerable instanceof PlayerInteractEventRegisterable)
            ((PlayerInteractEventRegisterable)registerable).onPlayerInteract(event);
    }
    //endregion
}
