package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.gameplay.base.EntityDamageByEntityEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class EntityDamageByEntityListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player)event.getDamager();

        UUID registerableUuid = HiddenStringUtils.extractUuidFromItemStack(player.getInventory().getItemInMainHand());

        if (registerableUuid == null)
            return;

        BaseGameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(registerableUuid);

        if (registerable instanceof EntityDamageByEntityEventRegisterable)
            ((EntityDamageByEntityEventRegisterable)registerable).onEntityDamageByEntity(event);
    }
    //endregion
}
