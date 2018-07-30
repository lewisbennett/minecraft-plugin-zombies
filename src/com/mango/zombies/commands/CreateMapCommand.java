package com.mango.zombies.commands;

import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.assets.MapInterface;
import com.mango.zombies.entities.MapEntity;

public class CreateMapCommand implements CommandExecutor
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
		// the command was ran by a player
		else
		{
			// check that only one argument was used
			if (args.length == 1)
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
						
						break;
					}
				}
				
				// check if the map does not already exist
				if (!doesExist)
				{
					// define new Random and generate delete key
					Random random = new Random();
					int deleteKey = 100000 + random.nextInt(999999);
					
					// build a new MapEntity using the name provided and add it to the map list
					MapEntity newMap = new MapEntity(args[0].trim());
					newMap.setDescription("");
					newMap.setDeleteKey(Integer.toString(deleteKey));
					newMap.setEnabled(false);
					newMap.setUuid(UUID.randomUUID().toString());
					
					// try to make the file and get success booolean
					boolean createMapSuccess = MapInterface.WriteMapFile(newMap);
					
					// check if creating the map file was successful
					if (createMapSuccess)
					{
						// add the map to the list
						Main.mapList.add(newMap);
												
						// notify the sender
						sender.sendMessage(ChatColor.GREEN + "Created new map: " + ChatColor.BOLD + args[0]);
					}
					// creating the file failed
					else
					{
						// notify the sender
						sender.sendMessage(ChatColor.RED + "Unable to create map: " + args[0]);
					}
				}
				// the map already exists
				else
				{
					// notify the sender
					sender.sendMessage(ChatColor.RED + "Map: " + args[0] + " already exists");
				}
			}
			// either too many or too few arguments were used
			else
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_createmap <map name>");
			}
		}
		
		return true;
	}
}