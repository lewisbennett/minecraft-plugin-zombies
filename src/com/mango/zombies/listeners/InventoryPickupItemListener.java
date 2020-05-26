package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.InventoryPickupItemEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

import java.util.UUID;

public class InventoryPickupItemListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onInventoryPickupItemListener(InventoryPickupItemEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(InventoryPickupItemEvent event) {

        UUID registerableUuid = null;

        String itemCustomName = event.getItem().getCustomName();

        if (itemCustomName != null && !itemCustomName.isEmpty()) {

            try {
                registerableUuid = UUID.fromString(itemCustomName);
            } catch (Exception ignored) { }

        } else
            registerableUuid = HiddenStringUtils.extractUuidFromItemStack(event.getItem().getItemStack());

        if (registerableUuid == null)
            return;

        GameplayRegisterable registerable = PluginCore.getGameplayService().findRegisterableByUUID(registerableUuid);

        if (registerable instanceof InventoryPickupItemEventRegisterable)
            ((InventoryPickupItemEventRegisterable)registerable).onInventoryPickUpItem(event);
    }
    //endregion
}
