package com.mango.zombies.entities;

import com.mango.zombies.schema.WeaponClasses;
import com.mango.zombies.schema.WeaponServices;

public class WeaponEntity
{
	public String name;
	public String weaponClass;
	public String item;
	public boolean isWonderWeapon;
	public WeaponServiceEntity[] services;
	
	public WeaponEntity(String weaponName, String weaponClass)
	{
		name = weaponName;
		this.weaponClass = weaponClass;
		item = WeaponClasses.getDefaultItemForWeaponClass(weaponClass);
		isWonderWeapon = weaponClass.equals(WeaponClasses.special);
		services = WeaponClasses.getDefaultServicesForWeaponClass(weaponClass);
	}
	
	public boolean canPackAPunch()
	{
		for (WeaponServiceEntity service : services)
		{
			if (service.typeUUID == WeaponServices.packAPunch)
				return true;
		}
		
		return false;
	}
}