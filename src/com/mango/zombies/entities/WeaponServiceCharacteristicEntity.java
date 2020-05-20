package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

public class WeaponServiceCharacteristicEntity {

	//region Fields
	@Expose private Object value;

	@Expose private String type;
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