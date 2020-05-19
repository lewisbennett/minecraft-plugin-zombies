package com.mango.zombies.entities;

import com.mango.zombies.serializers.LocationEntityJsonSerializer;
import org.bukkit.Location;

public class LocationEntity {

    //region Constant Values
    public static final LocationEntityJsonSerializer SERIALIZER = new LocationEntityJsonSerializer();

    public static final String X_JSON_TAG = "x";
    public static final String Y_JSON_TAG = "y";
    public static final String Z_JSON_TAG = "z";
    //endregion

    //region Fields
    private int x;
    private int y;
    private int z;
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