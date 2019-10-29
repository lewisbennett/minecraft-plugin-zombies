package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.services.MessagingService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MapInfoCommandExecutor extends BaseCommandExecutor {

	//region Constant Values
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /mapinfo [map ID]";
	public static final String MAP_DOES_NOT_EXIST_ERROR = "%s is not a valid map ID.";
	//endregion

	//region Event Handlers
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length != 1) {
			MessagingService.showError(sender, CORRECT_USAGE_ERROR);
			return true;
		}
		
		MapEntity map = null;
		
		for (MapEntity queryMap : PluginCore.getMaps()) {

			if (queryMap.getId().equalsIgnoreCase(args[0])) {
				map = queryMap;
				break;
			}
		}
		
		if (map == null) {
			MessagingService.showError(sender, String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));
			return true;
		}
		
		sender.sendMessage(new String[] {
			ChatColor.GREEN + "" + "<==-- " + map.getName() + " --==>",
			ChatColor.YELLOW + "ID: " + ChatColor.RESET + map.getId(),
			ChatColor.YELLOW + "Top: " + ChatColor.RESET + map.getTop().toString(),
			ChatColor.YELLOW + "Bottom: " + ChatColor.RESET + map.getBottom().toString(),
			ChatColor.YELLOW + "Origin point: " + ChatColor.RESET + map.getOriginPoint().toString(),
			ChatColor.YELLOW + "Player spawn points: " + ChatColor.RESET + map.getPlayerSpawns().size(),
			ChatColor.YELLOW + "Enemy spawn points: " + ChatColor.RESET + map.getEnemySpawns().size(),
			ChatColor.YELLOW + "Delete key: " + ChatColor.RESET + (sender.isOp() ? map.getDeleteKey() : "RESTRICTED")
		});
		
		return true;
	}
	//endregion
}