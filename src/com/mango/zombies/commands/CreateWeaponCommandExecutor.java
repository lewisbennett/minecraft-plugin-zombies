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
	public static final String CorrectUsageError = "Correct usage: /createweapon [ID] [name] [weapon class]";
	public static final String WeaponIdAlreadyExistsError = "A weapon with this ID already exists";
	public static final String WeaponNameAlreadyExistsError = "A weapon with this name already exists";
	public static final String ClassDoesNotExistError = "The specified weapon class does not exist";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 3)
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		if (!isValidWeaponId(args[0]))
		{
			CustomMessaging.showError(sender, WeaponIdAlreadyExistsError);
			return true;
		}
		
		if (!isValidWeaponName(args[1]))
		{
			CustomMessaging.showError(sender, WeaponNameAlreadyExistsError);
			return true;
		}
		
		if (!isValidWeaponClass(args[2]))
		{
			CustomMessaging.showError(sender, ClassDoesNotExistError);
			return true;
		}
		
		WeaponEntity weapon = new WeaponEntity(args[0], args[1], args[2]);
		PluginCore.gameplay.weapons.add(weapon);
		CustomMessaging.showSuccess(sender, "Successfully created weapon: " + ChatColor.BOLD + weapon.name);
		
		return true;
	}
	
	private boolean isValidWeaponId(String weaponId)
	{
		for (WeaponEntity weapon : PluginCore.gameplay.weapons)
		{
			if (weapon.id.equals(weaponId))
				return false;
		}
		
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