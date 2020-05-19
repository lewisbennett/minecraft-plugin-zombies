package com.mango.zombies.commands;

import com.mango.zombies.Main;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class InfoCommandExecutor extends BaseCommandExecutor {

	//region Event Handlers
	@Override
	public String executeCommand(CommandSender sender, Command command, String label, String[] args) {

		PluginDescriptionFile descriptionFile = Main.getInstance().getDescription();

		sender.sendMessage(new String[] {
			ChatColor.GREEN + "" + "<==-- Call of Minecraft: Zombies --==>",
			ChatColor.YELLOW + "Version: " + ChatColor.RESET + descriptionFile.getVersion(),
			ChatColor.YELLOW + "Authors: " + ChatColor.RESET + String.join(", ", descriptionFile.getAuthors())
		});
		
		return null;
	}
	//endregion
}