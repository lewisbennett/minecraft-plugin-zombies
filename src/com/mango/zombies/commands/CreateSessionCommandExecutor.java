package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.gameplay.GameplaySession;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class CreateSessionCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createsession [map ID] [gamemode]";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length != 2)
            throw new CommandException(CORRECT_USAGE_ERROR);

        GameplaySession session;

        try {

            session = PluginCore.getGameplayService().createSession(args[0], args[1]);

        } catch (IllegalStateException ex) {
            throw new CommandException(ex.getMessage());
        }

        return "Session for " + session.getMap().getName() + " created successfully. Join using: /joinsession " + args[0];
    }
    //endregion
}
