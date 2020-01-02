package com.mango.zombies.gameplay;

import java.util.*;

public class GameplayCore {

    //region Fields
    private static Dictionary<UUID, PositionTool> positionTools = new Hashtable<UUID, PositionTool>();
    private static Dictionary<UUID, GameplayWeapon> weapons = new Hashtable<UUID, GameplayWeapon>();
    //endregion

    //region Getters/Setters
    /**
     * Gets the position tools.
     */
    public static Dictionary<UUID, PositionTool> getPositionTools() {
        return positionTools;
    }

    /**
     * Gets the weapons.
     */
    public static Dictionary<UUID, GameplayWeapon> getWeapons() {
        return weapons;
    }
    //endregion
}
