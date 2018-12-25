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
	public static final String AlreadyExistsError = "This map already exists";
	
	private Player _player;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
			_player = (Player)sender;
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
		
		for (MapEntity map : PluginCore.gameplay.maps)
		{
			if (map.name.equals(args[0]))
			{
				CustomMessaging.showError(sender, AlreadyExistsError);
				return true;
			}
		}
		
		PluginCore.gameplay.maps.add(new MapEntity(args[0], _player.getLocation().subtract(0, 1, 0)));
		CustomMessaging.showSuccess(sender, "Successfully created map: " + ChatColor.BOLD + args[0]);
		
		return true;
	}
}