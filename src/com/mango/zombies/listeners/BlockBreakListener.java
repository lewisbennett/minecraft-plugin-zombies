package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.gameplay.base.BlockBreakEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class BlockBreakListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        handOffToRegisterables(event);

        if (event.isCancelled())
            return;

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

    //region Private Methods
    private void handOffToRegisterables(BlockBreakEvent event) {

        UUID uuid = HiddenStringUtils.extractUuidFromItemStack(event.getPlayer().getInventory().getItemInMainHand());

        if (uuid == null)
            return;

        BaseGameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(uuid);

        if (registerable instanceof BlockBreakEventRegisterable)
            ((BlockBreakEventRegisterable)registerable).onBlockBreak(event);
    }
    //endregion
}
