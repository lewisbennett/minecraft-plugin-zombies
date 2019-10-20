package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.services.MessagingService;
import com.mango.zombies.schema.WeaponType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreateWeaponClassCommandExecutor extends BaseCommandExecutor {

	public static final String CAN_PACK_A_PUNCH_SYNTAX_ERROR = "Weapon class not created. You need to enter \"yes\" or \"no\" for whether this weapon class can be Pack-A-Punched.";
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createweaponclass [ID] [default cost] [weapon type] [can Pack-A-Punch yes/no] [name]";
	public static final String INVALID_COST = "Weapon class not created. You need to enter a valid cost.";
	public static final String WEAPON_CLASS_ID_ALREADY_EXISTS_ERROR = "Weapon class not created. %s already exists.";
	public static final String WEAPON_TYPE_DOES_NOT_EXIST_ERROR = "Weapon class not created. %s is not a valid weapon type.";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 5) {
			MessagingService.showError(sender, CORRECT_USAGE_ERROR);
			return true;
		}
		
		if (!isValidWeaponClassId(args[0])) {
			MessagingService.showError(sender, String.format(WEAPON_CLASS_ID_ALREADY_EXISTS_ERROR, args[0]));
			return true;
		}
		
		if (!args[2].equalsIgnoreCase(WeaponType.GUNSHOT) && !args[2].equalsIgnoreCase(WeaponType.MELEE)) {
			MessagingService.showError(sender, String.format(WEAPON_TYPE_DOES_NOT_EXIST_ERROR, args[2]));
			return true;
		}

		Integer cost;

		try {
			cost = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			MessagingService.showError(sender, INVALID_COST);
			return true;
		}

		boolean canPackAPunch;

		if (args[3].equalsIgnoreCase("yes"))
			canPackAPunch = true;
		else if (args[3].equalsIgnoreCase("no"))
			canPackAPunch = false;
		else {
			MessagingService.showError(sender, CAN_PACK_A_PUNCH_SYNTAX_ERROR);
			return true;
		}

		StringBuilder name = new StringBuilder();

		for (int i = 4; i < args.length; i++) {

			if (i > 4)
				name.append(" ");

			name.append(args[i]);
		}

		WeaponClassEntity weaponClass = new WeaponClassEntity(args[0], name.toString(), cost, args[2], canPackAPunch);
		PluginCore.getWeaponsClasses().add(weaponClass);
		MessagingService.showSuccess(sender, "Successfully created weapon class: " + ChatColor.BOLD + weaponClass.getName());
		
		return true;
	}

	private boolean isValidWeaponClassId(String weaponClassId) {

		for (WeaponClassEntity weaponClass : PluginCore.getWeaponsClasses()) {

			if (weaponClass.getId().equalsIgnoreCase(weaponClassId))
				return false;
		}

		return true;
	}
}