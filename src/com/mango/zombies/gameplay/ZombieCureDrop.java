package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.base.GameplayDrop;
import org.bukkit.Material;
import org.bukkit.Sound;

public class ZombieCureDrop extends GameplayDrop {

    //region Getters/Setters
    /**
     * Gets the material for this drop.
     */
    @Override
    public Material getMaterial() {
        return PluginCore.getGameplayConfig().getZombieCureItem();
    }

    /**
     * Gets the sound played when this drop is picked up.
     */
    @Override
    public Sound getSound() {
        return null;
    }
    //endregion

    //region Constructors
    public ZombieCureDrop(ZombiesGamemode gamemode) {
        super(gamemode);
    }
    //endregion
}
