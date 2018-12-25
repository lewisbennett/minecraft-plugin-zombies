package com.mango.zombies.schema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;

import com.mango.zombies.entities.WeaponCharacteristicEntity;
import com.mango.zombies.entities.WeaponServiceEntity;

public class WeaponClasses
{
	public static final String pistols = "pistols";
	public static final String subMachineGuns = "sub_machine_guns";
	public static final String assaultRifles = "assault_rifles";
	public static final String lightMachineGuns = "light_machine_guns";
	public static final String shotguns = "shotguns";
	public static final String sniperRifles = "sniper_rifles";
	public static final String knives = "knives";
	public static final String special = "special";
	
	public static String[] getWeaponClasses()
	{
		return new String[]
		{
			pistols,
			subMachineGuns,
			assaultRifles,
			lightMachineGuns,
			shotguns,
			sniperRifles,
			knives,
			special
		};
	}
	
	public static boolean isValidWeaponClass(String weaponClass)
	{
		return weaponClass.equals(pistols)
			|| weaponClass.equals(shotguns)
			|| weaponClass.equals(subMachineGuns)
			|| weaponClass.equals(assaultRifles)
			|| weaponClass.equals(lightMachineGuns)
			|| weaponClass.equals(sniperRifles)
			|| weaponClass.equals(knives)
			|| weaponClass.equals(special);
	}
	
	public static String getDefaultItemForWeaponClass(String weaponClass)
	{
		switch (weaponClass)
		{
			case pistols:
				return Material.FEATHER.name();
				
			case subMachineGuns:
				return Material.STICK.name();
				
			case assaultRifles:
				return Material.ARROW.name();
				
			case lightMachineGuns:
				return Material.IRON_INGOT.name();
			
			case shotguns:
				return Material.BONE.name();
				
			case sniperRifles:
				return Material.WHEAT.name();
				
			case knives:
				return Material.IRON_SWORD.name();
				
			case special:
				return Material.BLAZE_ROD.name();
		
			default:
				return Material.FEATHER.name();
		}
	}
	
	public static WeaponServiceEntity[] getDefaultServicesForWeaponClass(String weaponClass)
	{
		boolean canPackAPunch = true;
		int damage = 0;
		UUID typeUUID = WeaponServices.singleShot;
		
		int magazineSize = -1;
		int bulletCapacity = -1;
		int reloadSpeed = -1;
		int projectilesInCartridge = -1;
		
		switch (weaponClass)
		{
			case pistols:
				damage = 2;
				
				magazineSize = 8;
				bulletCapacity = 40;
				reloadSpeed = 4;
				break;
				
			case subMachineGuns:
				damage = 3;
				
				magazineSize = 20;
				bulletCapacity = 100;
				reloadSpeed = 4;
				break;
				
			case assaultRifles:
				damage = 5;
				
				magazineSize = 30;
				bulletCapacity = 120;
				reloadSpeed = 6;
				break;
				
			case lightMachineGuns:
				damage = 8;
				
				magazineSize = 100;
				bulletCapacity = 300;
				reloadSpeed = 9;
				break;
			
			case shotguns:
				damage = 6;
				typeUUID = WeaponServices.buckShot;
				
				magazineSize = 6;
				bulletCapacity = 36;
				reloadSpeed = 5;
				break;
				
			case sniperRifles:
				damage = 10;
				
				magazineSize = 6;
				bulletCapacity = 30;
				reloadSpeed = 7;
				break;
				
			case knives:
				damage = 50;
				typeUUID = WeaponServices.melee;
				break;
				
			case special:
				damage = 12;
				
				magazineSize = 10;
				bulletCapacity = 80;
				reloadSpeed = 4;
				break;
		
			default:
				damage = 2;
				
				magazineSize = 8;
				bulletCapacity = 40;
				reloadSpeed = 4;
				break;
		}
		
		WeaponServiceEntity standardValues = new WeaponServiceEntity(damage, typeUUID);
		
		if (magazineSize != -1 || bulletCapacity != -1 || reloadSpeed != -1 || projectilesInCartridge != -1)
		{
			List<WeaponCharacteristicEntity> characteristics = new ArrayList<WeaponCharacteristicEntity>();
			
			if (magazineSize != -1)
				characteristics.add(new WeaponCharacteristicEntity(magazineSize, WeaponCharacteristics.magazineSize));
			
			if (bulletCapacity != -1)
				characteristics.add(new WeaponCharacteristicEntity(bulletCapacity, WeaponCharacteristics.bulletCapacity));
			
			if (reloadSpeed != -1)
				characteristics.add(new WeaponCharacteristicEntity(reloadSpeed, WeaponCharacteristics.reloadSpeed));
			
			if (projectilesInCartridge != -1)
				characteristics.add(new WeaponCharacteristicEntity(projectilesInCartridge, WeaponCharacteristics.projectilesInCatridge));
			
			standardValues.characteristics = characteristics.toArray(new WeaponCharacteristicEntity[characteristics.size()]);
		}
		
		if (canPackAPunch)
		{
			WeaponServiceEntity packAPunchValues = new WeaponServiceEntity(standardValues.damage * 5, WeaponServices.packAPunch);
			packAPunchValues.characteristics = standardValues.characteristics == null ? new WeaponCharacteristicEntity[0] : standardValues.characteristics;
			return new WeaponServiceEntity[] { standardValues, packAPunchValues };
		}
		else
			return new WeaponServiceEntity[] { standardValues };
	}
}