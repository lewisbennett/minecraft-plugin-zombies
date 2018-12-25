package com.mango.zombies.entities;

import java.util.UUID;

public class WeaponServiceEntity
{
	public int damage;
	public UUID typeUUID;
	public WeaponCharacteristicEntity[] characteristics;
	
	public WeaponServiceEntity(int damage, UUID typeUUID)
	{
		this.damage = damage;
		this.typeUUID = typeUUID;
	}
}