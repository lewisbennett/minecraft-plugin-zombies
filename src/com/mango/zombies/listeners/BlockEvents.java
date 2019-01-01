package com.mango.zombies.listeners;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.PerkEntity;
import com.mango.zombies.entities.WeaponCharacteristicEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.entities.WeaponServiceEntity;
import com.mango.zombies.gameplay.Gameplay;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.schema.MapItems;
import com.mango.zombies.schema.Signs;
import com.mango.zombies.schema.WeaponCharacteristics;
import com.mango.zombies.schema.WeaponServices;

public class BlockEvents implements Listener
{
	// errors specific to these handlers
	public static final String PerkDoesNotExistError = "The specified perk does not exist";
	public static final String WeaponDoesNotExistError = "The specified weapon does not exist";
	public static final String MapDoesNotExistError = "The specified map does not exist";
	public static final String TypeDoesNotExistError = "The specified type does not exist";
	
	@EventHandler
	public void onSignChange(SignChangeEvent event)
	{
		String[] lines = event.getLines();
		
		if (!lines[0].equalsIgnoreCase(Signs.header))
			return;
		
		String operation = null;
		String desc = null;
		
		switch (lines[1])
		{
			case Signs.mysteryBox:
				operation = MapItems.mysteryBox;
				desc = "Cost: " + (lines[3].trim().equals("") ? Integer.toString(Gameplay.defaultMysteryBoxCost) : lines[3]);
				break;
				
			case Signs.perk:
				PerkEntity perk = null;
				
				for (PerkEntity queryPerk : PluginCore.gameplay.perks)
				{
					if (queryPerk.id.equals(lines[3]))
					{
						perk = queryPerk;
						break;
					}
				}
				
				if (perk == null)
				{
					CustomMessaging.showError(event.getPlayer(), PerkDoesNotExistError);
					return;
				}
				else
				{
					operation = perk.name;
					desc = "Cost: " + Integer.toString(perk.cost);
				}
				break;
				
			case Signs.weapon:
				WeaponEntity weapon = null;
				
				for (WeaponEntity queryWeapon : PluginCore.gameplay.weapons)
				{
					if (queryWeapon.id.equals(lines[3]))
					{
						weapon = queryWeapon;
						break;
					}
				}
				
				if (weapon == null)
				{
					CustomMessaging.showError(event.getPlayer(), WeaponDoesNotExistError);
					return;
				}
				else
				{
					operation = weapon.name;

					// gets the characteristic containing the cost value and appends the desc value
					for (WeaponServiceEntity service : weapon.services)
					{
						if (!service.typeUUID.equals(WeaponServices.packAPunch))
						{
							for (WeaponCharacteristicEntity characteristic : service.characteristics)
							{
								if (characteristic.typeUUID.equals(WeaponCharacteristics.weaponCost))
								{
									desc = "Cost: " + Double.toString((double)characteristic.value);
									break;
								}
							}
							
							if (desc != null)
								break;
						}
					}
				}
				break;
				
			default:
				CustomMessaging.showError(event.getPlayer(), TypeDoesNotExistError);
				return;
		}
		
		MapEntity map = null;
		
		for (MapEntity queryMap : PluginCore.gameplay.maps)
		{
			if (queryMap.id.equalsIgnoreCase(lines[2]))
			{
				map = queryMap;
				break;
			}
		}
		
		if (map == null)
		{
			CustomMessaging.showError(event.getPlayer(), MapDoesNotExistError);
			return;
		}
		
		event.setLine(0, ChatColor.RED + Signs.header);
		event.setLine(1, "");
		event.setLine(2, ChatColor.AQUA + operation);
		event.setLine(3, ChatColor.GREEN + desc);
	}
}