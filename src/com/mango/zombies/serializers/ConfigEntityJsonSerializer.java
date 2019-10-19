package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.ConfigEntity;

import java.lang.reflect.Type;

public class ConfigEntityJsonSerializer implements JsonSerializer<ConfigEntity>, JsonDeserializer<ConfigEntity> {

    @Override
    public ConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        ConfigEntity config = new ConfigEntity();

        config.setAutoGenerateDefaultPerks(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_PERKS_JSON_TAG).getAsBoolean());
        config.setAutoGenerateDefaultWeaponClasses(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPON_CLASSES_JSON_TAG).getAsBoolean());
        config.setAutoGenerateDefaultWeapons(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPONS_JSON_TAG).getAsBoolean());
        config.setDefaultMysteryBoxCost(jsonObject.get(ConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG).getAsInt());
        config.setWorldName(jsonObject.get(ConfigEntity.WORLD_NAME_JSON_TAG).getAsString());

        return config;
    }

    @Override
    public JsonElement serialize(ConfigEntity configEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(ConfigEntity.WORLD_NAME_JSON_TAG, new JsonPrimitive(configEntity.getWorldName()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_PERKS_JSON_TAG, new JsonPrimitive(configEntity.isAutoGenerateDefaultPerks()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPON_CLASSES_JSON_TAG, new JsonPrimitive(configEntity.isAutoGenerateDefaultWeaponClasses()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPONS_JSON_TAG, new JsonPrimitive(configEntity.isAutoGenerateDefaultWeapons()));
        jsonObject.add(ConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG, new JsonPrimitive(configEntity.getDefaultMysteryBoxCost()));

        return jsonObject;
    }
}
