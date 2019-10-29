package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.services.MessagingService;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class SetPositionCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /setpositon [map ID] [top/bottom]";
    public static final String INVALID_POSITION_ERROR = "Position not set. The position must be \"top\" or \"bottom\".";
    public static final String MAP_DOES_NOT_EXIST_ERROR = "Position not set. %s is not a valid map ID.";
    //endregion

    //region Event Handlers
    @Override
    public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

        if (args.length != 2) {
            MessagingService.showError(player, CORRECT_USAGE_ERROR);
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
            MessagingService.showError(player, String.format(MAP_DOES_NOT_EXIST_ERROR, args[0]));
            return true;
        }

        Location position = player.getLocation().subtract(0, 1, 0);
        String successMessage;

        if (args[1].equals("top")) {
            map.setTop(new LocationEntity(position));
            successMessage = "Top position for " + map.getId() + " set successfully.";

        } else if (args[1].equals("bottom")) {
            map.setBottom(new LocationEntity(position));
            successMessage = "Bottom position for " + map.getId() + " set successfully.";

        } else {
            MessagingService.showError(player, INVALID_POSITION_ERROR);
            return true;
        }

        MessagingService.showSuccess(player, successMessage);

        return true;
    }
    //endregion
}
