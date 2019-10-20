package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceCharacteristicEntityJsonSerializer;

import java.util.UUID;

public class WeaponServiceCharacteristicEntity {

	public static final WeaponServiceCharacteristicEntityJsonSerializer SERIALIZER = new WeaponServiceCharacteristicEntityJsonSerializer();
	public static final String TYPE_UUID_JSON_TAG = "type_uuid";
	public static final String VALUE_JSON_TAG = "value";

	private UUID typeUUID;
	private Object value;

	/**
	 * Gets the characteristic's type UUID.
	 */
	public UUID getTypeUUID() {
		return typeUUID;
	}

	/**
	 * Sets the characteristic's UUID.
	 */
	public void setTypeUUID(UUID typeUUID) {
		this.typeUUID = typeUUID;
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

	public WeaponServiceCharacteristicEntity() {
	}

	public WeaponServiceCharacteristicEntity(Object value, UUID typeUUID) {
		this.value = value;
		this.typeUUID = typeUUID;
	}
}