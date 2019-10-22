package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.serializers.WeaponEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class WeaponEntity {

	//region Constant Values
	public static final String COST_JSON_TAG = "cost";
	public static final String ID_JSON_TAG = "id";
	public static final String IS_WONDER_WEAPON_JSON_TAG = "is_wonder_weapon";
	public static final String ITEM_JSON_TAG = "item";
	public static final String NAME_JSON_TAG = "name";
	public static final String PACK_A_PUNCH_NAME_JSON_TAG = "pack_a_punch_name";
	public static final WeaponEntityJsonSerializer SERIALIZER = new WeaponEntityJsonSerializer();
	public static final String SERVICES_JSON_TAG = "services";
	public static final String WEAPON_CLASS_ID_JSON_TAG = "weapon_class_id";
	//endregion

	//region Fields
	private int cost;
	private String id, item, name, packAPunchName, weaponClassId;
	private boolean isWonderWeapon;
	private List<WeaponServiceEntity> services = new ArrayList<WeaponServiceEntity>();
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
	//endregion

	//region Public Methods
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
	//endregion

	//region Constructors
	public WeaponEntity() {
	}

	public WeaponEntity(String id, String name, String weaponClassId) {

		this.id = id;
		this.name = name;
		this.weaponClassId = weaponClassId;
		isWonderWeapon = false;
		packAPunchName = "Upgraded " + this.name;

		WeaponClassEntity weaponClass = getWeaponClass();

		if (weaponClass == null)
			return;

		cost = weaponClass.getDefaultWeaponCost();
		item = weaponClass.getDefaultItem();
		services.addAll(weaponClass.getDefaultServices());
	}
	//endregion
}