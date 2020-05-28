package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

public class LockedLocationEntity extends LocationEntity {

    //region Fields
    @Expose private String lockId;
    //endregion

    //region Getters/Setters
    /**
     * Gets the ID of the resource that this location is locked to.
     */
    public String getLockId() {
        return lockId;
    }

    /**
     * Sets the ID of the resource that this location is locked to.
     */
    public void setLockId(String lockId) {
        this.lockId = lockId;
    }
    //endregion
}
