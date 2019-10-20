package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.serializers.WeaponEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class WeaponEntity {

	public static final String ID_JSON_TAG = "id";
	public static final String IS_WONDER_WEAPON_JSON_TAG = "is_wonder_weapon";
	public static final String ITEM_JSON_TAG = "item";
	public static final String NAME_JSON_TAG = "name";
	public static final WeaponEntityJsonSerializer SERIALIZER = new WeaponEntityJsonSerializer();
	public static final String SERVICES_JSON_TAG = "services";
	public static final String WEAPON_CLASS_ID_JSON_TAG = "weapon_class_id";

	private String id;
	private boolean isWonderWeapon;
	private String item;
	private String name;
	private List<WeaponServiceEntity> services = new ArrayList<WeaponServiceEntity>();
	private String weaponClassId;

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
	 * Gets whether the weapon is a wonder weapon.
	 */
	public boolean isWonderWeapon() {
		return isWonderWeapon;
	}

	/**
	 * Sets whether the weapon is a wonder weapon.
	 */
	public void setIsWonderWeapon(boolean isWonderWeapon) {
		this.isWonderWeapon = isWonderWeapon;
	}

	/**
	 * Gets the weapon's item.
	 */
	public String getItem() {
		return item;
	}

	/**
	 * Sets the weapon's item.
	 */
	public void setItem(String item) {
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
	 * Gets the weapon's services.
	 */
	public List<WeaponServiceEntity> getServices() {
		return services;
	}

	/**
	 * Gets the weapon class ID.
	 */
	public String getWeaponClassId() {
		return weaponClassId;
	}

	/**
	 * Sets the weapon class ID.
	 */
	public void setWeaponClassId(String weaponClassId) {
		this.weaponClassId = weaponClassId;
	}

	/**
	 * Gets whether this weapon can be Pack-A-Punched.
	 */
	public boolean canPackAPunch() {

		for (WeaponServiceEntity service : services) {
			if (service.getTypeUUID().equals(WeaponService.PACK_A_PUNCH))
				return true;
		}

		return false;
	}

	/**
	 * Gets this weapon's class.
	 */
	public WeaponClassEntity getWeaponClass() {

		for (WeaponClassEntity weaponClass : PluginCore.getWeaponsClasses()) {

			if (weaponClass.getId().equals(weaponClassId))
				return weaponClass;
		}

		return null;
	}

	public WeaponEntity() {
	}

	public WeaponEntity(String id, String name, String weaponClassId) {

		this.id = id;
		this.name = name;
		this.weaponClassId = weaponClassId;
		isWonderWeapon = false;

		WeaponClassEntity weaponClass = getWeaponClass();

		if (weaponClass == null)
			return;

		item = weaponClass.getDefaultItem();
		services.addAll(weaponClass.getDefaultServices());
	}
}