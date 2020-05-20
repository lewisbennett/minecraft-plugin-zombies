package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import org.bukkit.Material;

public class PerkConfigEntity {

    //region Constant Values
    public static final boolean DEFAULT_DEFAULT_DOES_REQUIRE_POWER = true;

    public static final Material DEFAULT_DEFAULT_MATERIAL = Material.DIAMOND;
    //endregion

    //region Fields
    @Expose private boolean defaultDoesRequirePower = DEFAULT_DEFAULT_DOES_REQUIRE_POWER;

    @Expose private Material defaultMaterial = DEFAULT_DEFAULT_MATERIAL;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether perks require power by default.
     */
    public boolean getDefaultDoesRequirePower() {
        return defaultDoesRequirePower;
    }

    /**
     * Sets whether perks require power by default.
     */
    public void setDefaultDoesRequirePower(boolean defaultDoesRequirePower) {
        this.defaultDoesRequirePower = defaultDoesRequirePower;
    }

    /**
     * Gets the default material.
     */
    public Material getDefaultMaterial() {
        return defaultMaterial;
    }

    /**
     * Sets the default material.
     */
    public void setDefaultMaterial(Material defaultMaterial) {
        this.defaultMaterial = defaultMaterial;
    }
    //endregion
}
