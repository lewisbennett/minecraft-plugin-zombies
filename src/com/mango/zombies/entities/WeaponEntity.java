package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import com.mango.zombies.PluginCore;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.schema.WeaponServiceCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;

import java.util.ArrayList;
import java.util.List;

public class WeaponEntity {

	//region Fields
	@Expose private ChatColor weaponColor;

	@Expose private int cost;

	@Expose private List<WeaponServiceEntity> services = new ArrayList<WeaponServiceEntity>();

	@Expose private Material item;

	@Expose private String id;
	@Expose private String name;
	@Expose private String packAPunchName;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the cost of the weapon.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost of the weapon.
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Gets the weapon's ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the weapon's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the weapon's item.
	 */
	public Material getItem() {
		return item;
	}

	/**
	 * Sets the weapon's item.
	 */
	public void setItem(Material item) {
		this.item = item;
	}

	/**
	 * Gets the weapon's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the weapon's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name used when this weapon has been Pack-A-Punched.
	 */
	public String getPackAPunchName() {
		return packAPunchName;
	}

	/**
	 * Sets the name used when this weapon has been Pack-A-Punched.
	 */
	public void setPackAPunchName(String packAPunchName) {
		this.packAPunchName = packAPunchName;
	}

	/**
	 * Gets the weapon's services.
	 */
	public List<WeaponServiceEntity> getServices() {
		return new ArrayList<WeaponServiceEntity>(services);
	}

	/**
	 * Gets the weapon color.
	 */
	public ChatColor getWeaponColor() {
		return weaponColor;
	}

	/**
	 * Sets the weapon color.
	 */
	public void setWeaponColor(ChatColor weaponColor) {
		this.weaponColor = weaponColor;
	}
	//endregion

	//region Public Methods
	/**
	 * Adds a service to this weapon.
	 * @param service The service to add.
	 */
	public void addService(WeaponServiceEntity service) {
		services.add(service);
	}

	/**
	 * Gets the accuracy from a service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punched service.
	 */
	public int getAccuracy(boolean isPackAPunched) {
		return getAccuracy(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the accuracy from a service, if available, or default.
	 * @param service The service to look for the accuracy in.
	 */
	public int getAccuracy(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity accuracyCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.ACCURACY);

		if (accuracyCharacteristic == null || !(accuracyCharacteristic.getValue() instanceof Number))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchAccuracy() : PluginCore.getWeaponConfig().getDefaultAccuracy();

		return ((Number)accuracyCharacteristic.getValue()).intValue();
	}

	/**
	 * Gets a specific characteristic from a service, if available.
	 * @param service The service to look for the characteristic in.
	 * @param characteristicType The characteristic type to search for.
	 */
	public WeaponServiceCharacteristicEntity getCharacteristic(WeaponServiceEntity service, String characteristicType) {

		for (WeaponServiceCharacteristicEntity queryCharacteristic : service.getCharacteristics()) {

			if (queryCharacteristic.getType().equals(characteristicType))
				return queryCharacteristic;
		}

		return null;
	}

	/**
	 * Gets the damage dealt by a specific service type, if available, or 0.
	 * @param serviceType The service type to find the damage for.
	 * @param isPackAPunched Whether the weapon is Pack-A-Punched.
	 */
	public int getDamage(String serviceType, boolean isPackAPunched) {

		WeaponServiceEntity service = getService(serviceType, isPackAPunched);

		return service == null ? 0 : service.getDamage();
	}

	/**
	 * Gets the magazine capacity from a service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public int getMagazineCapacity(boolean isPackAPunched) {
		return getMagazineCapacity(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the magazine capacity from a service, if available, or default.
	 * @param service The service to look for the magazine capacity in.
	 */
	public int getMagazineCapacity(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity magazineCapacityCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.MAGAZINE_CAPACITY);

		if (magazineCapacityCharacteristic == null || !(magazineCapacityCharacteristic.getValue() instanceof Number))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchMagazineCapacity() : PluginCore.getWeaponConfig().getDefaultMagazineCapacity();

		return ((Number)magazineCapacityCharacteristic.getValue()).intValue();
	}

	/**
	 * Gets the out of ammo sound from a service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public Sound getOutOfAmmoSound(boolean isPackAPunched) {
		return getOutOfAmmoSound(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the out of ammo sound from a service, if available, or default.
	 * @param service The service to look for the out of ammo sound in.
	 */
	public Sound getOutOfAmmoSound(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity outOfAmmoSoundCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.OUT_OF_AMMO_SOUND);

