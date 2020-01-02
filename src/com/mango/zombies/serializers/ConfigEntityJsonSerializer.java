package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.ConfigEntity;
import org.bukkit.ChatColor;

import java.lang.reflect.Type;

public class ConfigEntityJsonSerializer implements JsonSerializer<ConfigEntity>, JsonDeserializer<ConfigEntity> {

    //region Public Methods
    @Override
    public ConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        ConfigEntity config = new ConfigEntity();

        config.setAmmoIndicatorColor(ChatColor.valueOf(jsonObject.get(ConfigEntity.AMMO_INDICATOR_COLOR_JSON_TAG).getAsString()));
        config.setAutoGenerateDefaultPerks(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_PERKS_JSON_TAG).getAsBoolean());
        config.setAutoGenerateDefaultWeaponClasses(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPON_CLASSES_JSON_TAG).getAsBoolean());
        config.setAutoGenerateDefaultWeapons(jsonObject.get(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPONS_JSON_TAG).getAsBoolean());
        config.setAutoSaveTimerInterval(jsonObject.get(ConfigEntity.AUTO_SAVE_TIMER_INTERVAL_JSON_TAG).getAsInt());
        config.setDefaultMysteryBoxCost(jsonObject.get(ConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG).getAsInt());
        config.setEnableDebugging(jsonObject.get(ConfigEntity.ENABLE_DEBUGGING_JSON_TAG).getAsBoolean());
        config.setOutOfAmmoIndicatorColor(ChatColor.valueOf(jsonObject.get(ConfigEntity.OUT_OF_AMMO_INDICATOR_COLOR_JSON_TAG).getAsString()));
        config.setReloadingIndicatorColor(ChatColor.valueOf(jsonObject.get(ConfigEntity.RELOADING_INDICATOR_COLOR_JSON_TAG).getAsString()));
        config.setWorldName(jsonObject.get(ConfigEntity.WORLD_NAME_JSON_TAG).getAsString());

        return config;
    }

    @Override
    public JsonElement serialize(ConfigEntity configEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(ConfigEntity.WORLD_NAME_JSON_TAG, new JsonPrimitive(configEntity.getWorldName()));
        jsonObject.add(ConfigEntity.AUTO_SAVE_TIMER_INTERVAL_JSON_TAG, new JsonPrimitive(configEntity.getAutoSaveTimerInterval()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_PERKS_JSON_TAG, new JsonPrimitive(configEntity.doesAutoGenerateDefaultPerks()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPON_CLASSES_JSON_TAG, new JsonPrimitive(configEntity.doesAutoGenerateDefaultWeaponClasses()));
        jsonObject.add(ConfigEntity.AUTO_GENERATE_DEFAULT_WEAPONS_JSON_TAG, new JsonPrimitive(configEntity.doesAutoGenerateDefaultWeapons()));
        jsonObject.add(ConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG, new JsonPrimitive(configEntity.getDefaultMysteryBoxCost()));
        jsonObject.add(ConfigEntity.AMMO_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(configEntity.getAmmoIndicatorColor().name()));
        jsonObject.add(ConfigEntity.OUT_OF_AMMO_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(configEntity.getOutOfAmmoIndicatorColor().name()));
        jsonObject.add(ConfigEntity.RELOADING_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(configEntity.getReloadingIndicatorColor().name()));
        jsonObject.add(ConfigEntity.ENABLE_DEBUGGING_JSON_TAG, new JsonPrimitive(configEntity.isDebuggingEnabled()));

        return jsonObject;
    }
    //endregion
}
