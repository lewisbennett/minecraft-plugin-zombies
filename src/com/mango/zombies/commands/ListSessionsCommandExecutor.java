package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ListSessionsCommandExecutor extends BaseCommandExecutor {

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        List<GameplaySession> sessions = new ArrayList<GameplaySession>();

        for (BaseGameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (queryRegisterable instanceof GameplaySession)
                sessions.add((GameplaySession)queryRegisterable);
        }

        if (sessions.size() < 1)
            return "No sessions found. Create one using: /createsession [map ID]";

        for (int i = 0; i < sessions.size(); i++) {

            GameplaySession session = sessions.get(i);

            List<String> info = new ArrayList<String>();

            info.add(ChatColor.GREEN + "" + "<==-- " + session.getMap().getName() + " --==>");
            info.add(ChatColor.YELLOW + "Players: " + ChatColor.RESET + session.getPlayers().length + "/" + session.getMap().getMaxPlayers());

            List<String> actions = new ArrayList<String>();

            if (!session.hasBegun())
                actions.add("join");

            if (session.getMap().getSpectatorSpawns().length > 0)
                actions.add("spectate");

            if (actions.size() > 0)
                info.add(ChatColor.YELLOW + "Actions: " + ChatColor.RESET + String.join(", ", actions));

            if (i < sessions.size() - 1)
                info.add("");

            commandSender.sendMessage(info.toArray(new String[0]));
        }

        return null;
    }
    //endregion
}
