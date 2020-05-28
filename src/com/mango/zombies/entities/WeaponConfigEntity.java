package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

public class WeaponConfigEntity {

    //region Constant Values
    public static final boolean DEFAULT_ENABLE_EGG_PROJECTILE_HATCHING = false;

    public static final ChatColor DEFAULT_AMMO_INDICATOR_COLOR = ChatColor.AQUA;
    public static final ChatColor DEFAULT_OUT_OF_AMMO_INDICATOR_COLOR = ChatColor.RED;
    public static final ChatColor DEFAULT_RELOADING_INDICATOR_COLOR = ChatColor.RED;
    public static final ChatColor DEFAULT_DEFAULT_WEAPON_COLOR = ChatColor.GREEN;

    public static final int DEFAULT_DEFAULT_ACCURACY = 90;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_ACCURACY = 95;
    public static final int DEFAULT_DEFAULT_GUNSHOT_DAMAGE = 30;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_GUNSHOT_DAMAGE = 60;
    public static final int DEFAULT_DEFAULT_INITIAL_MAGAZINE_COUNT = 5;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_INITIAL_MAGAZINE_COUNT = 8;
    public static final int DEFAULT_DEFAULT_MAGAZINE_CAPACITY = 8;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_MAGAZINE_CAPACITY = 12;
    public static final int DEFAULT_DEFAULT_MELEE_DAMAGE = 155;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_MELEE_DAMAGE = 1000;
    public static final int DEFAULT_DEFAULT_PROJECTILE_COUNT = 1;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_PROJECTILE_COUNT = 1;
    public static final int DEFAULT_DEFAULT_RELOAD_SPEED = 3;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_RELOAD_SPEED = 5;
    public static final int DEFAULT_DEFAULT_TOTAL_AMMO_CAPACITY = 80;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_TOTAL_AMMO_CAPACITY = 120;

    public static final Material DEFAULT_DEFAULT_GUNSHOT_ITEM = Material.STICK;
    public static final Material DEFAULT_DEFAULT_MELEE_ITEM = Material.IRON_SWORD;

    public static final Sound DEFAULT_DEFAULT_GUNSHOT_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;
    public static final Sound DEFAULT_DEFAULT_PACK_A_PUNCH_GUNSHOT_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;
    public static final Sound DEFAULT_DEFAULT_MELEE_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;
    public static final Sound DEFAULT_DEFAULT_PACK_A_PUNCH_MELEE_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;
    public static final Sound DEFAULT_DEFAULT_OUT_OF_AMMO_SOUND = Sound.BLOCK_DISPENSER_FAIL;
    public static final Sound DEFAULT_DEFAULT_PACK_A_PUNCH_OUT_OF_AMMO_SOUND = Sound.BLOCK_DISPENSER_FAIL;

    public static final String DEFAULT_DEFAULT_PROJECTILE = Material.SNOWBALL.name();
    public static final String DEFAULT_DEFAULT_PACK_A_PUNCH_PROJECTILE = Material.SNOWBALL.name();
    //endregion

    //region Fields
    @Expose private boolean enableEggProjectileHatching = DEFAULT_ENABLE_EGG_PROJECTILE_HATCHING;

    @Expose private ChatColor ammoIndicatorColor = DEFAULT_AMMO_INDICATOR_COLOR;
    @Expose private ChatColor outOfAmmoIndicatorColor = DEFAULT_OUT_OF_AMMO_INDICATOR_COLOR;
    @Expose private ChatColor reloadingIndicatorColor = DEFAULT_RELOADING_INDICATOR_COLOR;
    @Expose private ChatColor defaultWeaponColor = DEFAULT_DEFAULT_WEAPON_COLOR;

