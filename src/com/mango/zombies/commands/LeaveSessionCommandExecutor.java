package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;

public class LeaveSessionCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String NOT_IN_SESSION_ERROR = "You are not currently in a session.";
    //endregion

    //region Event Handlers
    @Override
    public String executePlayerOnlyCommand(Player player, Command command, String label, String[] args) {

        GameplaySession gameplaySession = null;
        GameplayPlayer gameplayPlayer = null;

        for (GameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (!(queryRegisterable instanceof GameplaySession))
                continue;

            GameplaySession querySession = (GameplaySession)queryRegisterable;

            for (GameplayPlayer queryPlayer : querySession.getPlayers()) {

                if (queryPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {

                    gameplaySession = querySession;
                    gameplayPlayer = queryPlayer;

                    break;
                }
            }
        }

        if (gameplaySession == null)
            throw new CommandException(NOT_IN_SESSION_ERROR);

        gameplaySession.removePlayer(gameplayPlayer);

        if (gameplaySession.getPlayers().length < 1)
            gameplaySession.unregister();

        return "Left " + gameplaySession.getMap().getName() + ", " + gameplaySession.getGamemode().getName() + ".";
    }
    //endregion
}
