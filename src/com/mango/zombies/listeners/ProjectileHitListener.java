package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.ProjectileHitEventRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;

public class ProjectileHitListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(ProjectileHitEvent event) {

        String projectileCustomName = event.getEntity().getCustomName();

        if (projectileCustomName == null || projectileCustomName.isEmpty())
            return;

        UUID registerableUuid = null;

        try {
            registerableUuid = UUID.fromString(projectileCustomName);
        } catch (Exception ignored) { }

        if (registerableUuid == null)
            return;

        GameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(registerableUuid);

        if (registerable instanceof ProjectileHitEventRegisterable)
            ((ProjectileHitEventRegisterable)registerable).onProjectileHit(event);
    }
    //endregion
}
