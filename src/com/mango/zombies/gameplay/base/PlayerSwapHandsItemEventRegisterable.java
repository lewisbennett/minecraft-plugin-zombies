package com.mango.zombies.gameplay.base;

import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public interface PlayerSwapHandsItemEventRegisterable {

    //region Event Handlers
    /**
     * Called when a player swaps the items in their hands.
     * @param event The event caused by the swap.
     */
    void onPlayerSwapHansItem(PlayerSwapHandItemsEvent event);
    //endregion
}
