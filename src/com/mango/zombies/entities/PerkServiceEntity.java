package com.mango.zombies.entities;

import com.mango.zombies.serializers.PerkServiceEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PerkServiceEntity {

	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DESCRIPTION_JSON_TAG = "description";
	public static final PerkServiceEntityJsonSerializer SERIALIZER = new PerkServiceEntityJsonSerializer();
	public static final String TYPE_UUID_JSON_TAG = "type_uuid";

	private List<PerkServiceCharacteristicEntity> characteristics = new ArrayList<PerkServiceCharacteristicEntity>();
	private String description;
	private UUID typeUUID;

	/**
	 * Gets the service's characteristics.
	 */
	public List<PerkServiceCharacteristicEntity> getCharacteristics() {
		return characteristics;
	}

	/**
	 * Gets the service's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the service's description.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the service's type UUID.
	 */
	public UUID getTypeUUID() {
		return typeUUID;
	}

	/**
	 * Sets the service's type UUID.
	 */
	public void setTypeUUID(UUID typeUUID) {
		this.typeUUID = typeUUID;
	}
}