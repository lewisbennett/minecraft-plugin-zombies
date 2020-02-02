package com.mango.zombies.entities;

import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.serializers.WeaponClassEntityJsonSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class WeaponClassEntity {

	//region Constant Values
	public static final Sound DEFAULT_GUNSHOT_USAGE_SOUND = Sound.BLOCK_LAVA_POP;
	public static final int DEFAULT_MAGAZINE_SIZE = 8;
	public static final Sound DEFAULT_MELEE_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;
	public static final Sound DEFAULT_OUT_OF_AMMO_SOUND = Sound.BLOCK_DISPENSER_FAIL;
	public static final int DEFAULT_PACK_A_PUNCHED_MAGAZINE_SIZE = 12;
	public static final int DEFAULT_PACK_A_PUNCHED_RELOAD_SPEED = 6;
	public static final int DEFAULT_PACK_A_PUNCHED_TOTAL_AMMO_CAPACITY = 120;
	public static final int DEFAULT_PACK_A_PUNCHED_PROJECTILE_COUNT = 1;
	public static final int DEFAULT_PROJECTILE_COUNT = 1;
	public static final int DEFAULT_RELOAD_SPEED = 4;
	public static final int DEFAULT_TOTAL_AMMO_CAPACITY = 80;

	public static final WeaponClassEntityJsonSerializer SERIALIZER = new WeaponClassEntityJsonSerializer();

	public static final String COLOR_JSON_TAG = "color";
	public static final String ID_JSON_TAG = "id";
	public static final String DEFAULT_ITEM_JSON_TAG = "default_item";
	public static final String DEFAULT_SERVICES_JSON_TAG = "default_services";
	public static final String DEFAULT_WEAPON_COST_JSON_TAG = "default_weapon_cost";
	public static final String NAME_JSON_TAG = "name";
	//endregion

	//region Fields
	private String color;
	private String defaultItem;
	private List<WeaponServiceEntity> defaultServices = new ArrayList<WeaponServiceEntity>();
	private int defaultWeaponCost;
	private String id;
	private String name;
	//endregion

	//region Getters/Setters
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
	//endregion

	//region Constructors
	public WeaponClassEntity() {
	}

	public WeaponClassEntity(String id, String name, int defaultWeaponCost, String type, boolean canPackAPunch) {

		this.id = id;
		this.name = name;
		defaultItem = Material.STICK.name();
		this.defaultWeaponCost = defaultWeaponCost;
		color = ChatColor.RED.name();

		if (!WeaponService.toList().contains(type))
			return;

		int damage;
		Sound usageSound;

		if (type.equals(WeaponService.GUNSHOT)) {
			damage = 20;
			usageSound = DEFAULT_GUNSHOT_USAGE_SOUND;
		} else {
			damage = 155;
			usageSound = DEFAULT_MELEE_USAGE_SOUND;
		}

		WeaponServiceEntity standardService = new WeaponServiceEntity();
		standardService.setDamage(damage);
		standardService.setDoesRequirePackAPunch(false);
		standardService.setType(type);
		standardService.setUsageSound(usageSound);

		defaultServices.add(standardService);

		if (standardService.getType().equals(WeaponService.GUNSHOT)) {

			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(defaultWeaponCost / 2, WeaponCharacteristic.AMMO_COST));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_MAGAZINE_SIZE, WeaponCharacteristic.MAGAZINE_SIZE));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_RELOAD_SPEED, WeaponCharacteristic.RELOAD_SPEED));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_TOTAL_AMMO_CAPACITY, WeaponCharacteristic.TOTAL_AMMO_CAPACITY));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_PROJECTILE_COUNT, WeaponCharacteristic.PROJECTILES_IN_CARTRIDGE));
			standardService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_OUT_OF_AMMO_SOUND, WeaponCharacteristic.OUT_OF_AMMO_SOUND));
		}

		if (!canPackAPunch)
			return;

		WeaponServiceEntity packAPunchService = new WeaponServiceEntity();
		packAPunchService.setDamage(standardService.getDamage() * 3);
		packAPunchService.setDoesRequirePackAPunch(true);
		packAPunchService.setType(type);
		packAPunchService.setUsageSound(usageSound);

		defaultServices.add(packAPunchService);

		if (!packAPunchService.getType().equals(WeaponService.GUNSHOT))
			return;

		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(defaultWeaponCost, WeaponCharacteristic.AMMO_COST));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_PACK_A_PUNCHED_MAGAZINE_SIZE, WeaponCharacteristic.MAGAZINE_SIZE));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_PACK_A_PUNCHED_RELOAD_SPEED, WeaponCharacteristic.RELOAD_SPEED));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_PACK_A_PUNCHED_TOTAL_AMMO_CAPACITY, WeaponCharacteristic.TOTAL_AMMO_CAPACITY));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_PROJECTILE_COUNT, WeaponCharacteristic.PROJECTILES_IN_CARTRIDGE));
		packAPunchService.getCharacteristics().add(new WeaponServiceCharacteristicEntity(DEFAULT_OUT_OF_AMMO_SOUND, WeaponCharacteristic.OUT_OF_AMMO_SOUND));
	}
	//endregion
}