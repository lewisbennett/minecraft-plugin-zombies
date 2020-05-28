package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.StandardGamemodeConfigEntity;
import com.mango.zombies.entities.TurnedGamemodeConfigEntity;
import com.mango.zombies.schema.Gamemode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class AddGamemodeCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /addgamemode [map ID] [gamemode]";
    public static final String INVALID_GAMEMODE_ERROR = "Gamemode not added. %s is not a valid gamemode.";
    public static final String MAP_NOT_FOUND_ERROR = "Gamemode not added. %s is not a valid map ID.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length != 2)
            throw new CommandException(CORRECT_USAGE_ERROR);

        MapEntity mapEntity = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[0])) {
                mapEntity = queryMap;
                break;
            }
        }

        if (mapEntity == null)
            throw new CommandException(String.format(MAP_NOT_FOUND_ERROR, args[0]));

        switch (args[1]) {

            case Gamemode.STANDARD:
                mapEntity.setStandardGamemodeConfig(new StandardGamemodeConfigEntity());
                break;

            case Gamemode.TURNED:
                mapEntity.setTurnedGamemodeConfig(new TurnedGamemodeConfigEntity());
                break;

            default:
                throw new CommandException(String.format(INVALID_GAMEMODE_ERROR, args[1]));
        }

        return "Gamemode added successfully.";
    }
    //endregion
}
