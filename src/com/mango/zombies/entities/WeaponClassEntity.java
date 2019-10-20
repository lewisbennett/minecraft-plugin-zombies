package com.mango.zombies.entities;

import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.schema.WeaponType;
import com.mango.zombies.serializers.WeaponClassEntityJsonSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class WeaponClassEntity {

	public static final String COLOR_JSON_TAG = "color";
	public static final String DEFAULT_ITEM_JSON_TAG = "default_item";
	public static final String DEFAULT_SERVICES_JSON_TAG = "default_services";
	public static final String ID_JSON_TAG = "id";
	public static final String NAME_JSON_TAG = "name";
	public static final WeaponClassEntityJsonSerializer SERIALIZER = new WeaponClassEntityJsonSerializer();

	private String color;
	private String defaultItem;
	private List<WeaponServiceEntity> defaultServices = new ArrayList<WeaponServiceEntity>();
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

	public WeaponClassEntity(String id, String name, int cost, String type, boolean canPackAPunch) {

		this.id = id;
		this.name = name;
		defaultItem = Material.STICK.name();
		color = ChatColor.RED.name();

		WeaponServiceEntity damageService = new WeaponServiceEntity();

		switch (type) {

			case WeaponType.BUCK_SHOT:
				damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(3, WeaponCharacteristic.PROJECTILES_IN_CATRIDGE));
				damageService.setTypeUUID(WeaponService.BUCK_SHOT);
				break;

			case WeaponType.MELEE:
				damageService.setDamage(1);
				damageService.setTypeUUID(WeaponService.MELEE);
				break;

			case WeaponType.SINGLE_SHOT:
				damageService.setTypeUUID(WeaponService.SINGLE_SHOT);
				break;
		}

		defaultServices.add(damageService);

		if (damageService.getTypeUUID() != WeaponService.MELEE) {

			damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(40, WeaponCharacteristic.BULLET_CAPACITY));
			damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(8, WeaponCharacteristic.MAGAZINE_SIZE));
			damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(4, WeaponCharacteristic.RELOAD_SPEED));
			damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(cost / 2, WeaponCharacteristic.AMMO_COST));
		}

		damageService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(cost, WeaponCharacteristic.WEAPON_COST));

		if (canPackAPunch) {

			WeaponServiceEntity packAPunchService = new WeaponServiceEntity(damageService.getDamage() * 5, WeaponService.PACK_A_PUNCH);

			for (WeaponServiceCharacteristicEntity characteristic : damageService.getCharacteristics())
				packAPunchService.getCharacteristics().add(characteristic);

			packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity("Upgraded", WeaponCharacteristic.PACK_A_PUNCH_NAME));

			defaultServices.add(packAPunchService);
		}
	}
}