package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BasePositionTool;
import com.mango.zombies.schema.Positionable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPositionToolCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR_CONSOLE = "Correct usage: /getpositiontool [positionable] [map ID] [player name]";
    public static final String CORRECT_USAGE_ERROR_PLAYER = "Correct usage: /getpositiontool [positionable] [map ID]";
    public static final String INVALID_MAP_ERROR = "%s is not a valid map ID.";
    public static final String INVALID_POSITIONABLE_ERROR = "%s is not a valid positionable.";
    public static final String MAP_ENABLED_ERROR = "%s must be disabled first.";
    public static final String NO_STANDARD_GAMEMODE_ERROR = "The Standard gamemode has not been added for %s.";
    public static final String NO_TURNED_GAMEMODE_ERROR = "The Turned gamemode has not been added for %s.";
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

        MapEntity mapEntity = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[1])) {
                mapEntity = queryMap;
                break;
            }
        }

        if (mapEntity == null)
            throw new CommandException(String.format(INVALID_MAP_ERROR, args[1]));

        if (mapEntity.isEnabled())
            throw new CommandException(String.format(MAP_ENABLED_ERROR, mapEntity.getName()));

        switch (args[0]) {

            case Positionable.ENEMY_SPAWN:
            case Positionable.PLAYER_SPAWN_STANDARD:

                if (mapEntity.getStandardGamemodeConfig() == null)
                    throw new CommandException(String.format(NO_STANDARD_GAMEMODE_ERROR, mapEntity.getName()));

                break;

            case Positionable.PLAYER_SPAWN_TURNED:

                if (mapEntity.getTurnedGamemodeConfig() == null)
                    throw new CommandException(String.format(NO_TURNED_GAMEMODE_ERROR, mapEntity.getName()));

        }

        BasePositionTool positionTool = BasePositionTool.getPositionToolForPositionable(mapEntity, args[0]);

        if (positionTool == null)
            throw new CommandException(String.format(INVALID_POSITIONABLE_ERROR, args[0]));

        positionTool.register();
        positionTool.giveItemStack(player);

        return positionTool.getUsageDescription();


    }
    //endregion
}
