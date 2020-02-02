package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceEntityJsonSerializer;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class WeaponServiceEntity {

	//region Constant Values
	public static final int DEFAULT_ACCURACY = 85;
	public static final Sound DEFAULT_USAGE_SOUND = Sound.BLOCK_PUMPKIN_CARVE;

	public static final WeaponServiceEntityJsonSerializer SERIALIZER = new WeaponServiceEntityJsonSerializer();

	public static final String ACCURACY_JSON_TAG = "accuracy";
	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DAMAGE_JSON_TAG = "damage";
	public static final String DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG = "does_require_pack_a_punch";
	public static final String TYPE_JSON_TAG = "type";
	public static final String USAGE_SOUND_JSON_TAG = "usage_sound";
	//endregion

	//region Fields
	private int accuracy = DEFAULT_ACCURACY, damage;
	private List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();
	private boolean doesRequirePackAPunch;
	private String type;
	private Sound usageSound = DEFAULT_USAGE_SOUND;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the accuracy of this service.
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * Sets the accuracy. Only values between 1 and 100 are accepted.
	 */
	public void setAccuracy(int accuracy) {

		if (accuracy > 0 && accuracy <= 100)
			this.accuracy = accuracy;
	}

	/**
	 * Gets the service's characteristics.
	 */
	public List<WeaponServiceCharacteristicEntity> getCharacteristics() {
		return characteristics;
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
}