package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.helper.GlobalErrors;

public class CreateMapCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /createmap [map name]";
	public static final String MapAlreadyExistsError = "This map already exists";
	
	private Player player;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
			player = (Player)sender;
		else
		{
			CustomMessaging.showError(sender, GlobalErrors.PlayerOnlyCommand);
			return true;
		}
		
		if (args.length != 1)
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		for (MapEntity map : PluginCore.mapList)
		{
			if (map.mapName.equals(args[0]))
			{
				CustomMessaging.showError(sender, MapAlreadyExistsError);
				return true;
			}
		}
		
		PluginCore.mapList.add(new MapEntity(args[0], player.getLocation().subtract(0, 1, 0)));
		CustomMessaging.showSuccess(sender, "Successfully created map: " + ChatColor.BOLD + args[0]);
		
		return true;
	}
}