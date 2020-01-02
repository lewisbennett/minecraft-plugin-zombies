package com.mango.zombies.listeners;

import com.mango.zombies.gameplay.GameplayCore;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class WeaponInteractListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {

        if (!doesItemStackHaveValidLore(event.getItem()))
            return;

        UUID uuid;

        try {

            uuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getItem().getItemMeta().getLore().get(0)));

        } catch (Exception e) {
            return;
        }

        GameplayWeapon gameplayWeapon = GameplayCore.getWeapons().get(uuid);

        if (gameplayWeapon == null)
            return;

        gameplayWeapon.setItemStack(event.getItem());

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            gameplayWeapon.shoot(event.getPlayer());
            return;
        }

        if (gameplayWeapon.canMelee()) {
            // Melee.
            return;
        }

        gameplayWeapon.reload(event.getPlayer());
    }
    //endregion

    //region Private Methods
    private boolean doesItemStackHaveValidLore(ItemStack itemStack) {

        return itemStack != null
                && itemStack.getItemMeta() != null
                && itemStack.getItemMeta().getLore() != null
                && itemStack.getItemMeta().getLore().size() > 0
                && itemStack.getItemMeta().getLore().get(0) != null;
    }
    //endregion
}
