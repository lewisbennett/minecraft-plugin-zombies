package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.assets.MapInterface;
import com.mango.zombies.entities.MapEntity;

public class AddSpawnPointZombieCommand implements CommandExecutor
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
						// check that this spawn point does not already exist
						if (!map.getZombieSpawnPoints().contains(player.getLocation().getBlockX() + "," + player.getLocation().subtract(0, 1, 0).getBlockY() + "," + player.getLocation().getBlockZ()))
						{
							// check that this spawn point does not already exist
							if (!map.getPlayerSpawnPoints().contains(player.getLocation().getBlockX() + "," + player.getLocation().subtract(0, 1, 0).getBlockY() + "," + player.getLocation().getBlockZ()))
							{
								// check that the map is disabled
								if (!map.isEnabled())
								{
									// define Location and Block objects
									Location location = player.getLocation().subtract(0, 1, 0);
									Block block = location.getBlock();
									
									// add the spawn point to the list
									map.addZombieSpawnLocation(location, block);
									
									// re-write the map file
									boolean writeMapSuccess = MapInterface.WriteMapFile(map);
									
									// check if writing the map was successful
									if (writeMapSuccess)
									{
										// notify the sender
										sender.sendMessage(ChatColor.GREEN + "Added Zombie spawn point for map: "
											+ ChatColor.BOLD
											+ args[0]
											+ ChatColor.RESET
											+ ""
											+ ChatColor.GREEN
											+ " at "
											+ ChatColor.ITALIC
											+ player.getLocation().getBlockX()
											+ " "
											+ player.getLocation().getBlockY()
											+ " "
											+ player.getLocation().getBlockZ());
										
										// show the spawn point
										block.setType(Material.REDSTONE_BLOCK);
									}
									// writing the map failed
									else
									{
										// notify the sender
										sender.sendMessage(ChatColor.RED + "Could not save map edits");
									}
								}
								// the map is enabled
								else
								{
									// notify the sender
									sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + args[0] + ChatColor.RESET + "" + ChatColor.RED + " has to be disabled to add a Zombie spawn point");
								}
								
								break;
							}
							// the spawn point already exists
							else
							{
								// notify the sender
								sender.sendMessage(ChatColor.RED
									+ "A Player spawn point already exists at "
									+ ChatColor.ITALIC
									+ player.getLocation().getBlockX()
									+ " "
									+ player.getLocation().getBlockY()
									+ " "
									+ player.getLocation().getBlockZ());
								
								break;
							}
						}
						// the spawn point already exists
						else
						{
							// notify the sender
							sender.sendMessage(ChatColor.RED
								+ "A Zombie spawn point already exists at "
								+ ChatColor.ITALIC
								+ player.getLocation().getBlockX()
								+ " "
								+ player.getLocation().getBlockY()
								+ " "
								+ player.getLocation().getBlockZ());
							
							break;
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
			// the command was run incorrectly
			else
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_addspawnpoint_zombie <map name>");
			}
		}
		
		return true;
	}
}
