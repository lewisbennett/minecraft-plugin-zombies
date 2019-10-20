package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.*;
import com.mango.zombies.helper.Messaging;
import com.mango.zombies.schema.MapItem;
import com.mango.zombies.schema.Sign;
import com.mango.zombies.schema.WeaponCharacteristic;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class BlockEvents implements Listener {

	public static final String PERK_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid perk.";
	public static final String PERK_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid perk.";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid weapon.";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid weapon.";
	public static final String MAP_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid map.";
	public static final String MAP_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid map.";
	public static final String TYPE_DOES_NOT_EXIST_ERROR = "\"%s\" is not a valid sign type.";
	public static final String TYPE_DOES_NOT_EXIST_ERROR_GENERIC = "Invalid sign type.";

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
				lineTwo = "Cost: " + (paramter.isEmpty() ? ConfigEntity.DEFAULT_DEFAULT_MYSTERY_BOX_COST : Integer.parseInt(lines[3]));
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

				WeaponServiceEntity standardService = null;
				WeaponServiceEntity packAPunchService = null;

				for (WeaponServiceEntity service : weapon.getServices()) {

					if (service.getTypeUUID().equals(WeaponService.PACK_A_PUNCH))
						packAPunchService = service;
					else
						standardService = service;
				}

				for (WeaponServiceCharacteristicEntity characteristic : standardService.getCharacteristics()) {

					if (characteristic.getTypeUUID().equals(WeaponCharacteristic.WEAPON_COST))
						lineTwo = "Cost: " + characteristic.getValue();

					if (characteristic.getTypeUUID().equals(WeaponCharacteristic.AMMO_COST))
						lineThree = "Ammo: " + characteristic.getValue();
				}

				if (packAPunchService != null) {

					for (WeaponServiceCharacteristicEntity characteristic : packAPunchService.getCharacteristics()) {

						if (characteristic.getTypeUUID().equals(WeaponCharacteristic.AMMO_COST))
							lineThree += "/" + characteristic.getValue();
					}
				}

				break;
				
			default:
				error = lines[1] == null || lines[1].isEmpty() ? TYPE_DOES_NOT_EXIST_ERROR_GENERIC : String.format(TYPE_DOES_NOT_EXIST_ERROR, lines[1]);
				return;
		}

		if (error != null) {
			Messaging.showError(event.getPlayer(), "Sign not created. " + error);
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
			Messaging.showError(event.getPlayer(), mapId.isEmpty() ? MAP_DOES_NOT_EXIST_ERROR_GENERIC : String.format(MAP_DOES_NOT_EXIST_ERROR, mapId));
			return;
		}
		
		event.setLine(0, ChatColor.RED + Sign.HEADER);
		event.setLine(1, ChatColor.AQUA + lineOne);
		event.setLine(2, ChatColor.BLACK + lineTwo);
		event.setLine(3, ChatColor.BLACK + lineThree);
	}
}