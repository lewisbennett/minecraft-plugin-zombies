package com.mango.zombies.entities;

import java.util.UUID;

public class WeaponCharacteristicEntity
{
	public Object value;
	public UUID typeUUID;
	
	public WeaponCharacteristicEntity(Object value, UUID typeUUID)
	{
		this.value = value;
		this.typeUUID = typeUUID;
	}
}