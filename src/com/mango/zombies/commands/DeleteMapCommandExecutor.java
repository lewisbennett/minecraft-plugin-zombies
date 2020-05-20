package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class DeleteMapCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /deletemap [map ID] [delete key]";
    public static final String DELETE_KEY_INCORRECT_ERROR = "Map not deleted. The delete key is incorrect.";
    public static final String MAP_DOES_NOT_EXIST_ERROR = "Map not deleted. %s is not a valid map ID.";
    public static final String OP_ONLY_COMMAND = "This command can only be run by an operator.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.isOp())
            throw new CommandException(OP_ONLY_COMMAND);

        if (args.length != 2)
            throw new CommandException(CORRECT_USAGE_ERROR);

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[0])) {
                map = queryMap;
                break;
            }
        }

        if (map == null)
            throw new CommandException(String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));

        if (!map.getDeleteKey().equals(args[1]))
            throw new CommandException(DELETE_KEY_INCORRECT_ERROR);

        PluginCore.getFilingService().deleteFile(PluginCore.getFilingService().getMapsFolder(), map.getId());
        PluginCore.removeMap(map);

        return map.getId() + " deleted successfully.";
    }
    //endregion
}
