package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class InfoCommandExecutor extends BaseCommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		sender.sendMessage(new String[] {
			ChatColor.GREEN + "" + "<==-- Call of Minecraft: Zombies --==>",
			ChatColor.YELLOW + "Version: " + ChatColor.RESET + PluginCore.getDescriptionFile().getVersion(),
			ChatColor.YELLOW + "Authors: " + ChatColor.RESET + String.join(", ", PluginCore.getDescriptionFile().getAuthors())
		});
		
		return true;
	}
}