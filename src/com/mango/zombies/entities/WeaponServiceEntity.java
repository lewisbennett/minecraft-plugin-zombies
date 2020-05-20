package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceEntityJsonSerializer;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class WeaponServiceEntity {

	//region Constant Values
	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DAMAGE_JSON_TAG = "damage";
	public static final String DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG = "doesRequirePackAPunch";
	public static final String TYPE_JSON_TAG = "type";
	public static final String USAGE_SOUND_JSON_TAG = "usageSound";

	public static final WeaponServiceEntityJsonSerializer SERIALIZER = new WeaponServiceEntityJsonSerializer();
	//endregion

	//region Fields
	private boolean doesRequirePackAPunch;

	private int damage;

	private List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();

	private Sound usageSound;

	private String type;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the service's characteristics.
	 */
	public List<WeaponServiceCharacteristicEntity> getCharacteristics() {
		return new ArrayList<WeaponServiceCharacteristicEntity>(characteristics);
	}

	/**
	 * Gets the damage.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets the damage.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Gets whether Pack-A-Punch is required to use this service.
	 */
	public boolean doesRequirePackAPunch() {
		return doesRequirePackAPunch;
	}

	/**
	 * Sets whether Pack-A-Punch is required to use this service.
	 */
	public void setDoesRequirePackAPunch(boolean doesRequirePackAPunch) {
		this.doesRequirePackAPunch = doesRequirePackAPunch;
	}

	/**
	 * Gets this service's type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets this service's type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the usage sound.
	 */
	public Sound getUsageSound() {
		return usageSound;
	}

	/**
	 * Sets the usage sound.
	 */
	public void setUsageSound(Sound usageSound) {
		this.usageSound = usageSound;
	}
	//endregion

	//region Public Methods
	/**
	 * Adds a characteristic.
	 * @param characteristic The characteristic to add.
	 */
	public void addCharacteristic(WeaponServiceCharacteristicEntity characteristic) {
		characteristics.add(characteristic);
	}
	//endregion
}