package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.entities.MapEntity;

public class MapInfoCommand implements CommandExecutor
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
			// define new Player object
			Player player = (Player) sender;
			
			// check if the command only has 1 argument
			if (args.length == 1)
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
						// show the map info
						sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "<-- " + map.getName() + " -->");
						sender.sendMessage(ChatColor.YELLOW + "Name: " + ChatColor.RESET + map.getName());
						if (map.getDescription().equals(""))
						{
							sender.sendMessage(ChatColor.YELLOW + "Description: " + ChatColor.RESET + "No description set");
						}
						else
						{
							sender.sendMessage(ChatColor.YELLOW + "Description: " + ChatColor.RESET + map.getDescription());
						}
						sender.sendMessage(ChatColor.YELLOW + "Origin point: " + ChatColor.RESET + map.getOriginPointDisplay());
						sender.sendMessage(ChatColor.YELLOW + "Player spawn points: " + ChatColor.RESET + map.listPlayerSpawnPoints().size());
						sender.sendMessage(ChatColor.YELLOW + "Zombie spawn points: " + ChatColor.RESET + map.listZombieSpawnPoints().size());
						if (map.isEnabled())
						{
							sender.sendMessage(ChatColor.YELLOW + "Enabled: " + ChatColor.RESET + "Yes");
						}
						else
						{
							sender.sendMessage(ChatColor.YELLOW + "Enabled: " + ChatColor.RESET + "No");
						}
						sender.sendMessage(ChatColor.YELLOW + "UUID: " + ChatColor.RESET + map.getUuid());
						
						// check if the user in an Op
						if (player.isOp())
						{
							// show the delete key
							sender.sendMessage(ChatColor.YELLOW + "Delete key: " + ChatColor.RESET + map.getDeleteKey());
						}
						
						break;
					}
				}
				
				// check if the map doesn't exist
				if (!doesExist)
				{
					// notify the sender
					sender.sendMessage(ChatColor.RED + "Map: " + args[0] + " does not exist");
				}
			}
			// the command was run incorrectly
			else
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_mapinfo <map name>");
			}
		}
		
		return true;
	}
}
