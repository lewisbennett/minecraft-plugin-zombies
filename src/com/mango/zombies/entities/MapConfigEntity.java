package com.mango.zombies.entities;

import com.mango.zombies.serializers.MapConfigEntityJsonSerializer;

public class MapConfigEntity {

    //region Constant Values
    public static final int DEFAULT_DEFAULT_MYSTERY_BOX_COST = 950;
    public static final int DEFAULT_DEFAULT_PACK_A_PUNCH_COST = 5000;

    public static final MapConfigEntityJsonSerializer SERIALIZER = new MapConfigEntityJsonSerializer();

    public static final String DEFAULT_MYSTERY_BOX_COST_JSON_TAG = "defaultMysteryBoxCost";
    public static final String DEFAULT_PACK_A_PUNCH_COST_JSON_TAG = "defaulPackAPunchCost";
    //endregion

    //region Fields
    private int defaultMysteryBoxCost = DEFAULT_DEFAULT_MYSTERY_BOX_COST;
    private int defaultPackAPunchCost = DEFAULT_DEFAULT_PACK_A_PUNCH_COST;
    //endregion

    //region Getters/Setters
    /**
     * Gets the default mystery box cost.
     */
    public int getDefaultMysteryBoxCost() {
        return defaultMysteryBoxCost;
    }

    /**
     * Sets the default mystery box cost.
     */
    public void setDefaultMysteryBoxCost(int defaultMysteryBoxCost) {
        this.defaultMysteryBoxCost = defaultMysteryBoxCost;
    }

    /**
     * Gets the default Pack-A-Punch cost.
     */
    public int getDefaultPackAPunchCost() {
        return defaultPackAPunchCost;
    }

    /**
     * Sets the default Pack-A-Punch cost.
     */
    public void setDefaultDefaultPackAPunchCost(int defaultPackAPunchCost) {
        this.defaultPackAPunchCost = defaultPackAPunchCost;
    }
    //endregion
}