    @Expose private int defaultAccuracy = DEFAULT_DEFAULT_ACCURACY;
    @Expose private int defaultPackAPunchAccuracy = DEFAULT_DEFAULT_PACK_A_PUNCH_ACCURACY;
    @Expose private int defaultGunshotDamage = DEFAULT_DEFAULT_GUNSHOT_DAMAGE;
    @Expose private int defaultPackAPunchGunshotDamage = DEFAULT_DEFAULT_PACK_A_PUNCH_GUNSHOT_DAMAGE;
    @Expose private int defaultInitialMagazineCount = DEFAULT_DEFAULT_INITIAL_MAGAZINE_COUNT;
    @Expose private int defaultPackAPunchInitialMagazineCount = DEFAULT_DEFAULT_PACK_A_PUNCH_INITIAL_MAGAZINE_COUNT;
    @Expose private int defaultMagazineCapacity = DEFAULT_DEFAULT_MAGAZINE_CAPACITY;
    @Expose private int defaultPackAPunchMagazineCapacity = DEFAULT_DEFAULT_PACK_A_PUNCH_MAGAZINE_CAPACITY;
    @Expose private int defaultMeleeDamage = DEFAULT_DEFAULT_MELEE_DAMAGE;
    @Expose private int defaultPackAPunchMeleeDamage = DEFAULT_DEFAULT_PACK_A_PUNCH_MELEE_DAMAGE;
    @Expose private int defaultProjectileCount = DEFAULT_DEFAULT_PROJECTILE_COUNT;
    @Expose private int defaultPackAPunchProjectileCount = DEFAULT_DEFAULT_PACK_A_PUNCH_PROJECTILE_COUNT;
    @Expose private int defaultReloadSpeed = DEFAULT_DEFAULT_RELOAD_SPEED;
    @Expose private int defaultPackAPunchReloadSpeed = DEFAULT_DEFAULT_PACK_A_PUNCH_RELOAD_SPEED;
    @Expose private int defaultTotalAmmoCapacity = DEFAULT_DEFAULT_TOTAL_AMMO_CAPACITY;
    @Expose private int defaultPackAPunchTotalAmmoCapacity = DEFAULT_DEFAULT_PACK_A_PUNCH_TOTAL_AMMO_CAPACITY;

    @Expose private Material defaultGunshotItem = DEFAULT_DEFAULT_GUNSHOT_ITEM;
    @Expose private Material defaultMeleeItem = DEFAULT_DEFAULT_MELEE_ITEM;

    @Expose private Sound defaultGunshotUsageSound = DEFAULT_DEFAULT_GUNSHOT_USAGE_SOUND;
    @Expose private Sound defaultPackAPunchGunshotUsageSound = DEFAULT_DEFAULT_PACK_A_PUNCH_GUNSHOT_USAGE_SOUND;
    @Expose private Sound defaultMeleeUsageSound = DEFAULT_DEFAULT_MELEE_USAGE_SOUND;
    @Expose private Sound defaultPackAPunchMeleeUsageSound = DEFAULT_DEFAULT_PACK_A_PUNCH_MELEE_USAGE_SOUND;
    @Expose private Sound defaultOutOfAmmoSound = DEFAULT_DEFAULT_OUT_OF_AMMO_SOUND;
    @Expose private Sound defaultPackAPunchOutOfAmmoSound = DEFAULT_DEFAULT_PACK_A_PUNCH_OUT_OF_AMMO_SOUND;

    @Expose private String defaultProjectile = DEFAULT_DEFAULT_PROJECTILE;
    @Expose private String defaultPackAPunchProjectile = DEFAULT_DEFAULT_PACK_A_PUNCH_PROJECTILE;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether egg projectile hatching is enabled.
     */
    public boolean isEggProjectileHatchingEnabled() {
        return enableEggProjectileHatching;
    }

    /**
     * Sets whether egg projectile hatching is enabled.
     */
    public void setIsEggProjectileHatchingEnabled(boolean enableEggProjectileHatching) {
        this.enableEggProjectileHatching = enableEggProjectileHatching;
    }




    /**
     * Gets the ammo indicator color.
     */
    public ChatColor getAmmoIndicatorColor() {
        return ammoIndicatorColor;
    }

    /**
     * Sets the ammo indicator color.
     */
    public void setAmmoIndicatorColor(ChatColor ammoIndicatorColor) {
        this.ammoIndicatorColor = ammoIndicatorColor;
    }

