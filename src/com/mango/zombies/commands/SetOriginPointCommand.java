package com.mango.zombies.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;
import com.mango.zombies.assets.MapInterface;
import com.mango.zombies.entities.MapEntity;

public class SetOriginPointCommand implements CommandExecutor
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
			// create Player object
			Player player = (Player) sender;
			
			// check that only one argument was supplied
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
						// check that this origin point does not already exist
						if (!map.getOriginPointStr().contains(player.getLocation().getBlockX() + "," + player.getLocation().subtract(0, 1, 0).getBlockY() + "," + player.getLocation().getBlockZ()))
						{
							// check that the map is disabled
							if (!map.isEnabled())
							{
								// check if the origin point is not default
								if (!map.getOriginPointStr().equals("0,0,0,GRASS"))
								{
									// define World
									World world = Bukkit.getServer().getWorld(Main.worldName);
									
									// define Location and Block objects for the origin point
									Location originPointLocation = new Location(world, map.getOriginPoint().getXCoord(), map.getOriginPoint().getYCoord(), map.getOriginPoint().getZCoord());
									Block originPointBlock = originPointLocation.getBlock();
									
									// set the block back to its original state
									originPointBlock.setType(Material.matchMaterial(map.getOriginPoint().getBlock()));
								}
								
								// define Location and Block objects
								Location location = player.getLocation().subtract(0, 1, 0);
								Block block = location.getBlock();
								
								// set the origin point
								map.setOriginPointLocation(location, block);
								
								// re-write the map file
								boolean editSuccess = MapInterface.WriteMapFile(map);
								
								// check if the edit was successful
								if (editSuccess)
								{
									// notify the sender
									sender.sendMessage(ChatColor.GREEN + "Set origin point for map: "
										+ ChatColor.BOLD
										+ args[0]
										+ ChatColor.RESET
										+ ""
										+ ChatColor.GREEN
										+ " to "
										+ ChatColor.ITALIC
										+ location.getBlockX()
										+ " "
										+ location.getBlockY()
										+ " "
										+ location.getBlockZ());
									
									// show the spawn point
									block.setType(Material.DIAMOND_BLOCK);
								}
								// the edit failed
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
								sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + args[0] + ChatColor.RESET + "" + ChatColor.RED + " has to be disabled to set the origin point");
							}
						}
						// the origin point is already here
						else
						{
							// notify the sender
							sender.sendMessage(ChatColor.RED
								+ "The origin point is already located at "
								+ ChatColor.ITALIC
								+ player.getLocation().getBlockX()
								+ " "
								+ player.getLocation().getBlockY()
								+ " "
								+ player.getLocation().getBlockZ());
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
				sender.sendMessage(ChatColor.RED + "Correct usage: /z_setoriginpoint <map name>");
			}
		}
		
		return true;
	}
}