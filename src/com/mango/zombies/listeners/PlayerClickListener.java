package com.mango.zombies.listeners;

import com.mango.zombies.gameplay.GameplayCore;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class PlayerClickListener implements Listener {

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent event) {

        if (event.getItem() == null
            || event.getItem().getItemMeta() == null
            || event.getItem().getItemMeta().getLore() == null
            || event.getItem().getItemMeta().getLore().size() < 1
            || event.getItem().getItemMeta().getLore().get(0) == null)
            return;

        GameplayWeapon gameplayWeapon = GameplayCore.getWeapons().get(UUID.fromString(HiddenStringUtils.extractHiddenString(event.getItem().getItemMeta().getLore().get(0))));

        if (gameplayWeapon == null)
            return;

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (gameplayWeapon.canMelee()) {
                // Melee.
                return;
            }

            gameplayWeapon.reload(event.getItem());

            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            gameplayWeapon.shoot(event.getPlayer(), event.getItem());
    }
}
