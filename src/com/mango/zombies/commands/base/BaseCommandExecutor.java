package com.mango.zombies.commands.base;

import com.mango.zombies.PluginCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BaseCommandExecutor implements CommandExecutor {

    //region Event Handlers
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        try {

            String successMessage = executeCommand(commandSender, command, label, args);

            if (successMessage != null)
                PluginCore.getMessagingService().success(commandSender, successMessage);

            return true;

        } catch (CommandException e) {

            PluginCore.getMessagingService().error(commandSender, e.getMessage());
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }
    //endregion
}
