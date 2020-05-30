package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;

public class JoinSessionCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /joinsession [map ID]";
    public static final String MULTIPLE_SESSION_ERROR = "You must leave your current session before joining a new one.";
    public static final String SESSION_DOES_NOT_EXIST_ERROR = "A session for %s does not exist. Create a session using: /createsession [map ID] [gamemode]";
    //endregion

    //region Event Handlers
    @Override
    public String executePlayerOnlyCommand(Player player, Command command, String label, String[] args) {

        if (args.length != 1)
            throw new CommandException(CORRECT_USAGE_ERROR);

        GameplaySession session = null;

        for (BaseGameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (!(queryRegisterable instanceof GameplaySession))
                continue;

            GameplaySession querySession = (GameplaySession)queryRegisterable;

            if (querySession.getMap().getId().equals(args[0])) {
                session = querySession;
                continue;
            }

            for (GameplayPlayer queryPlayer : querySession.getPlayers()) {

                if (queryPlayer.getPlayer().getUniqueId().equals(player.getUniqueId()))
                    throw new CommandException(MULTIPLE_SESSION_ERROR);
            }
        }

        if (session == null)
            throw new CommandException(String.format(SESSION_DOES_NOT_EXIST_ERROR, args[0]));

        session.addPlayer(player);

        return "Joined " + session.getMap().getName() + ", " + session.getGamemode().getName() + ".";
    }
    //endregion
}
