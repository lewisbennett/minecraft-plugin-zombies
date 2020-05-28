package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.PlayerSwapHandsItemEventRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(PlayerSwapHandItemsEvent event) {

        for (GameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (queryRegisterable instanceof PlayerSwapHandsItemEventRegisterable)
                ((PlayerSwapHandsItemEventRegisterable)queryRegisterable).onPlayerSwapHansItem(event);
        }
    }
    //endregion
}
