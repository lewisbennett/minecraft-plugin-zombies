package com.mango.zombies.gameplay.base;

import org.bukkit.event.block.BlockBreakEvent;

public interface BlockBreakEventRegisterable {

    //region Event Handlers
    /**
     * Called when a block breaks.
     * @param event The event caused by the break.
     */
    void onBlockBreak(BlockBreakEvent event);
    //endregion
}