    /**
     * Gets the out of ammo indicator color.
     */
    public ChatColor getOutOfAmmoIndicatorColor() {
        return outOfAmmoIndicatorColor;
    }

    /**
     * Sets the out of ammo indicator color.
     */
    public void setOutOfAmmoIndicatorColor(ChatColor outOfAmmoIndicatorColor) {
        this.outOfAmmoIndicatorColor = outOfAmmoIndicatorColor;
    }

    /**
     * Gets the reloading indicator color.
     */
    public ChatColor getReloadingIndicatorColor() {
        return reloadingIndicatorColor;
    }

    /**
     * Sets the reloading indicator color.
     */
    public void setReloadingIndicatorColor(ChatColor reloadingIndicatorColor) {
        this.reloadingIndicatorColor = reloadingIndicatorColor;
    }

    /**
     * Gets the default weapon color.
     */
    public ChatColor getDefaultWeaponColor() {
        return defaultWeaponColor;
    }

    /**
     * Sets the default weapon color.
     */
    public void setDefaultWeaponColor(ChatColor defaultWeaponColor) {
        this.defaultWeaponColor = defaultWeaponColor;
    }


    /**
     * Gets the default accuracy.
     */
    public int getDefaultAccuracy() {
        return defaultAccuracy;
    }

    /**
     * Sets the default accuracy.
     */
    public void setDefaultAccuracy(int defaultAccuracy) {
        this.defaultAccuracy = defaultAccuracy;
    }

    /**
     * Gets the default Pack-A-Punch accuracy.
     */
    public int getDefaultPackAPunchAccuracy() {
        return defaultPackAPunchAccuracy;
    }

    /**
     * Sets the default Pack-A-Punch accuracy.
     */
    public void setDefaultPackAPunchAccuracy(int defaultPackAPunchAccuracy) {
        this.defaultPackAPunchAccuracy = defaultPackAPunchAccuracy;
    }

    /**
     * Gets the default gunshot damage.
     */
    public int getDefaultGunshotDamage() {
        return defaultGunshotDamage;
    }

    /**
     * Sets the default gunshot damage.
     */
    public void setDefaultGunshotDamage(int defaultGunshotDamage) {
        this.defaultGunshotDamage = defaultGunshotDamage;
    }

    /**
     * Gets the default Pack-A-Punch gunshot damage.
     */
    public int getDefaultPackAPunchGunshotDamage() {
        return defaultPackAPunchGunshotDamage;
    }

    /**
     * Sets the default Pack-A-Punch gunshot damage.
     */
    public void setDefaultPackAPunchGunshotDamage(int defaultPackAPunchGunshotDamage) {
        this.defaultPackAPunchGunshotDamage = defaultPackAPunchGunshotDamage;
    }

    /**
     * Gets the default initial magazine capacity.
     */
    public int getDefaultInitialMagazineCount() {
        return defaultInitialMagazineCount;
    }

    /**
     * Sets the default initial magazine capacity.
     */
    public void setDefaultInitialMagazineCount(int defaultInitialMagazineCount) {
        this.defaultInitialMagazineCount = defaultInitialMagazineCount;
    }

    /**
     * Gets the default Pack-A-Punch initial magazine capacity.
     */
    public int getDefaultPackAPunchInitialMagazineCount() {
        return defaultPackAPunchInitialMagazineCount;
    }

    /**
     * Sets the default Pack-A-Punch initial magazine capacity.
     */
    public void setDefaultPackAPunchInitialMagazineCount(int defaultPackAPunchInitialMagazineCount) {
        this.defaultPackAPunchInitialMagazineCount = defaultPackAPunchInitialMagazineCount;
    }

    /**
     * Gets the default magazine capacity.
     */
    public int getDefaultMagazineCapacity() {
        return defaultMagazineCapacity;
    }

    /**
     * Sets the default magazine capacity.
     */
    public void setDefaultMagazineCapacity(int defaultMagazineCapacity) {
        this.defaultMagazineCapacity = defaultMagazineCapacity;
    }

