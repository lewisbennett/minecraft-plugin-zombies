package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class WeaponServiceEntity {

	//region Fields
	@Expose private boolean doesRequirePackAPunch;

	@Expose private int damage;

	@Expose private final List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();

	@Expose private Sound usageSound;

	@Expose private String type;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the service's characteristics.
	 */
	public WeaponServiceCharacteristicEntity[] getCharacteristics() {
		return characteristics.toArray(new WeaponServiceCharacteristicEntity[0]);
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