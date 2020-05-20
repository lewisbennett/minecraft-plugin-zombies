package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.MapConfigEntity;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class MapConfigEntityJsonSerializer implements JsonSerializer<MapConfigEntity>, JsonDeserializer<MapConfigEntity> {

    //region Public Methods
    @Override
    public MapConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        MapConfigEntity config = new MapConfigEntity();

        JsonElement defaultMysteryBoxCostJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG);
        JsonElement defaultPackAPunchCostJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_PACK_A_PUNCH_COST_JSON_TAG);
        JsonElement defaultRoundEndSoundJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_ROUND_END_SOUND_JSON_TAG);
        JsonElement defaultRoundStartSoundJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_ROUND_START_SOUND_JSON_TAG);

        if (defaultMysteryBoxCostJsonElement != null)
            config.setDefaultMysteryBoxCost(defaultMysteryBoxCostJsonElement.getAsInt());

        if (defaultPackAPunchCostJsonElement != null)
            config.setDefaultDefaultPackAPunchCost(defaultPackAPunchCostJsonElement.getAsInt());

        if (defaultRoundEndSoundJsonElement != null)
            config.setDefaultRoundEndSound(Sound.valueOf(defaultRoundEndSoundJsonElement.getAsString()));

        if (defaultRoundStartSoundJsonElement != null)
            config.setDefaultRoundStartSound(Sound.valueOf(defaultRoundStartSoundJsonElement.getAsString()));

        return config;
    }

    @Override
    public JsonElement serialize(MapConfigEntity mapConfigEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(MapConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultMysteryBoxCost()));
        jsonObject.add(MapConfigEntity.DEFAULT_PACK_A_PUNCH_COST_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultPackAPunchCost()));
        jsonObject.add(MapConfigEntity.DEFAULT_ROUND_START_SOUND_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultRoundStartSound().name()));
        jsonObject.add(MapConfigEntity.DEFAULT_ROUND_END_SOUND_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultRoundEndSound().name()));

        return jsonObject;
    }
    //endregion
}
