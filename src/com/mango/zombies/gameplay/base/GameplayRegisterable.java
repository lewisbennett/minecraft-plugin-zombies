package com.mango.zombies.gameplay.base;

import java.util.UUID;

public abstract class GameplayRegisterable {

    //region Getters/Setters
    /**
     * Gets the UUID of this gameplay registerable.
     */
    public abstract UUID getUUID();
    //endregion

    //region Event Handlers
    /**
     * Called when this gameplay registerable is registered.
     */
    public void onRegistered() { }

    /**
     * Called when this gameplay registerabled is unregistered.
     */
    public void onUnregistered() { }
    //endregion
}
