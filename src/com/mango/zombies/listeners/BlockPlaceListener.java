package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        MapEntity mapEntity = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.isWithinMapBounds(event.getBlock().getLocation())) {
                mapEntity = queryMap;
                break;
            }
        }

        if (mapEntity != null && mapEntity.isEnabled())
            event.setCancelled(true);
    }
    //endregion
}
