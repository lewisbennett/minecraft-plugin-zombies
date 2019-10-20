package com.mango.zombies.base;

import com.mango.zombies.services.MessagingService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerOnlyCommandExecutor extends BaseCommandExecutor {

    public static final String PLAYER_ONLY_COMMAND_ERROR = "This command can only be run by a player.";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (commandSender instanceof Player)
            return onSuccessfulCommand((Player)commandSender, command, label, args);

        MessagingService.showError(commandSender, PLAYER_ONLY_COMMAND_ERROR);

        return true;
    }

    public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {
        return false;
    }
}
