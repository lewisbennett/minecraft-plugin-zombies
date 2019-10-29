package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.services.FilingService;
import com.mango.zombies.services.MessagingService;
import org.bukkit.command.Command;
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.isOp()) {
            MessagingService.showError(sender, OP_ONLY_COMMAND);
            return true;
        }

        if (args.length != 2) {
            MessagingService.showError(sender, CORRECT_USAGE_ERROR);
            return true;
        }

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(args[0])) {
                map = queryMap;
                break;
            }
        }

        if (map == null) {
            MessagingService.showError(sender, String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));
            return true;
        }

        if (!map.getDeleteKey().equals(args[1])) {
            MessagingService.showError(sender, DELETE_KEY_INCORRECT_ERROR);
            return true;
        }

        FilingService.deleteFile(PluginCore.getMapsFolder(), map.getId());
        PluginCore.getMaps().remove(map);

        MessagingService.showSuccess(sender, map.getId() + " deleted successfully.");

        return true;
    }
    //endregion
}
