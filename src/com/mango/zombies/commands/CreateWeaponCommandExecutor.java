package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.schema.WeaponClasses;

public class CreateWeaponCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /createweapon [weapon name] [weapon class (" + String.join(", ", WeaponClasses.getWeaponClasses()) + ")]";
	public static final String AlreadyExistsError = "This weapon already exists";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 2 || !WeaponClasses.isValidWeaponClass(args[1]))
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		for (WeaponEntity weapon : PluginCore.gameplay.weapons)
		{
			if (weapon.name.equals(args[0]))
			{
				CustomMessaging.showError(sender, AlreadyExistsError);
				return true;
			}
		}
		
		PluginCore.gameplay.weapons.add(new WeaponEntity(args[0], args[1]));
		CustomMessaging.showSuccess(sender, "Successfully created weapon: " + ChatColor.BOLD + args[0]);
		
		return true;
	}
}