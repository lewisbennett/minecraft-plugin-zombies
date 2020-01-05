package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class WeaponServiceEntity {

	//region Constant Values
	public int DEFAULT_ACCURACY = 85;

	public static final WeaponServiceEntityJsonSerializer SERIALIZER = new WeaponServiceEntityJsonSerializer();

	public static final String ACCURACY_JSON_TAG = "accuracy";
	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DAMAGE_JSON_TAG = "damage";
	public static final String DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG = "does_require_pack_a_punch";
	public static final String TYPE_JSON_TAG = "type";
	//endregion

	//region Fields
	private int accuracy = DEFAULT_ACCURACY;
	private List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();
	private int damage;
	private boolean doesRequirePackAPunch;
	private String type;
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
	//endregion

	//region Constructors
	public WeaponServiceEntity() {
	}

	public WeaponServiceEntity(int damage, boolean doesRequirePackAPunch, String type) {

		this.damage = damage;
		this.doesRequirePackAPunch = doesRequirePackAPunch;
		this.type = type;
	}
	//endregion
}