		if (outOfAmmoSoundCharacteristic == null || !(outOfAmmoSoundCharacteristic.getValue() instanceof Sound))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchOutOfAmmoSound() : PluginCore.getWeaponConfig().getDefaultOutOfAmmoSound();

		return (Sound)outOfAmmoSoundCharacteristic.getValue();
	}

	/**
	 * Gets the projectile count from a gunshot service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public int getProjectileCount(boolean isPackAPunched) {
		return getProjectileCount(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the projectile count from a gunshot service, if available, or default.
	 * @param service The service to look for the projectile count in.
	 */
	public int getProjectileCount(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity projectileCountCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.PROJECTILES_IN_CARTRIDGE);

		if (projectileCountCharacteristic == null || !(projectileCountCharacteristic.getValue() instanceof Number))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchProjectileCount() : PluginCore.getWeaponConfig().getDefaultProjectileCount();

		return ((Number)projectileCountCharacteristic.getValue()).intValue();
	}

	/**
	 * Gets the projectile type from a gunshot service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public Class<? extends Projectile> getProjectileType(boolean isPackAPunched) {
		return getProjectileType(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the projectile type from a gunshot service, if available, or default.
	 * @param service The service to look for the projectile type in.
	 */
	public Class<? extends Projectile> getProjectileType(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity projectileTypeCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.PROJECTILE_TYPE);

		if (projectileTypeCharacteristic != null && projectileTypeCharacteristic.getValue() instanceof String) {

			Class<? extends Projectile> clazz = getClassForType((String)projectileTypeCharacteristic.getValue());

			if (clazz != null)
				return clazz;
		}

		Class<? extends Projectile> clazz = getClassForType(service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultProjectile() : PluginCore.getWeaponConfig().getDefaultPackAPunchProjectile());

		return clazz == null ? Snowball.class : clazz;
	}

	/**
	 * Gets the reload speed from a gunshot service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public int getReloadSpeed(boolean isPackAPunched) {
		return getReloadSpeed(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the reload speed from a gunshot service, if available, or default.
	 * @param service The service to look for the reload speed in.
	 */
	public int getReloadSpeed(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity reloadSpeedCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.RELOAD_SPEED);

		if (reloadSpeedCharacteristic == null || !(reloadSpeedCharacteristic.getValue() instanceof Number))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchReloadSpeed() : PluginCore.getWeaponConfig().getDefaultReloadSpeed();

		return ((Number)reloadSpeedCharacteristic.getValue()).intValue();
	}

	/**
	 * Gets a specific service from the weapon, if available.
	 * @param serviceType The service type to search for.
	 * @param isPackAPunched Whether to search for a standard or Pack-A-Punched service. Standard will be returned if Pack-A-Punched is not available.
	 */
	public WeaponServiceEntity getService(String serviceType, boolean isPackAPunched) {

		WeaponServiceEntity standardService = null;
		WeaponServiceEntity packAPunchedService = null;

		for (WeaponServiceEntity queryService : services) {

			if (!queryService.getType().equals(serviceType))
				continue;

			if (queryService.doesRequirePackAPunch())
				packAPunchedService = queryService;
			else
				standardService = queryService;
		}

		if (isPackAPunched && packAPunchedService != null)
			return packAPunchedService;

		return standardService;
	}

	/**
	 * Gets the total ammo capacity from a gunshot service, if available, or default.
	 * @param isPackAPunched Whether to look for a Pack-A-Punch service.
	 */
	public int getTotalAmmoCapacity(boolean isPackAPunched) {
		return getTotalAmmoCapacity(getService(WeaponService.GUNSHOT, isPackAPunched));
	}

	/**
	 * Gets the total ammo capacity from a gunshot service, if available, or default.
	 * @param service The service to look for the total ammo capacity in.
	 */
	public int getTotalAmmoCapacity(WeaponServiceEntity service) {

		if (service == null || !service.getType().equals(WeaponService.GUNSHOT))
			throw new IllegalArgumentException("Gunshot service required.");

		WeaponServiceCharacteristicEntity totalAmmoCapacityCharacteristic = getCharacteristic(service, WeaponServiceCharacteristic.TOTAL_AMMO_CAPACITY);

		if (totalAmmoCapacityCharacteristic == null || !(totalAmmoCapacityCharacteristic.getValue() instanceof Number))
			return service.doesRequirePackAPunch() ? PluginCore.getWeaponConfig().getDefaultPackAPunchTotalAmmoCapacity() : PluginCore.getWeaponConfig().getDefaultTotalAmmoCapacity();

		return ((Number)totalAmmoCapacityCharacteristic.getValue()).intValue();
	}
	//endregion

	//region Constructors
	public WeaponEntity() { }

	public WeaponEntity(String id, String weaponType, String name, int cost) {
		this(id, weaponType, name, cost, null);
	}

	public WeaponEntity(String id, String weaponType, String name, int cost, Material item) {

		this();

		this.cost = cost;
		this.id = id;
		this.item = item;
		this.name = name;
		packAPunchName = "Upgraded " + this.name;

		configureWeaponDefaults(weaponType);
	}
	//endregion

	//region Private Methods
	private void configureWeaponDefaults(String weaponType) {

		WeaponConfigEntity config = PluginCore.getWeaponConfig();

		WeaponServiceEntity standardService = new WeaponServiceEntity();
		WeaponServiceEntity packAPunchService = new WeaponServiceEntity();

		standardService.setDoesRequirePackAPunch(false);
		packAPunchService.setDoesRequirePackAPunch(true);

		if (weaponType.equals(WeaponService.GUNSHOT)) {

			if (item == null)
				item = config.getDefaultGunshotItem();

			standardService.setDamage(config.getDefaultGunshotDamage());
			standardService.setType(WeaponService.GUNSHOT);
			standardService.setUsageSound(config.getDefaultGunshotUsageSound());

			WeaponServiceCharacteristicEntity accuracyCharacteristic = new WeaponServiceCharacteristicEntity();
			accuracyCharacteristic.setType(WeaponServiceCharacteristic.ACCURACY);
			accuracyCharacteristic.setValue(config.getDefaultAccuracy());

			WeaponServiceCharacteristicEntity ammoCostCharacteristic = new WeaponServiceCharacteristicEntity();
			ammoCostCharacteristic.setType(WeaponServiceCharacteristic.AMMO_COST);
			ammoCostCharacteristic.setValue(cost / 2);

			WeaponServiceCharacteristicEntity magazineCapacityCharacteristic = new WeaponServiceCharacteristicEntity();
			magazineCapacityCharacteristic.setType(WeaponServiceCharacteristic.MAGAZINE_CAPACITY);
			magazineCapacityCharacteristic.setValue(config.getDefaultMagazineCapacity());

			WeaponServiceCharacteristicEntity outOfAmmoSoundCharacteristic = new WeaponServiceCharacteristicEntity();
			outOfAmmoSoundCharacteristic.setType(WeaponServiceCharacteristic.OUT_OF_AMMO_SOUND);
			outOfAmmoSoundCharacteristic.setValue(config.getDefaultOutOfAmmoSound());

			WeaponServiceCharacteristicEntity reloadSpeedCharacteristic = new WeaponServiceCharacteristicEntity();
			reloadSpeedCharacteristic.setType(WeaponServiceCharacteristic.RELOAD_SPEED);
			reloadSpeedCharacteristic.setValue(config.getDefaultReloadSpeed());

			WeaponServiceCharacteristicEntity projectileTypeCharacteristic = new WeaponServiceCharacteristicEntity();
			projectileTypeCharacteristic.setType(WeaponServiceCharacteristic.PROJECTILE_TYPE);
			projectileTypeCharacteristic.setValue(config.getDefaultProjectile());

			WeaponServiceCharacteristicEntity projectilesInCartridgeCharacteristic = new WeaponServiceCharacteristicEntity();
			projectilesInCartridgeCharacteristic.setType(WeaponServiceCharacteristic.PROJECTILES_IN_CARTRIDGE);
			projectilesInCartridgeCharacteristic.setValue(config.getDefaultProjectileCount());

			WeaponServiceCharacteristicEntity totalAmmoCapacityCharacteristic = new WeaponServiceCharacteristicEntity();
			totalAmmoCapacityCharacteristic.setType(WeaponServiceCharacteristic.TOTAL_AMMO_CAPACITY);
			totalAmmoCapacityCharacteristic.setValue(config.getDefaultTotalAmmoCapacity());

			standardService.addCharacteristic(accuracyCharacteristic);
			standardService.addCharacteristic(ammoCostCharacteristic);
			standardService.addCharacteristic(magazineCapacityCharacteristic);
			standardService.addCharacteristic(outOfAmmoSoundCharacteristic);
			standardService.addCharacteristic(reloadSpeedCharacteristic);
			standardService.addCharacteristic(projectileTypeCharacteristic);
			standardService.addCharacteristic(projectilesInCartridgeCharacteristic);
			standardService.addCharacteristic(totalAmmoCapacityCharacteristic);

			packAPunchService.setDamage(config.getDefaultPackAPunchGunshotDamage());
			packAPunchService.setType(WeaponService.GUNSHOT);
			packAPunchService.setUsageSound(config.getDefaultPackAPunchGunshotUsageSound());

			WeaponServiceCharacteristicEntity accuracyPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			accuracyPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.ACCURACY);
			accuracyPackAPunchCharacteristic.setValue(config.getDefaultPackAPunchAccuracy());

			WeaponServiceCharacteristicEntity ammoCostPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			ammoCostPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.AMMO_COST);
			ammoCostPackAPunchCharacteristic.setValue(cost);

			WeaponServiceCharacteristicEntity magazineCapacityPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			magazineCapacityPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.MAGAZINE_CAPACITY);
			magazineCapacityPackAPunchCharacteristic.setValue(config.getDefaultPackAPunchMagazineCapacity());

			WeaponServiceCharacteristicEntity outOfAmmoSoundPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			outOfAmmoSoundPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.OUT_OF_AMMO_SOUND);
			outOfAmmoSoundPackAPunchCharacteristic.setValue(config.getDefaultPackAPunchOutOfAmmoSound());

			WeaponServiceCharacteristicEntity reloadSpeedPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			reloadSpeedPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.RELOAD_SPEED);
			reloadSpeedPackAPunchCharacteristic.setValue(config.getDefaultPackAPunchReloadSpeed());

			WeaponServiceCharacteristicEntity projectileTypePackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			projectileTypePackAPunchCharacteristic.setType(WeaponServiceCharacteristic.PROJECTILE_TYPE);
			projectileTypePackAPunchCharacteristic.setValue(config.getDefaultPackAPunchProjectile());

			WeaponServiceCharacteristicEntity projectilesInCartridgePackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			projectilesInCartridgePackAPunchCharacteristic.setType(WeaponServiceCharacteristic.PROJECTILES_IN_CARTRIDGE);
			projectilesInCartridgePackAPunchCharacteristic.setValue(config.getDefaultPackAPunchProjectileCount());

			WeaponServiceCharacteristicEntity totalAmmoCapacityPackAPunchCharacteristic = new WeaponServiceCharacteristicEntity();
			totalAmmoCapacityPackAPunchCharacteristic.setType(WeaponServiceCharacteristic.TOTAL_AMMO_CAPACITY);
			totalAmmoCapacityPackAPunchCharacteristic.setValue(config.getDefaultPackAPunchTotalAmmoCapacity());

			packAPunchService.addCharacteristic(accuracyPackAPunchCharacteristic);
			packAPunchService.addCharacteristic(ammoCostPackAPunchCharacteristic);
			packAPunchService.addCharacteristic(magazineCapacityPackAPunchCharacteristic);
			packAPunchService.addCharacteristic(outOfAmmoSoundPackAPunchCharacteristic);
			packAPunchService.addCharacteristic(reloadSpeedPackAPunchCharacteristic);
			packAPunchService.addCharacteristic(projectileTypePackAPunchCharacteristic);
			packAPunchService.addCharacteristic(projectilesInCartridgePackAPunchCharacteristic);
			packAPunchService.addCharacteristic(totalAmmoCapacityPackAPunchCharacteristic);

		} else if (weaponType.equals(WeaponService.MELEE)) {

			if (item == null)
				item = config.getDefaultMeleeItem();

			standardService.setDamage(config.getDefaultMeleeDamage());
			standardService.setType(WeaponService.MELEE);
			standardService.setUsageSound(config.getDefaultMeleeUsageSound());

			packAPunchService.setDamage(config.getDefaultPackAPunchMeleeDamage());
			packAPunchService.setType(WeaponService.MELEE);
			packAPunchService.setUsageSound(config.getDefaultPackAPunchMeleeUsageSound());
		}

		this.weaponColor = config.getDefaultWeaponColor();

		services.add(standardService);
		services.add(packAPunchService);
	}

	private Class<? extends Projectile> getClassForType(String type) {

		if (type.equals(Material.SNOWBALL.name()))
			return Snowball.class;

		if (type.equals(Material.ARROW.name()))
			return Arrow.class;

		if (type.equals(Material.EGG.name()))
			return Egg.class;

		return null;
	}
	//endregion
}