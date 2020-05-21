package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class MapInfoCommandExecutor extends BaseCommandExecutor {

	//region Constant Values
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /mapinfo [map ID]";
	public static final String MAP_DOES_NOT_EXIST_ERROR = "%s is not a valid map ID.";
	//endregion

	//region Event Handlers
	@Override
	public String executeCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length != 1)
			throw new CommandException(CORRECT_USAGE_ERROR);

		MapEntity map = null;
		
		for (MapEntity queryMap : PluginCore.getMaps()) {

			if (queryMap.getId().equalsIgnoreCase(args[0])) {
				map = queryMap;
				break;
			}
		}

		if (map == null)
			throw new CommandException(String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));
		
		sender.sendMessage(new String[] {
			ChatColor.GREEN + "" + "<==-- " + map.getName() + " --==>",
			ChatColor.YELLOW + "ID: " + ChatColor.RESET + map.getId(),
			ChatColor.YELLOW + "Top: " + ChatColor.RESET + map.getTop().toString(),
			ChatColor.YELLOW + "Bottom: " + ChatColor.RESET + map.getBottom().toString(),
			ChatColor.YELLOW + "Origin point: " + ChatColor.RESET + map.getOriginPoint().toString(),
			ChatColor.YELLOW + "Player spawn points: " + ChatColor.RESET + map.getPlayerSpawns().length,
			ChatColor.YELLOW + "Enemy spawn points: " + ChatColor.RESET + map.getEnemySpawns().length,
			ChatColor.YELLOW + "Delete key: " + ChatColor.RESET + (sender.isOp() ? map.getDeleteKey() : "RESTRICTED")
		});
		
		return null;
	}
	//endregion
}