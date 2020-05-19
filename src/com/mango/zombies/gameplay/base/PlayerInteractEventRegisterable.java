package com.mango.zombies.gameplay.base;

import org.bukkit.event.player.PlayerInteractEvent;

public interface PlayerInteractEventRegisterable {

    //region Event Handlers
    /**
     * Called when a player interacts with this weapon.
     * @param event The event caused by the interaction.
     */
    void onPlayerInteract(PlayerInteractEvent event);
    //endregion
}
