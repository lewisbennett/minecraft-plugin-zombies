package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayLiving;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof LivingEntity) || event.getCause() != EntityDamageEvent.DamageCause.FIRE)
            return;

        LivingEntity livingEntity = (LivingEntity)event.getEntity();

        BaseGameplayRegisterable gameplayRegisterable = PluginCore.getGameplayService().findRegisterableByUUID(livingEntity.getUniqueId());

        if (gameplayRegisterable instanceof BaseGameplayLiving)
            event.setCancelled(true);
    }
    //endregion
}
