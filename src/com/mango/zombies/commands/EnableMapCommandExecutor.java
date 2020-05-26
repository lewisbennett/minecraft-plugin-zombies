package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class EnableMapCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USGAE_ERROR = "Correct usage: /enablemap [map ID]";
    public static final String MAP_DOES_NOT_EXIST_ERROR = "%s is not a valid map ID.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length != 1)
            throw new CommandException(CORRECT_USGAE_ERROR);

        MapEntity mapEntity = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[0])) {
                mapEntity = queryMap;
                break;
            }
        }

        if (mapEntity == null)
            throw new CommandException(String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));

        try {

            mapEntity.enableMap();

        } catch (IllegalStateException ex) {

            throw new CommandException(ex.getMessage());

        } catch (Exception ex) {

            String error = "Map not enabled.";

            if (PluginCore.getConfig().isDebuggingEnabled()) {

                String stackTraceFileName = PluginCore.getFilingService().writeStackTraceFile(error, ExceptionUtils.getStackTrace(ex));

                if (stackTraceFileName != null)
                    error += " View the stacktrace: " + stackTraceFileName;
            }

            throw new CommandException(error);
        }

        if (mapEntity.isEnabled())
            return mapEntity.getName() + " is now enabled.";

        throw new CommandException("Map not enabled.");
    }
    //endregion
}
