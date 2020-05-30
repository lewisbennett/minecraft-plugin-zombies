package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.gameplay.base.InventoryClickEventRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(InventoryClickEvent event) {

        for (BaseGameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (queryRegisterable instanceof InventoryClickEventRegisterable)
                ((InventoryClickEventRegisterable)queryRegisterable).onInventoryClick(event);
        }
    }
    //endregion
}
