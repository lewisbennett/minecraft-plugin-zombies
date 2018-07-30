package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.Main;

public class ListMapsCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		// check if the command was not run by a player
		if (!(sender instanceof Player))
		{
			// let the sender know that they aren't able to run this command
			sender.sendMessage("This command can only be run by a player");
		}
		// the command was run by a player
		else
		{
			// check if the maps list contains any items
			if (Main.mapList.size() == 0)
			{
				// notify the sender
				sender.sendMessage(ChatColor.RED + "No maps found");
			}
			// there is at least one map in the list
			else
			{
				// send the first line
				sender.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "Maps:");
				
				// run this for every item in the maps list
				for (int i = 0; i < Main.mapList.size(); i++)
				{
					// list the maps
					sender.sendMessage(Main.mapList.get(i).getName());
				}
			}
		}
		
		return true;
	}
}