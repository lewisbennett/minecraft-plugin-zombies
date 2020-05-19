package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.PerkConfigEntity;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class PerkConfigEntityJsonSerializer implements JsonSerializer<PerkConfigEntity>, JsonDeserializer<PerkConfigEntity> {

    //region Public Methods
    @Override
    public PerkConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        PerkConfigEntity config = new PerkConfigEntity();

        JsonElement defaultDoesRequirePowerJsonElement = jsonObject.get(PerkConfigEntity.DEFAULT_DOES_REQUIRE_POWER_JSON_TAG);
        JsonElement defaultMaterialJsonElement = jsonObject.get(PerkConfigEntity.DEFAULT_MATERIAL_JSON_TAG);

        if (defaultDoesRequirePowerJsonElement != null)
            config.setDefaultDoesRequirePower(defaultDoesRequirePowerJsonElement.getAsBoolean());

        if (defaultMaterialJsonElement != null)
            config.setDefaultMaterial(Material.valueOf(defaultMaterialJsonElement.getAsString()));

        return config;
    }

    @Override
    public JsonElement serialize(PerkConfigEntity perkConfigEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(PerkConfigEntity.DEFAULT_DOES_REQUIRE_POWER_JSON_TAG, new JsonPrimitive(perkConfigEntity.getDefaultDoesRequirePower()));
        jsonObject.add(PerkConfigEntity.DEFAULT_MATERIAL_JSON_TAG, new JsonPrimitive(perkConfigEntity.getDefaultMaterial().name()));

        return jsonObject;
    }
    //endregion
}
