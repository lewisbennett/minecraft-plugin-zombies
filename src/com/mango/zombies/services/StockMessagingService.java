package com.mango.zombies.services;

import com.mango.zombies.services.base.MessagingService;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StockMessagingService implements MessagingService {

	//region Public Methods
	/**
	 * Shows a formatted error message to the sender.
	 */
	public void error(CommandSender sender, String error) {
		sender.sendMessage(ChatColor.RED + error);
	}

	/**
	 * Shows a formatted success message to the sender.
	 */
	public void success(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GREEN + message);
	}
	//endregion
}