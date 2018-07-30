package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.assets.MapInterface;
import com.mango.zombies.entities.MapEntity;

public class DeleteMapCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// check if the command was not run by a player
		if (!(sender instanceof Player))
		{
			// let the sender know that they aren't able to run this command
			sender.sendMessage("[Zombies] This command can only be run by a player");
		}
		// the command was run by a player
		else
		{
			// check that only one argument was supplied
			if (args.length == 2)
			{
				// define boolean for this check
				boolean doesExist = false;
				
				// check if the map is already present in the list
				for (MapEntity map : Main.mapList)
				{
					// check if the map name is in the list of maps
					if (map.getName().equals(args[0]))
					{
						// validate the check boolean
						doesExist = true;
					}
					
					// check if the map exists
					if (doesExist)
					{
						// check that the delete key matches
						if (map.getDeleteKey().equals(args[1]))
						{
							// delete the map and get success boolean
							boolean deleteSuccess = MapInterface.DeleteMapFile(map);
							
							// check if deleting the map was successful
							if (deleteSuccess)
							{
								// remove the map from the list
								Main.mapList.remove(map);
								
								// notify the sender
								sender.sendMessage(ChatColor.GREEN + "Deleted map: " + ChatColor.BOLD + args[0]);
							}
							// deleting the map failed
							else
							{
								// notify the sender
								sender.sendMessage(ChatColor.RED + "Could not delete map: " + args[0] + ". Please try again later");
							}
						}
						// the delete key does not match
						else
						{
							// notify the sender
							sender.sendMessage(ChatColor.RED + "Invalid delete key for map: " + args[0]);
						}
					}
				}
				
				// check if the map doesn't exist
				if (!doesExist)
				{
					// notify the sender
					sender.sendMessage(ChatColor.RED + "Map: " + args[0] + " does not exist");
				}
			}
			// the command wasn't executed properly
			else
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_deletemap <map name> <map delete key>");
			}
		}
		
		return true;
	}
}
