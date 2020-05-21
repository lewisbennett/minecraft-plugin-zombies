package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

public class SignEntity {

    //region Fields
    @Expose private LocationEntity location;

    @Expose private String resourceType;
    @Expose private String resourceValue;
    @Expose private String type;
    //endregion

    //region Getters/Setters
    /**
     * Gets the location.
     */
    public LocationEntity getLocation() {
        return location;
    }

    /**
     * Sets the location.
     */
    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    /**
     * Gets the resource type.
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * Sets the resource type.
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Gets the resource value.
     */
    public String getResourceValue() {
        return resourceValue;
    }

    /**
     * Sets the resource value.
     */
    public void setResourceValue(String resourceValue) {
        this.resourceValue = resourceValue;
    }

    /**
     * Gets the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     */
    public void setType(String type) {
        this.type = type;
    }
    //endregion
}
