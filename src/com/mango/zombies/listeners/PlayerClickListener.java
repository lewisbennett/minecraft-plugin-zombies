package com.mango.zombies.listeners;

import com.mango.zombies.Main;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;
import com.mango.zombies.entities.WeaponServiceEntity;
import com.mango.zombies.gameplay.GameplayCore;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.Sound;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
            melee(event, gameplayWeapon);
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            gunshot(event, gameplayWeapon);
            return;
        }
    }

    private boolean canShoot(GameplayWeapon gameplayWeapon) {

        if (gameplayWeapon.getAmmoInMagazine() > 0)
            return true;

        gameplayWeapon.reload();

        return false;
    }

    private void melee(PlayerInteractEvent event, GameplayWeapon gameplayWeapon) {

        WeaponServiceEntity standardMeleeService = null;
        WeaponServiceEntity packAPunchedMeleeService = null;

        for (WeaponServiceEntity service : gameplayWeapon.getWeapon().getServices()) {

            if (!service.getTypeUUID().equals(WeaponService.MELEE))
                continue;

            if (service.doesRequirePackAPunch())
                packAPunchedMeleeService = service;
            else
                standardMeleeService = service;
        }

        if (standardMeleeService == null && packAPunchedMeleeService == null)
            return;
    }

    private void gunshot(PlayerInteractEvent event, GameplayWeapon gameplayWeapon) {

        WeaponServiceEntity standardGunshotService = null;
        WeaponServiceEntity packAPunchedGunshotService = null;

        for (WeaponServiceEntity service : gameplayWeapon.getWeapon().getServices()) {

            if (!service.getTypeUUID().equals(WeaponService.GUNSHOT))
                continue;

            if (service.doesRequirePackAPunch())
                packAPunchedGunshotService = service;
            else
                standardGunshotService = service;
        }

        if (standardGunshotService == null && packAPunchedGunshotService == null)
            return;

        if (!canShoot(gameplayWeapon)) {
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_DISPENSER_FAIL, 10, 1);
            return;
        }

        WeaponServiceCharacteristicEntity standardProjectilesCharacteristic = null;
        WeaponServiceCharacteristicEntity packAPunchedProjectilesCharacteristic = null;

        for (WeaponServiceCharacteristicEntity characteristic : standardGunshotService.getCharacteristics()) {

            if (characteristic.getTypeUUID().equals(WeaponCharacteristic.PROJECTILES_IN_CARTRIDGE)) {
                standardProjectilesCharacteristic = characteristic;
                break;
            }
        }

        if (packAPunchedGunshotService != null) {

            for (WeaponServiceCharacteristicEntity characteristic : packAPunchedGunshotService.getCharacteristics()) {

                if (characteristic.getTypeUUID().equals(WeaponCharacteristic.PROJECTILES_IN_CARTRIDGE)) {
                    packAPunchedProjectilesCharacteristic = characteristic;
                    break;
                }
            }
        }

        int projectiles = gameplayWeapon.isPackAPunched() && packAPunchedProjectilesCharacteristic != null ? (Integer)packAPunchedProjectilesCharacteristic.getValue() : (Integer)standardProjectilesCharacteristic.getValue();

        int delay = 0;

        for (int i = 0; i < projectiles; i++) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    event.getPlayer().launchProjectile(Snowball.class, event.getPlayer().getLocation().getDirection().multiply(2.5));
                }

            }.runTaskLater(Main.getInstance(), delay);

            delay += 2;
        }

        gameplayWeapon.setAmmoInMagazine(gameplayWeapon.getAmmoInMagazine() - 1);
        canShoot(gameplayWeapon);
    }

    private void handleGun(PlayerInteractEvent event, GameplayWeapon gameplayWeapon) {

        int projectiles = 0;

        for (WeaponServiceEntity service : gameplayWeapon.getWeapon().getServices()) {

            for (WeaponServiceCharacteristicEntity characteristic : service.getCharacteristics()) {

                if (characteristic.getTypeUUID().equals(WeaponCharacteristic.PROJECTILES_IN_CARTRIDGE)) {
                    projectiles = (Integer)characteristic.getValue();
                    break;
                }
            }

            if (projectiles > 0)
                break;
        }

        if (projectiles == 0)
            projectiles = 1;

        int delay = 0;

        for (int i = 0; i < projectiles; i++) {

            new BukkitRunnable() {

                @Override
                public void run() {
                    event.getPlayer().launchProjectile(Snowball.class, event.getPlayer().getLocation().getDirection().multiply(2.5));
                }

            }.runTaskLater(Main.getInstance(), delay);

            delay += 2;
        }
    }
}
