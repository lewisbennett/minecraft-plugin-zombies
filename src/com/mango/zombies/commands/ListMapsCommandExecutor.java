package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ListMapsCommandExecutor extends BaseCommandExecutor {

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        MapEntity[] maps = PluginCore.getMaps();

        if (maps.length < 1)
            return "No maps found. Create one using: /createmap [map ID] [map name]";

        for (int i = 0; i < maps.length; i++) {

            MapEntity map = maps[i];

            List<String> info = new ArrayList<String>();

            info.add(ChatColor.GREEN + "" + "<==-- " + map.getName() + " --==>");
            info.add(ChatColor.YELLOW + "ID: " + map.getId());
            info.add(ChatColor.YELLOW + "Enabled: " + Boolean.toString(map.isEnabled()).toLowerCase());
            info.add(ChatColor.YELLOW + "Maximum players: " + map.getMaxPlayers());

            if (i < maps.length - 1)
                info.add("");

            commandSender.sendMessage(info.toArray(new String[0]));
        }

        return null;
    }
    //endregion
}
