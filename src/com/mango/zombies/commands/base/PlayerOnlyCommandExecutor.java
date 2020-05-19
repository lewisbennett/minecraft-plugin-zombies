package com.mango.zombies.commands.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerOnlyCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String PLAYER_ONLY_COMMAND_ERROR = "This command can only be run by a player.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!(commandSender instanceof Player))
            throw new CommandException(PLAYER_ONLY_COMMAND_ERROR);

        return executePlayerOnlyCommand((Player)commandSender, command, label, args);
    }

    public String executePlayerOnlyCommand(Player player, Command command, String label, String[] args) {
        return null;
    }
    //endregion
}