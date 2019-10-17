package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.helper.GlobalErrors;

public class CreateMapCommandExecutor implements CommandExecutor
{
    // errors specific to this command
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createmap [ID] [name]";
    public static final String MAP_ID_ALREADY_EXISTS_ERROR = "A map with this ID already exists";
    public static final String MAP_NAME_ALREADY_EXISTS_ERROR = "A map with this name already exists";

    private Player _player;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
            _player = (Player)sender;
        else
        {
            CustomMessaging.showError(sender, GlobalErrors.PlayerOnlyCommand);
            return true;
        }

        if (args.length != 2)
        {
            CustomMessaging.showError(sender, CORRECT_USAGE_ERROR);
            return true;
        }

        if (!isValidMapId(args[0]))
        {
            CustomMessaging.showError(sender, MAP_ID_ALREADY_EXISTS_ERROR);
            return true;
        }

        if (!isValidMapName(args[1]))
        {
            CustomMessaging.showError(sender, MAP_NAME_ALREADY_EXISTS_ERROR);
            return true;
        }

        MapEntity map = new MapEntity(args[0], args[1], _player.getLocation().subtract(0, 1, 0));
        PluginCore.gameplay.maps.add(map);
        CustomMessaging.showSuccess(sender, "Successfully created map: " + ChatColor.BOLD + map.name);

        return true;
    }

    private boolean isValidMapId(String mapId)
    {
        for (MapEntity map : PluginCore.gameplay.maps)
        {
            if (map.id.equals(mapId))
                return false;
        }

        return true;
    }

    private boolean isValidMapName(String mapName)
    {
        for (MapEntity map: PluginCore.gameplay.maps)
        {
            if (map.name.equals(mapName))
                return false;
        }

        return true;
    }
}