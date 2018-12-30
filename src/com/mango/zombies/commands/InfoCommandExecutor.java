package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.mango.zombies.PluginCore;

public class InfoCommandExecutor implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		sender.sendMessage(new String[]
		{
			ChatColor.GREEN + "" + "<==-- Call of Minecraft: Zombies --==>",
			ChatColor.YELLOW + "Version: " + ChatColor.RESET + PluginCore.descriptionFile.getVersion(),
			ChatColor.YELLOW + "Authors: " + ChatColor.RESET + String.join(", ", PluginCore.descriptionFile.getAuthors())
		});
		
		return true;
	}
}