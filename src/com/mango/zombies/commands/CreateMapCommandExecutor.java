package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;

public class CreateMapCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createmap [ID] [name]";
    public static final String MAP_ID_ALREADY_EXISTS_ERROR = "Map not created. %s already exists.";
    //endregion

    //region Event Handlers
    @Override
    public String executePlayerOnlyCommand(Player player, Command command, String label, String[] args) {

        if (args.length < 2)
            throw new CommandException(CORRECT_USAGE_ERROR);

        if (!isValidMapId(args[0]))
            throw new CommandException(String.format(MAP_ID_ALREADY_EXISTS_ERROR, args[0]));

        StringBuilder name = new StringBuilder();

        for (int i = 1; i < args.length; i++) {

            if (i > 1)
                name.append(" ");

            name.append(args[i]);
        }

        MapEntity map = new MapEntity(args[0], name.toString(), player.getLocation().subtract(0, 1, 0));
        PluginCore.addMap(map);

        return "Successfully created map: " + ChatColor.BOLD + map.getName();
    }
    //endregion

    //region Private Methods
    private boolean isValidMapId(String mapId) {

        for (MapEntity map : PluginCore.getMaps()) {

            if (map.getId().equals(mapId))
                return false;
        }

        return true;
    }
    //endregion
}