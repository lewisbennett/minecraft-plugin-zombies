package com.mango.zombies.entities;

import com.mango.zombies.serializers.PerkConfigEntityJsonSerializer;
import org.bukkit.Material;

public class PerkConfigEntity {

    //region Constant Values
    public static final boolean DEFAULT_DEFAULT_DOES_REQUIRE_POWER = true;

    public static final Material DEFAULT_DEFAULT_MATERIAL = Material.DIAMOND;

    public static final PerkConfigEntityJsonSerializer SERIALIZER = new PerkConfigEntityJsonSerializer();

    public static final String DEFAULT_DOES_REQUIRE_POWER_JSON_TAG = "defaultDoesRequirePower";

    public static final String DEFAULT_MATERIAL_JSON_TAG = "defaultMaterial";
    //endregion

    //region Fields
    private boolean defaultDoesRequirePower = DEFAULT_DEFAULT_DOES_REQUIRE_POWER;

    private Material defaultMaterial = DEFAULT_DEFAULT_MATERIAL;
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
