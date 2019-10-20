package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeaponServiceEntity {

	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DAMAGE_JSON_TAG = "damage";
	public static final WeaponServiceEntityJsonSerializer SERIALIZER = new WeaponServiceEntityJsonSerializer();
	public static final String TYPE_UUID_JSON_TAG = "type_uuid";

	private List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();
	private int damage;
	private UUID typeUUID;

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

	public WeaponServiceEntity() {
	}

	public WeaponServiceEntity(int damage, UUID typeUUID) {
		this.damage = damage;
		this.typeUUID = typeUUID;
	}
}