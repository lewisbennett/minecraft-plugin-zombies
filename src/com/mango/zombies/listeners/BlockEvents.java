package com.mango.zombies.listeners;


import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.*;
import com.mango.zombies.gameplay.Gameplay;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.schema.MapItems;
import com.mango.zombies.schema.Signs;
import com.mango.zombies.schema.WeaponCharacteristics;
import com.mango.zombies.schema.WeaponServices;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class BlockEvents implements Listener {

	public static final String PERK_DOES_NOT_EXIST_ERROR = "%s is not a valid perk";
	public static final String PERK_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid perk";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR = "%s is not a valid weapon";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid weapon";
	public static final String MAP_DOES_NOT_EXIST_ERROR = "%s is not a valid map";
	public static final String MAP_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid map";
	public static final String TYPE_DOES_NOT_EXIST_ERROR = "%s is not a valid sign type";
	public static final String TYPE_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid sign type";

	@EventHandler
	public void onSignChange(SignChangeEvent event) {

		String[] lines = event.getLines();
		
		if (!lines[0].equalsIgnoreCase(Signs.HEADER))
			return;
		
		String operation = null;
		String desc = null;

		String error = null;

		switch (lines[1]) {
			case Signs.MYSTERY_BOX:
				operation = MapItems.MYSTERY_BOX;
				desc = "Cost: " + (lines[3] == null || lines[3].isEmpty() ? Integer.toString(Gameplay.DEFAULT_MYSTERY_BOX_COST) : lines[3]);
				break;
				
			case Signs.PERK:
				PerkEntity perk = null;
				
				for (PerkEntity queryPerk : PluginCore.gameplay.perks) {

					if (queryPerk.id.equals(lines[3])) {
						perk = queryPerk;
						break;
					}
				}
				
				if (perk == null) {
					error = lines[1] == null || lines[1].isEmpty() ? PERK_DOES_NOT_EXIST_ERROR_GENERIC : String.format(PERK_DOES_NOT_EXIST_ERROR, lines[1]);
					return;
				}

				operation = perk.name;
				desc = "Cost: " + Integer.toString(perk.cost);

				break;
				
			case Signs.WEAPON:
				WeaponEntity weapon = null;
				
				for (WeaponEntity queryWeapon : PluginCore.gameplay.weapons) {

					if (queryWeapon.id.equals(lines[3])) {
						weapon = queryWeapon;
						break;
					}
				}
				
				if (weapon == null) {
					CustomMessaging.showError(event.getPlayer(), WEAPON_DOES_NOT_EXIST_ERROR);
					return;
				}

				operation = weapon.name;

				// gets the characteristic containing the cost value and appends the desc value
				for (WeaponServiceEntity service : weapon.services) {

					if (service.typeUUID.equals(WeaponServices.PACK_A_PUNCH))
						continue;

					for (WeaponCharacteristicEntity characteristic : service.characteristics) {

						if (characteristic.typeUUID.equals(WeaponCharacteristics.WEAPON_COST)) {
							desc = "Cost: " + Double.toString((double)characteristic.value);
							break;
						}
					}
				}

				break;
				
			default:
				error = lines[1] == null || lines[1].isEmpty() ? TYPE_DOES_NOT_EXIST_ERROR_GENERIC : String.format(TYPE_DOES_NOT_EXIST_ERROR, lines[1]);
				return;
		}

		if (error != null) {
			CustomMessaging.showError(event.getPlayer(), error);
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
			CustomMessaging.showError(event.getPlayer(), MAP_DOES_NOT_EXIST_ERROR);
			return;
		}
		
		event.setLine(0, ChatColor.RED + Signs.HEADER);
		event.setLine(1, "");
		event.setLine(2, ChatColor.AQUA + operation);
		event.setLine(3, ChatColor.GREEN + desc);
	}
}