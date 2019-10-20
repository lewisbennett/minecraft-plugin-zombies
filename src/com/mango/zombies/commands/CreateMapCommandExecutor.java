package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.services.MessagingService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CreateMapCommandExecutor extends PlayerOnlyCommandExecutor {

    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createmap [ID] [name]";
    public static final String MAP_ID_ALREADY_EXISTS_ERROR = "Map not created. %s already exists.";

    @Override
    public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

        if (args.length < 2) {
            MessagingService.showError(player, CORRECT_USAGE_ERROR);
            return true;
        }

        if (!isValidMapId(args[0])) {
            MessagingService.showError(player, String.format(MAP_ID_ALREADY_EXISTS_ERROR, args[0]));
            return true;
        }

        StringBuilder name = new StringBuilder();

        for (int i = 1; i < args.length; i++) {

            if (i > 1)
                name.append(" ");

            name.append(args[i]);
        }

        MapEntity map = new MapEntity(args[0], name.toString(), player.getLocation().subtract(0, 1, 0));
        PluginCore.getMaps().add(map);
        MessagingService.showSuccess(player, "Successfully created map: " + ChatColor.BOLD + map.getName());

        return true;
    }

    private boolean isValidMapId(String mapId) {

        for (MapEntity map : PluginCore.getMaps()) {

            if (map.getId().equalsIgnoreCase(mapId))
                return false;
        }

        return true;
    }
}