package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.helper.CustomMessaging;

public class MapInfoCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /mapinfo [map ID]";
	public static final String MapDoesNotExistError = "The specified map does not exist";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args.length != 1)
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		// get the map - we need it to check that it exists and to construct the info later
		MapEntity map = null;
		
		for (MapEntity queryMap : PluginCore.gameplay.maps)
		{
			if (queryMap.id.equals(args[0]))
			{
				map = queryMap;
				break;
			}
		}
		
		if (map == null)
		{
			CustomMessaging.showError(sender, MapDoesNotExistError);
			return true;
		}
		
		sender.sendMessage(new String[]
		{
			ChatColor.GREEN + "" + "<==-- " + map.name + " --==>",
			ChatColor.YELLOW + "ID: " + ChatColor.RESET + map.id,
			ChatColor.YELLOW + "Origin point: " + ChatColor.RESET + map.originPoint.getLocationAsString(),
			ChatColor.YELLOW + "Player spawn points: " + ChatColor.RESET + Integer.toString(map.playerSpawns.size()),
			ChatColor.YELLOW + "Zombie spawn points: " + ChatColor.RESET + Integer.toString(map.zombieSpawns.size()),
			ChatColor.YELLOW + "Delete key: " + ChatColor.RESET + (sender.isOp() ? map.deleteKey : "RESTRICTED")
		});
		
		return true;
	}
}