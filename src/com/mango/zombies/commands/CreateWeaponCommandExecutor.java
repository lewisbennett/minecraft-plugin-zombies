package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.services.MessagingService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateWeaponCommandExecutor extends BaseCommandExecutor {

	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createweapon [ID] [weapon class ID] [name]";
	public static final String WEAPON_ID_ALREADY_EXISTS_ERROR = "Weapon not created. %s already exists.";
	public static final String WEAPON_CLASS_DOES_NOT_EXIST_ERROR = "Weapon not created. %s is not a valid weapon class.";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 3) {
			MessagingService.showError(sender, CORRECT_USAGE_ERROR);
			return true;
		}
		
		if (!isValidWeaponId(args[0])) {
			MessagingService.showError(sender, String.format(WEAPON_ID_ALREADY_EXISTS_ERROR, args[0]));
			return true;
		}
		
		if (!isValidWeaponClassId(args[1])) {
			MessagingService.showError(sender, String.format(WEAPON_CLASS_DOES_NOT_EXIST_ERROR, args[1]));
			return true;
		}

		StringBuilder name = new StringBuilder();

		for (int i = 2; i < args.length; i++) {

			if (i > 2)
				name.append(" ");

			name.append(args[i]);
		}

		WeaponEntity weapon = new WeaponEntity(args[0], name.toString(), args[1]);
		PluginCore.getWeapons().add(weapon);
		MessagingService.showSuccess(sender, "Successfully created weapon: " + ChatColor.BOLD + weapon.getName());
		
		return true;
	}
	
	private boolean isValidWeaponId(String weaponId) {

		for (WeaponEntity weapon : PluginCore.getWeapons()) {

			if (weapon.getId().equalsIgnoreCase(weaponId))
				return false;
		}
		
		return true;
	}
	
	private boolean isValidWeaponClassId(String weaponClassId) {

		for (WeaponClassEntity weaponClass : PluginCore.getWeaponsClasses()) {

			if (weaponClass.getId().equalsIgnoreCase(weaponClassId))
				return true;
		}
		
		return false;
	}
}