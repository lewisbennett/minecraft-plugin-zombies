package com.mango.zombies.gameplay.base;

import com.mango.zombies.PluginCore;

import java.util.UUID;

public abstract class BaseGameplayRegisterable {

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

    //region Public Methods
    /**
     * Registers this registerable.
     */
    public void register() {
        PluginCore.getGameplayService().addRegisterable(this);
    }

    /**
     * Unregisters this registerable.
     */
    public void unregister() {
        PluginCore.getGameplayService().removeRegisterable(this);
    }
    //endregion
}
