package com.mango.zombies.base;

import com.mango.zombies.services.MessagingService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleOnlyCommandExecutor extends BaseCommandExecutor {

    public static final String CONSOLE_ONLY_COMMAND_ERROR = "This command can only be run by the console.";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof ConsoleCommandSender)
            return onSuccessfulCommand(commandSender, command, label, args);

        MessagingService.showError(commandSender, CONSOLE_ONLY_COMMAND_ERROR);

        return true;
    }

    public boolean onSuccessfulCommand(CommandSender commandSender, Command command, String label, String[] args) {
        return false;
    }
}
