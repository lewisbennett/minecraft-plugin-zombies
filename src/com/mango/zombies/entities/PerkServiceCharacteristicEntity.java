package com.mango.zombies.entities;

import com.mango.zombies.serializers.PerkServiceCharacteristicEntityJsonSerializer;

import java.util.UUID;

public class PerkServiceCharacteristicEntity {

	public static final String VALUE_JSON_TAG = "value";
	public static final PerkServiceCharacteristicEntityJsonSerializer SERIALIZER = new PerkServiceCharacteristicEntityJsonSerializer();
	public static final String TYPE_UUID_JSON_TAG = "type_uuid";

	private Object value;
	private UUID typeUUID;

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

	/**
	 * Gets the characteristic's type UUID.
	 */
	public UUID getTypeUUID() {
		return typeUUID;
	}

	/**
	 * Sets the characteristic's type UUID.
	 */
	public void setTypeUUID(UUID typeUUID) {
		this.typeUUID = typeUUID;
	}
}