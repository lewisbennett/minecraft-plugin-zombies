package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.GameplayProjectile;
import com.mango.zombies.gameplay.base.BlockBreakEventRegisterable;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.ProjectileHitEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.ProjectileConfigComponent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.AbstractMap;
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

        UUID registerableUuid = null;

        try {
            registerableUuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getEntity().getCustomName()));
        } catch (Exception ignored) { }

        if (registerableUuid == null)
            return;

        GameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(registerableUuid);

        if (registerable instanceof ProjectileHitEventRegisterable)
            ((ProjectileHitEventRegisterable)registerable).onProjectileHit(event);
    }
    //endregion
}
