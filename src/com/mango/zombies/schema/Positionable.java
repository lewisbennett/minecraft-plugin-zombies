package com.mango.zombies.schema;

import java.util.ArrayList;
import java.util.List;

public class Positionable {

    //region Constant Values
    public static final String DOOR = "door";
    public static final String MAP = "map";
    //endregion

    //region Public Static Methods
    /**
     * Gets all positionables as an array.
     * @return
     */
    public static List<String> toList() {

        List<String> positionables = new ArrayList<String>();

        positionables.add(DOOR);
        positionables.add(MAP);

        return positionables;
    }
    //endregion
}
