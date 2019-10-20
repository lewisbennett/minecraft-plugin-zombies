package com.mango.zombies.gameplay;

import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;
import com.mango.zombies.entities.WeaponServiceEntity;
import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.inventory.ItemStack;

public class GameplayWeapon {

    private int ammoInMagazine = 0;
    private int availableAmmo = 0;
    private boolean isPackAPunched;
    private boolean isReloading;
    private ItemStack item;
    private WeaponEntity weapon;

    /**
     * Gets how much ammo is in the current magazine.
     */
    public int getAmmoInMagazine() {
        return ammoInMagazine;
    }

    /**
     * Sets how much ammo is in the current magazine.
     */
    public void setAmmoInMagazine(int ammoInMagazine) {
        this.ammoInMagazine = ammoInMagazine;
    }

    /**
     *
     */
    public int getAvailableAmmo() {
        return availableAmmo;
    }

    /**
     * Gets whether this weapon is Pack-A-Punched.
     */
    public boolean isPackAPunched() {
        return isPackAPunched;
    }

    /**
     * Gets whether the weapon is currently reloading.
     */
    public boolean isReloading() {
        return isReloading;
    }

    /**
     * Gets the item.
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Reloads this weapon.
     */
    public void reload() {

        if (isReloading)
            return;
    }

    /**
     * Gets the weapon.
     */
    public WeaponEntity getWeapon() {
        return weapon;
    }

    public GameplayWeapon(ItemStack item, WeaponEntity weapon) {

        this.item = item;
        this.weapon = weapon;
    }
}
