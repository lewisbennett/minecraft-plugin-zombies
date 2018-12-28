package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.CustomMessaging;

public class CreateWeaponCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /createweapon [weapon name] [weapon class]";
	public static final String WeaponAlreadyExistsError = "This weapon already exists";
	public static final String ClassDoesNotExistError = "This weapon class does not exist";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 2)
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		if (!isValidWeaponName(args[0]))
		{
			CustomMessaging.showError(sender, WeaponAlreadyExistsError);
			return true;
		}
		
		if (!isValidWeaponClass(args[1]))
		{
			CustomMessaging.showError(sender, ClassDoesNotExistError);
			return true;
		}
		
		PluginCore.gameplay.weapons.add(new WeaponEntity(args[0], args[1]));
		CustomMessaging.showSuccess(sender, "Successfully created weapon: " + ChatColor.BOLD + args[0]);
		
		return true;
	}
	
	private boolean isValidWeaponName(String weaponName)
	{
		for (WeaponEntity weapon : PluginCore.gameplay.weapons)
		{
			if (weapon.name.equals(weaponName))
				return false;
		}
		
		return true;
	}
	
	private boolean isValidWeaponClass(String weaponClassName)
	{
		for (WeaponClassEntity weaponClass : PluginCore.gameplay.weaponClasses)
		{
			if (weaponClass.name.equals(weaponClassName))
				return true;
		}
		
		return false;
	}
}