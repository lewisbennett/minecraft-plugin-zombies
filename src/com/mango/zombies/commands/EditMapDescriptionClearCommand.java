package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.assets.MapInterface;
import com.mango.zombies.entities.MapEntity;

public class EditMapDescriptionClearCommand implements CommandExecutor
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
			// check that one 1 argument was supplied
			if (args.length >= 1)
			{
				// define boolean for this check
				boolean doesExist = false;
				
				// check if the map is already present in the list
				for (MapEntity map : Main.mapList)
				{
					// invalidate the bool
					doesExist = false;
					
					// check if the map name is in the list of maps
					if (map.getName().equals(args[0]))
					{
						// validate the check boolean
						doesExist = true;
					}
					
					// check if the map exists
					if (doesExist)
					{
						// set the description
						map.setDescription("");
						
						// re-write the map file
						boolean editSuccess = MapInterface.WriteMapFile(map);
						
						// check if the edit was successful
						if (editSuccess)
						{
							// notify the sender
							sender.sendMessage(ChatColor.GREEN + "Cleared description for map: " + ChatColor.BOLD + args[0]);
						}
						// the edit failed
						else
						{
							// notify the sender
							sender.sendMessage(ChatColor.RED + "Could not save map edits");
						}
						
						break;
					}
				}
				
				// check if the map does not exist
				if (!doesExist)
				{
					// notify the sender
					sender.sendMessage(ChatColor.RED + "Map: " + args[0] + " does not exist");
				}
			}
			// not enough or too many arguments were supplied
			else
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_editmap_description_clear <map name>");
			}
		}
		
		return true;
	}
}