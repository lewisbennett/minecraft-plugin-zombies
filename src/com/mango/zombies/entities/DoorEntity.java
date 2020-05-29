package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Location;

import java.util.UUID;

public class DoorEntity {

    //region Fields
    @Expose private LocationEntity bottomPoint;
    @Expose private LocationEntity topPoint;

    @Expose private UUID uuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets the bottom point.
     */
    public LocationEntity getBottomPoint() {
        return bottomPoint;
    }

    /**
     * Sets the bottom point.
     */
    public void setBottomPoint(LocationEntity bottomPoint) {
        this.bottomPoint = bottomPoint;
    }

    /**
     * Gets the top point.
     */
    public LocationEntity getTopPoint() {
        return topPoint;
    }

    /**
     * Sets the top point.
     */
    public void setTopPoint(LocationEntity topPoint) {
        this.topPoint = topPoint;
    }

    /**
     * Gets the UUID.
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Sets the UUID.
     */
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }
    //endregion

    //region Constructors
    public DoorEntity() { }

    public DoorEntity(Location topPoint, Location bottomPoint) {
        this(new LocationEntity(topPoint), new LocationEntity(bottomPoint));
    }

    public DoorEntity(LocationEntity topPoint, LocationEntity bottomPoint) {

        this.topPoint = topPoint;
        this.bottomPoint = bottomPoint;

        uuid = UUID.randomUUID();
    }
    //endregion
}
