package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.*;
import com.mango.zombies.schema.MapItem;
import com.mango.zombies.schema.Sign;
import com.mango.zombies.schema.WeaponService;
import com.mango.zombies.schema.WeaponServiceCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangedListener implements Listener {

	//region Constant Values
	public static final String PERK_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid perk.";
	public static final String PERK_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid perk.";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid weapon.";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid weapon.";
	public static final String MAP_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid map.";
	public static final String MAP_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid map.";
	public static final String TYPE_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid sign type.";
	public static final String TYPE_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid sign type.";
	//endregion

	//region Event Handlers
	@EventHandler
	public void onSignChange(SignChangeEvent event) {

		String[] lines = event.getLines();
		
		if (!lines[0].equalsIgnoreCase(Sign.HEADER))
			return;

		String operation = lines[1] == null ? "" : lines[1];
		String mapId = lines[2] == null ? "" : lines[2];
		String paramter = lines[3] == null ? "" : lines[3];

		String lineOne = "";
		String lineTwo = "";
		String lineThree = "";

		String error = null;

		switch (operation) {

			case Sign.MYSTERY_BOX:
				lineOne = MapItem.MYSTERY_BOX;
				lineTwo = "Cost: " + (paramter.isEmpty() ? MapConfigEntity.DEFAULT_DEFAULT_MYSTERY_BOX_COST : Integer.parseInt(lines[3]));
				break;
				
			case Sign.PERK:

				PerkEntity perk = null;
				
				for (PerkEntity queryPerk : PluginCore.getPerks()) {

					if (queryPerk.getId().equalsIgnoreCase(paramter)) {
						perk = queryPerk;
						break;
					}
				}
				
				if (perk == null) {
					error = paramter.isEmpty() ? PERK_DOES_NOT_EXIST_ERROR_GENERIC : String.format(PERK_DOES_NOT_EXIST_ERROR, paramter);
					return;
				}

				lineOne = perk.getName();
				lineTwo = "Cost: " + perk.getCost();

				break;

			case Sign.WEAPON:

				WeaponEntity weapon = null;
				
				for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

					if (queryWeapon.getId().equalsIgnoreCase(paramter)) {
						weapon = queryWeapon;
						break;
					}
				}
				
				if (weapon == null) {
					error = paramter.isEmpty() ? WEAPON_DOES_NOT_EXIST_ERROR_GENERIC : String.format(WEAPON_DOES_NOT_EXIST_ERROR, paramter);
					return;
				}

				lineOne = weapon.getName();
				lineTwo = "Cost: " + weapon.getCost();

				WeaponServiceEntity gunshotService = null;
				WeaponServiceEntity packAPunchedGunshotService = null;

				for (WeaponServiceEntity service : weapon.getServices()) {

					if (!service.getType().equals(WeaponService.GUNSHOT))
						continue;

					if (service.doesRequirePackAPunch())
						packAPunchedGunshotService = service;
					else
						gunshotService = service;
				}

				if (gunshotService == null && packAPunchedGunshotService == null)
					break;

				int normalAmmoCost = -1;
				int packAPunchedAmmoCost = -1;

				for (WeaponServiceCharacteristicEntity characteristic : gunshotService.getCharacteristics()) {

					if (characteristic.getType().equals(WeaponServiceCharacteristic.AMMO_COST)) {
						normalAmmoCost = (Integer)characteristic.getValue();
						break;
					}
				}

				if (packAPunchedGunshotService != null) {

					for (WeaponServiceCharacteristicEntity characteristic : packAPunchedGunshotService.getCharacteristics()) {

						if (characteristic.getType().equals(WeaponServiceCharacteristic.AMMO_COST)) {
							packAPunchedAmmoCost = (Integer)characteristic.getValue();
							break;
						}
					}
				}

				if (normalAmmoCost < 1 && packAPunchedAmmoCost < 1)
					break;

				lineThree = "Ammo: ";

				if (normalAmmoCost < 1)
					lineThree += "X";
				else
					lineThree += normalAmmoCost;

				lineThree += "/";

				if (packAPunchedAmmoCost < 1)
					lineThree += "X";
				else
					lineThree += packAPunchedAmmoCost;

				break;
				
			default:
				error = lines[1] == null || lines[1].isEmpty() ? TYPE_DOES_NOT_EXIST_ERROR_GENERIC : String.format(TYPE_DOES_NOT_EXIST_ERROR, lines[1]);
				break;
		}

		if (error != null) {
			PluginCore.getMessagingService().error(event.getPlayer(), "Sign not created. " + error);
			return;
		}
		
		MapEntity map = null;
		
		for (MapEntity queryMap : PluginCore.getMaps()) {

			if (queryMap.getId().equalsIgnoreCase(mapId)) {
				map = queryMap;
				break;
			}
		}
		
		if (map == null) {
			PluginCore.getMessagingService().error(event.getPlayer(), mapId.isEmpty() ? MAP_DOES_NOT_EXIST_ERROR_GENERIC : String.format(MAP_DOES_NOT_EXIST_ERROR, mapId));
			return;
		}
		
		event.setLine(0, ChatColor.RED + Sign.HEADER);
		event.setLine(1, ChatColor.AQUA + lineOne);
		event.setLine(2, ChatColor.BLACK + lineTwo);
		event.setLine(3, ChatColor.BLACK + lineThree);
	}
	//endregion
}