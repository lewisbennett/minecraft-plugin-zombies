package com.mango.zombies.helper;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messaging {

	/**
	 * Shows a formatted success message to the sender.
	 */
	public static void showSuccess(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.GREEN + message);
	}

	/**
	 * Shows a formatted error message to the sender.
	 */
	public static void showError(CommandSender sender, String error) {
		sender.sendMessage(ChatColor.RED + error);
	}
}