    /**
     * Gets the default Pack-A-Punch magazine capacity.
     */
    public int getDefaultPackAPunchMagazineCapacity() {
        return defaultPackAPunchMagazineCapacity;
    }

    /**
     * Sets the default Pack-A-Punch magazine capacity.
     */
    public void setDefaultPackAPunchMagazineCapacity(int defaultPackAPunchMagazineCapacity) {
        this.defaultPackAPunchMagazineCapacity = defaultPackAPunchMagazineCapacity;
    }

    /**
     * Gets the default melee damage.
     */
    public int getDefaultMeleeDamage() {
        return defaultMeleeDamage;
    }

    /**
     * Sets the default melee damage.
     */
    public void setDefaultMeleeDamage(int defaultMeleeDamage) {
        this.defaultMeleeDamage = defaultMeleeDamage;
    }

    /**
     * Gets the default Pack-A-Punch melee damage.
     */
    public int getDefaultPackAPunchMeleeDamage() {
        return defaultPackAPunchMeleeDamage;
    }

    /**
     * Sets the default Pack-A-Punch melee damage.
     */
    public void setDefaultPackAPunchMeleeDamage(int defaultPackAPunchMeleeDamage) {
        this.defaultPackAPunchMeleeDamage = defaultPackAPunchMeleeDamage;
    }

    /**
     * Gets the default projectile count.
     */
    public int getDefaultProjectileCount() {
        return defaultProjectileCount;
    }

    /**
     * Sets the default projectile count.
     */
    public void setDefaultProjectileCount(int defaultProjectileCount) {
        this.defaultProjectileCount = defaultProjectileCount;
    }

    /**
     * Gets the default Pack-A-Punch projectile count.
     */
    public int getDefaultPackAPunchProjectileCount() {
        return defaultPackAPunchProjectileCount;
    }

    /**
     * Sets the default Pack-A-Punch projectile count.
     */
    public void setDefaultPackAPunchProjectileCount(int defaultPackAPunchProjectileCount) {
        this.defaultPackAPunchProjectileCount = defaultPackAPunchProjectileCount;
    }

    /**
     * Gets the default reload speed.
     */
    public int getDefaultReloadSpeed() {
        return defaultReloadSpeed;
    }

    /**
     * Sets the default reload speed.
     */
    public void setDefaultReloadSpeed(int defaultReloadSpeed) {
        this.defaultReloadSpeed = defaultReloadSpeed;
    }

    /**
     * Gets the default Pack-A-Punch reload speed.
     */
    public int getDefaultPackAPunchReloadSpeed() {
        return defaultPackAPunchReloadSpeed;
    }

    /**
     * Sets the default Pack-A-Punch reload speed.
     */
    public void setDefaultPackAPunchReloadSpeed(int defaultPackAPunchReloadSpeed) {
        this.defaultPackAPunchReloadSpeed = defaultPackAPunchReloadSpeed;
    }

    /**
     * Gets the default total ammo capacity.
     */
    public int getDefaultTotalAmmoCapacity() {
        return defaultTotalAmmoCapacity;
    }

    /**
     * Sets the default total ammo capacity.
     */
    public void setDefaultTotalAmmoCapacity(int defaultTotalAmmoCapacity) {
        this.defaultTotalAmmoCapacity = defaultTotalAmmoCapacity;
    }

    /**
     * Gets the default Pack-A-Punch total ammo capacity.
     */
    public int getDefaultPackAPunchTotalAmmoCapacity() {
        return defaultPackAPunchTotalAmmoCapacity;
    }

    /**
     * Sets the default Pack-A-Punch reload speed.
     */
    public void setDefaultPackAPunchTotalAmmoCapacity(int defaultPackAPunchTotalAmmoCapacity) {
        this.defaultPackAPunchTotalAmmoCapacity = defaultPackAPunchTotalAmmoCapacity;
    }


    /**
     * Gets the default gunshot item.
     */
    public Material getDefaultGunshotItem() {
        return defaultGunshotItem;
    }

