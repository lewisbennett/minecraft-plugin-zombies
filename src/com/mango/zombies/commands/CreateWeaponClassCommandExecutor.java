package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.schema.WeaponTypes;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreateWeaponClassCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /createweaponclass [name] [default cost] [weapon type (" + String.join(", ", WeaponTypes.toArray()) + ")] [can Pack-A-Punch? true/false]";
	public static final String ClassAlreadyExistsError = "A weapon class with this name already exists";
	public static final String TypeDoesNotExistError = "The specified weapon type does not exist";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 4 || !isValidBoolean(args[3]))
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		if (!isValidClassName(args[0]))
		{
			CustomMessaging.showError(sender, ClassAlreadyExistsError);
			return true;
		}
		
		if(!isValidWeaponType(args[2]))
		{
			CustomMessaging.showError(sender, TypeDoesNotExistError);
			return true;
		}
		
		PluginCore.gameplay.weaponClasses.add(new WeaponClassEntity(args[0], Integer.parseInt(args[1]), args[2], Boolean.parseBoolean(args[3])));
		CustomMessaging.showSuccess(sender, "Successfully created weapon class: " + ChatColor.BOLD + args[0]);
		
		return true;
	}
	
	private boolean isValidClassName(String className)
	{
		for (WeaponClassEntity weaponClass : PluginCore.gameplay.weaponClasses)
		{
			if (weaponClass.name.equals(className))
				return false;
		}
		
		return true;
	}
	
	private boolean isValidWeaponType(String weaponType)
	{
		for (String type : WeaponTypes.toArray())
		{
			if (type.equals(weaponType))
				return true;
		}
		
		return false;
	}
	
	private boolean isValidBoolean(String bool)
	{
		if (bool.toLowerCase().equals("true") || bool.toLowerCase().equals("false"))
			return true;
		else
			return false;
	}
}