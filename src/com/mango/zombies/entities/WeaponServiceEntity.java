package com.mango.zombies.entities;

import com.mango.zombies.serializers.WeaponServiceEntityJsonSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeaponServiceEntity {

	//region Constant Values
	public static final String CHARACTERISTICS_JSON_TAG = "characteristics";
	public static final String DAMAGE_JSON_TAG = "damage";
	public static final String DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG = "does_require_pack_a_punch";
	public static final WeaponServiceEntityJsonSerializer SERIALIZER = new WeaponServiceEntityJsonSerializer();
	public static final String TYPE_UUID_JSON_TAG = "type_uuid";
	//endregion

	//region Fields
	private List<WeaponServiceCharacteristicEntity> characteristics = new ArrayList<WeaponServiceCharacteristicEntity>();
	private int damage;
	private boolean doesRequirePackAPunch;
	private UUID typeUUID;
	//endregion

	//region Getters/Setters
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
	//endregion

	//region Constructors
	public WeaponServiceEntity() {
	}

	public WeaponServiceEntity(int damage, boolean doesRequirePackAPunch, UUID typeUUID) {

		this.damage = damage;
		this.doesRequirePackAPunch = doesRequirePackAPunch;
		this.typeUUID = typeUUID;
	}
	//endregion
}