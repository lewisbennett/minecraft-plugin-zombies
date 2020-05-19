package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceCharacteristicEntityJsonSerializer;

public class WeaponServiceCharacteristicEntity {

	//region Constant Values
	public static final String TYPE_JSON_TAG = "type";
	public static final String VALUE_JSON_TAG = "value";

	public static final WeaponServiceCharacteristicEntityJsonSerializer SERIALIZER = new WeaponServiceCharacteristicEntityJsonSerializer();
	//endregion

	//region Fields
	private Object value;

	private String type;
	//endregion

	//region Getters/Setters
	/**
	 * Gets this characteristic's type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets this characteristic's type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the characteristic's value.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the characteristic's value.
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	//endregion
}