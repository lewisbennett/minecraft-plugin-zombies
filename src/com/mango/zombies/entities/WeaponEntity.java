package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.schema.WeaponServices;

public class WeaponEntity
{
	public String id;
	public String name;
	public String weaponClass;
	public String item;
	public boolean isWonderWeapon;
	public WeaponServiceEntity[] services;
	
	public WeaponEntity(String id, String weaponName, String weaponClassName)
	{
		WeaponClassEntity weaponClass = null;
		
		for (WeaponClassEntity queryClass : PluginCore.gameplay.weaponClasses)
		{
			if (queryClass.name.equals(weaponClassName))
			{
				weaponClass = queryClass;
				break;
			}
		}
		
		this.id = id;
		name = weaponName;
		this.weaponClass = weaponClass.name;
		item = weaponClass.defaultItem;
		isWonderWeapon = false;
		services = weaponClass.defaultServices;
	}
	
	public boolean canPackAPunch()
	{
		for (WeaponServiceEntity service : services)
		{
			if (service.typeUUID == WeaponServices.PACK_A_PUNCH)
				return true;
		}
		
		return false;
	}
}