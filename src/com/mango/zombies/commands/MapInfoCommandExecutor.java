package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

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

		MapEntity mapEntity = null;
		
		for (MapEntity queryMap : PluginCore.getMaps()) {

			if (queryMap.getId().equalsIgnoreCase(args[0])) {
				mapEntity = queryMap;
				break;
			}
		}

		if (mapEntity == null)
			throw new CommandException(String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));

		List<String> message = new ArrayList<String>();

		message.add(ChatColor.GREEN + "" + "<==-- " + mapEntity.getName() + " --==>");
		message.add(ChatColor.YELLOW + "ID: " + ChatColor.RESET + mapEntity.getId());
		message.add(ChatColor.YELLOW + "Top: " + ChatColor.RESET + mapEntity.getTopPoint().toString());
		message.add(ChatColor.YELLOW + "Bottom: " + ChatColor.RESET + mapEntity.getBottomPoint().toString());
		message.add(ChatColor.YELLOW + "Origin point: " + ChatColor.RESET + mapEntity.getOriginPoint().toString());
		message.add(ChatColor.YELLOW + "Signs: " + ChatColor.RESET + mapEntity.getSigns().length);
		message.add(ChatColor.YELLOW + "Maximum players: " + ChatColor.RESET + mapEntity.getMaxPlayers());
		message.add(ChatColor.YELLOW + "Delete key: " + ChatColor.RESET + (sender.isOp() ? mapEntity.getDeleteKey() : "RESTRICTED"));

		List<String> gamemodes = new ArrayList<String>();

		if (mapEntity.getStandardGamemodeConfig() != null)
			gamemodes.add("Standard");

		if (mapEntity.getTurnedGamemodeConfig() != null)
			gamemodes.add("Turned");

		if (gamemodes.size() < 1)
			gamemodes.add("none");

		message.add(ChatColor.YELLOW + "Gamemodes: " + String.join(", ", gamemodes));

		sender.sendMessage(message.toArray(new String[0]));

		return null;
	}
	//endregion
}