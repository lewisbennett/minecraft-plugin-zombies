package com.mango.zombies.helper;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CustomMessaging
{
	public static void showSuccess(CommandSender sender, String message)
	{
		sender.sendMessage(ChatColor.GREEN + message);
	}
	
	public static void showError(CommandSender sender, String error)
	{
		sender.sendMessage(ChatColor.RED + error);
	}
}