package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Location;

public class LocationEntity {

    //region Fields
    @Expose private int x;
    @Expose private int y;
    @Expose private int z;
    //endregion

    //region Getters/Setters
    /**
     * Gets the X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the X coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the Y coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the Z coordinate.
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets the Z coordinate.
     */
    public void setZ(int z) {
        this.z = z;
    }
    //endregion

    //region Public Methods
    /**
     * Gets whether the location is empty.
     */
    public boolean isEmpty() {
        return x == 0 && y == 0 && z == 0;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }
    //endregion

    //region Constructors
    public LocationEntity() { }

    public LocationEntity(Location location) {
        this();

        x = location.getBlockX();
        y = location.getBlockY();
        z = location.getBlockZ();
    }
    //endregion
}