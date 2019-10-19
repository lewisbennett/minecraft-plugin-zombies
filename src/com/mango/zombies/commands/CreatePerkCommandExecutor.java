package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.PerkEntity;
import com.mango.zombies.helper.CustomMessaging;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreatePerkCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createperk [ID] [name] [cost]";
	public static final String PERK_ID_ALREADY_EXISTS_ERROR = "A perk with this ID already exists";
	public static final String PERK_NAME_ALREADY_EXISTS_ERROR = "A perk with this name already exists";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 3)
		{
			CustomMessaging.showError(sender, CORRECT_USAGE_ERROR);
			return true;
		}
		
		if (!isValidPerkId(args[0]))
		{
			CustomMessaging.showError(sender, PERK_ID_ALREADY_EXISTS_ERROR);
			return true;
		}
		
		if (!isValidPerkName(args[1]))
		{
			CustomMessaging.showError(sender, PERK_NAME_ALREADY_EXISTS_ERROR);
			return true;
		}
		
		PerkEntity perk = new PerkEntity(args[0], args[1], Integer.parseInt(args[2]));
		PluginCore.gameplay.perks.add(perk);
		CustomMessaging.showSuccess(sender, "Successfully created perk: " + ChatColor.BOLD + perk.name);
		
		return true;
	}
	
	private boolean isValidPerkId(String perkId)
	{
		for (PerkEntity perk : PluginCore.gameplay.perks)
		{
			if (perk.id.equals(perkId))
				return false;
		}
		
		return true;
	}
	
	private boolean isValidPerkName(String perkName)
	{
		for (PerkEntity perk : PluginCore.gameplay.perks)
		{
			if (perk.name.equals(perkName))
				return false;
		}
		
		return true;
	}
}