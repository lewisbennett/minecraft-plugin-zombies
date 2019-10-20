package com.mango.zombies.gameplay;

import java.util.*;

public class GameplayCore {

    private static Dictionary<UUID, GameplayWeapon> weapons = new Hashtable<UUID, GameplayWeapon>();

    /**
     * Gets the weapons.
     */
    public static Dictionary<UUID, GameplayWeapon> getWeapons() {
        return weapons;
    }
}
