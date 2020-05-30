package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

public class EntityCombustListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {

        if (!(event.getEntity() instanceof LivingEntity))
            return;

        LivingEntity livingEntity = (LivingEntity)event.getEntity();

        BaseGameplayRegisterable gameplayRegisterable = PluginCore.getGameplayService().findRegisterableByUUID(livingEntity.getUniqueId());

        if (gameplayRegisterable != null)
            event.setCancelled(true);
    }
    //endregion
}
