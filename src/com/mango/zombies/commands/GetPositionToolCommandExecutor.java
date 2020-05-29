package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.PositionTool;
import com.mango.zombies.schema.Positionable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPositionToolCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR_CONSOLE = "Correct usage: /getpositiontool [map/door] [map ID] [player name]";
    public static final String CORRECT_USAGE_ERROR_PLAYER = "Correct usage: /getpositiontool [map/door] [map ID]";
    public static final String INVALID_MAP_ERROR = "%s is not a valid map ID.";
    public static final String INVALID_POSITIONABLE_ERROR = "%s is not a valid positionable.";
    public static final String PLAYER_NOT_FOUND_ERROR = "Player not found.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        Player player;

        if (commandSender instanceof Player) {

            player = (Player)commandSender;

            if (args.length != 2)
                throw new CommandException(CORRECT_USAGE_ERROR_PLAYER);

        } else {

            if (args.length != 3)
                throw new CommandException(CORRECT_USAGE_ERROR_CONSOLE);

            player = Bukkit.getPlayer(args[2]);

            if (player == null)
                throw new CommandException(PLAYER_NOT_FOUND_ERROR);
        }

        if (!isValidPositionable(args[0]))
            throw new CommandException(String.format(INVALID_POSITIONABLE_ERROR, args[0]));

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[1])) {
                map = queryMap;
                break;
            }
        }

        if (map == null)
            throw new CommandException(String.format(INVALID_MAP_ERROR, args[1]));

        PositionTool positionTool = new PositionTool(map, args[0]);

        PluginCore.getGameplayService().addRegisterable(positionTool);

        positionTool.giveItemStack(player);

        return "With the Position Tool, left click to set the top point and right click to set the bottom point. Changes will be applied once both points have been selected.";
    }
    //endregion

    //region Private Methods
    private boolean isValidPositionable(String positionable) {

        for (String queryPositionable : Positionable.toList()) {

            if (queryPositionable.equals(positionable))
                return true;
        }

        return false;
    }
    //endregion
}
