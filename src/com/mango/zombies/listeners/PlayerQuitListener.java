package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        for (GameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (!(queryRegisterable instanceof GameplaySession))
                continue;

            GameplaySession querySession = (GameplaySession)queryRegisterable;

            boolean hasRemoved = false;

            for (GameplayPlayer queryPlayer : querySession.getPlayers()) {

                if (queryPlayer.getPlayer().getUniqueId().equals(event.getPlayer().getUniqueId())) {

                    querySession.removePlayer(queryPlayer);
                    hasRemoved = true;

                    break;
                }
            }

            if (hasRemoved)
                break;
        }
    }
    //endregion
}
