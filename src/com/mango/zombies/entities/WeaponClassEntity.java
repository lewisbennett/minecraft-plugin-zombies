package com.mango.zombies.entities;

import com.mango.zombies.schema.WeaponCharacteristics;
import com.mango.zombies.schema.WeaponServices;
import com.mango.zombies.schema.WeaponTypes;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WeaponClassEntity
{
	public String name;
	public String defaultItem;
	public String color;
	public WeaponServiceEntity[] defaultServices;
	
	public WeaponClassEntity(String name, int cost, String type, boolean canPackAPunch)
	{
		this.name = name;
		defaultItem = Material.STICK.name();
		color = ChatColor.RED.name();
		
		// we use a list here as it's easier to modify than an array - converted at the end
		List<WeaponServiceEntity> services = new ArrayList<WeaponServiceEntity>();
		List<WeaponCharacteristicEntity> chars = new ArrayList<WeaponCharacteristicEntity>();
		
		if (type.equals(WeaponTypes.MELEE))
			services.add(new WeaponServiceEntity(1, WeaponServices.MELEE));
		else
		{	
			UUID typeUUID = null;
			
			// here we get the type UUID of the service we're adding and also setup any service specific characteristics
			switch (type)
			{
				case WeaponTypes.BUCK_SHOT:
					chars.add(new WeaponCharacteristicEntity(3, WeaponCharacteristics.PROJECTILES_IN_CATRIDGE));
					typeUUID = WeaponServices.BUCK_SHOT;
					break;
					
				case WeaponTypes.SINGLE_SHOT:
					typeUUID = WeaponServices.SINGLE_SHOT;
					break;
			}
			
			services.add(new WeaponServiceEntity(2, typeUUID));
			
			// these characteristics are the same across every non-melee weapon type so define them here
			chars.add(new WeaponCharacteristicEntity(40, WeaponCharacteristics.BULLET_CAPACITY));
			chars.add(new WeaponCharacteristicEntity(8, WeaponCharacteristics.MAGAZINE_SIZE));
			chars.add(new WeaponCharacteristicEntity(4, WeaponCharacteristics.RELOAD_SPEED));
			chars.add(new WeaponCharacteristicEntity(cost / 2, WeaponCharacteristics.AMMO_COST));
		}
		
		chars.add(new WeaponCharacteristicEntity(cost, WeaponCharacteristics.WEAPON_COST));
		services.get(0).characteristics = chars.toArray(new WeaponCharacteristicEntity[chars.size()]);
		
		// add the default pack-a-punch service if needed
		if (canPackAPunch)
		{
			WeaponServiceEntity upgraded = new WeaponServiceEntity(services.get(0).damage * 5, WeaponServices.PACK_A_PUNCH);
			WeaponCharacteristicEntity[] newChars = new WeaponCharacteristicEntity[services.get(0).characteristics.length + 1];
			
			// copy the characteristics from the normal service
			for (int i = 0; i < services.get(0).characteristics.length; i++)
				newChars[i] = services.get(0).characteristics[i];
			
			// the last characteristic is the name of the weapon upon upgrade. -1 to account for 0 index
			newChars[newChars.length - 1] = new WeaponCharacteristicEntity("Upgraded", WeaponCharacteristics.PACK_A_PUNCH_NAME);
			
			upgraded.characteristics = newChars;
			services.add(upgraded);
		}
		
		defaultServices = services.toArray(new WeaponServiceEntity[services.size()]);
	}
}