    /**
     * Sets the default gunshot item.
     */
    public void setDefaultGunshotItem(Material defaultGunshotItem) {
        this.defaultGunshotItem = defaultGunshotItem;
    }

    /**
     * Gets the default melee item.
     */
    public Material getDefaultMeleeItem() {
        return defaultMeleeItem;
    }

    /**
     * Sets the default melee item.
     */
    public void setDefaultMeleeItem(Material defaultMeleeItem) {
        this.defaultMeleeItem = defaultMeleeItem;
    }




    /**
     * Gets the default gunshot usage sound.
     */
    public Sound getDefaultGunshotUsageSound() {
        return defaultGunshotUsageSound;
    }

    /**
     * Sets the default gunshot usage sound.
     */
    public void setDefaultGunshotUsageSound(Sound defaultGunshotUsageSound) {
        this.defaultGunshotUsageSound = defaultGunshotUsageSound;
    }

    /**
     * Gets the default Pack-A-Punch gunshot usage sound.
     */
    public Sound getDefaultPackAPunchGunshotUsageSound() {
        return defaultPackAPunchGunshotUsageSound;
    }

    /**
     * Sets the default Pack-A-Punch gunshot usage sound.
     */
    public void setDefaultPackAPunchGunshotUsageSound(Sound defaultPackAPunchGunshotUsageSound) {
        this.defaultPackAPunchGunshotUsageSound = defaultPackAPunchGunshotUsageSound;
    }

    /**
     * Gets the default melee usage sound.
     */
    public Sound getDefaultMeleeUsageSound() {
        return defaultMeleeUsageSound;
    }

    /**
     * Sets the default melee usage sound.
     */
    public void setDefaultMeleeUsageSound(Sound defaultMeleeUsageSound) {
        this.defaultMeleeUsageSound = defaultMeleeUsageSound;
    }

    /**
     * Gets the default Pack-A-Punch melee usage sound.
     */
    public Sound getDefaultPackAPunchMeleeUsageSound() {
        return defaultPackAPunchMeleeUsageSound;
    }

    /**
     * Sets the default Pack-A-Punch melee usage sound.
     */
    public void setDefaultPackAPunchMeleeUsageSound(Sound defaultPackAPunchMeleeUsageSound) {
        this.defaultPackAPunchMeleeUsageSound = defaultPackAPunchMeleeUsageSound;
    }

    /**
     * Gets the default out of ammo sound.
     */
    public Sound getDefaultOutOfAmmoSound() {
        return defaultOutOfAmmoSound;
    }

    /**
     * Sets the default out of ammo sound.
     */
    public void setDefaultOutOfAmmoSound(Sound defaultOutOfAmmoSound) {
        this.defaultOutOfAmmoSound = defaultOutOfAmmoSound;
    }

    /**
     * Gets the default Pack-A-Punch out of ammo sound.
     */
    public Sound getDefaultPackAPunchOutOfAmmoSound() {
        return defaultPackAPunchOutOfAmmoSound;
    }

    /**
     * Sets the default Pack-A-Punch out of ammo sound.
     */
    public void setDefaultPackAPunchOutOfAmmoSound(Sound defaultPackAPunchOutOfAmmoSound) {
        this.defaultPackAPunchOutOfAmmoSound = defaultPackAPunchOutOfAmmoSound;
    }




    /**
     * Gets the default projectile.
     */
    public String getDefaultProjectile() {
        return defaultProjectile;
    }

    /**
     * Sets the default projectile.
     */
    public void setDefaultProjectile(String defaultProjectile) {
        this.defaultProjectile = defaultProjectile;
    }

    /**
     * Gets the default Pack-A-Punch projectile.
     */
    public String getDefaultPackAPunchProjectile() {
        return defaultPackAPunchProjectile;
    }

    /**
     * Sets the default Pack-A-Punch projectile.
     */
    public void setDefaultPackAPunchProjectile(String defaultPackAPunchProjectile) {
        this.defaultPackAPunchProjectile = defaultPackAPunchProjectile;
    }
    //endregion
}
