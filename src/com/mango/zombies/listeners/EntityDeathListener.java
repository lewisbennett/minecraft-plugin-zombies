package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        BaseGameplayRegisterable gameplayRegisterable = PluginCore.getGameplayService().findRegisterableByUUID(event.getEntity().getUniqueId());

        if (gameplayRegisterable instanceof GameplayEnemy) {
            event.getDrops().clear();
            event.setDroppedExp(0);
        }
    }
    //endregion
}
