package com.mango.zombies.entities;

import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.schema.WeaponType;
import com.mango.zombies.serializers.WeaponClassEntityJsonSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeaponClassEntity {

	public static final String COLOR_JSON_TAG = "color";
	public static final String DEFAULT_ITEM_JSON_TAG = "default_item";
	public static final String DEFAULT_SERVICES_JSON_TAG = "default_services";
	public static final String DEFAULT_WEAPON_COST_JSON_TAG = "default_weapon_cost_json_tag";
	public static final String ID_JSON_TAG = "id";
	public static final String NAME_JSON_TAG = "name";
	public static final WeaponClassEntityJsonSerializer SERIALIZER = new WeaponClassEntityJsonSerializer();

	private String color;
	private String defaultItem;
	private List<WeaponServiceEntity> defaultServices = new ArrayList<WeaponServiceEntity>();
	private int defaultWeaponCost;
	private String id;
	private String name;

	/**
	 * Gets the color used for the weapon class.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the color used for the weapon class.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the default item for the weapon class.
	 */
	public String getDefaultItem() {
		return defaultItem;
	}

	/**
	 * Sets the default item for the weapon class.
	 */
	public void setDefaultItem(String defaultItem) {
		this.defaultItem = defaultItem;
	}

	/**
	 * Gets the default services for the weapon class.
	 */
	public List<WeaponServiceEntity> getDefaultServices() {
		return defaultServices;
	}

	/**
	 * Gets the default cost for weapons of this class.
	 */
	public int getDefaultWeaponCost() {
		return defaultWeaponCost;
	}

	/**
	 * Sets the default cost for weapons of this class.
	 */
	public void setDefaultWeaponCost(int defaultWeaponCost) {
		this.defaultWeaponCost = defaultWeaponCost;
	}

	/**
	 * Gets the ID of the weapon class.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID of the weapon class.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name of the weapon class.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the weapon class.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public WeaponClassEntity() {
	}

	public WeaponClassEntity(String id, String name, int defaultWeaponCost, String type, boolean canPackAPunch) {

		this.id = id;
		this.name = name;
		defaultItem = Material.STICK.name();
		this.defaultWeaponCost = defaultWeaponCost;
		color = ChatColor.RED.name();

		int damage;
		UUID typeUUID = null;

		switch (type) {

			case WeaponType.GUNSHOT:
				damage = 1;
				typeUUID = WeaponService.GUNSHOT;
				break;

			case WeaponType.MELEE:
				damage = 5;
				typeUUID = WeaponService.MELEE;
				break;

			default:
				return;
		}

		WeaponServiceEntity standardService = new WeaponServiceEntity(damage, false, typeUUID);
		defaultServices.add(standardService);

		if (standardService.getTypeUUID() != WeaponService.MELEE) {

			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(defaultWeaponCost / 2, WeaponCharacteristic.AMMO_COST));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(8, WeaponCharacteristic.MAGAZINE_SIZE));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(4, WeaponCharacteristic.RELOAD_SPEED));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(80, WeaponCharacteristic.TOTAL_AMMO_CAPACITY));
		}

		if (!canPackAPunch)
			return;

		WeaponServiceEntity packAPunchService = new WeaponServiceEntity(standardService.getDamage(), true, typeUUID);
		defaultServices.add(packAPunchService);

		if (packAPunchService.getTypeUUID() == WeaponService.MELEE)
			return;

		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(defaultWeaponCost, WeaponCharacteristic.AMMO_COST));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(12, WeaponCharacteristic.MAGAZINE_SIZE));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(6, WeaponCharacteristic.RELOAD_SPEED));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(120, WeaponCharacteristic.TOTAL_AMMO_CAPACITY));
	}
}