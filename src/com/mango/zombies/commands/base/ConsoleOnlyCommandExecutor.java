package com.mango.zombies.commands.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleOnlyCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CONSOLE_ONLY_COMMAND_ERROR = "This command can only be run by the console.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!(commandSender instanceof ConsoleCommandSender))
            throw new CommandException(CONSOLE_ONLY_COMMAND_ERROR);

        return executeConsoleOnlyCommand((ConsoleCommandSender)commandSender, command, label, args);
    }

    public String executeConsoleOnlyCommand(ConsoleCommandSender player, Command command, String label, String[] args) {
        return null;
    }
    //endregion
}