package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import org.bukkit.inventory.ItemStack;

public class PositionTool {

    //region Fields
    private LocationEntity bottom, top;
    private ItemStack itemStack;
    private MapEntity map;
    private String positionable;
    //endregion

    //region Getters/Setters
    /**
     * Gets the selected bottom point, if any.
     */
    public LocationEntity getBottom() {
        return bottom;
    }

    /**
     * Sets the selected bottom point.
     */
    public void setBottom(LocationEntity bottom) {
        this.bottom = bottom;
    }

    /**
     * Gets the position tool ItemStack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the position tool ItemStack.
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Gets the map.
     */
    public MapEntity getMap() {
        return map;
    }

    /**
     * Gets the positionable being configured by this position tool.
     */
    public String getPositionable() {
        return positionable;
    }

    /**
     * Gets the selected top point, if any.
     */
    public LocationEntity getTop() {
        return top;
    }

    /**
     * Sets the selected top position.
     */
    public void setTop(LocationEntity top) {
        this.top = top;
    }
    //endregion

    //region Constructors
    public PositionTool(ItemStack itemStack, String mapId, String positionable) {

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(mapId)) {
                map = queryMap;
                break;
            }
        }

        this.itemStack = itemStack;
        this.map = map;
        this.positionable = positionable;
    }

    public PositionTool(ItemStack itemStack, MapEntity map, String positionable) {

        this.itemStack = itemStack;
        this.map = map;
        this.positionable = positionable;
    }
    //endregion
}
