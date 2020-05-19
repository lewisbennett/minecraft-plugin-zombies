package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.MapConfigEntity;

import java.lang.reflect.Type;

public class MapConfigEntityJsonSerializer implements JsonSerializer<MapConfigEntity>, JsonDeserializer<MapConfigEntity> {

    //region Public Methods
    @Override
    public MapConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        MapConfigEntity config = new MapConfigEntity();

        JsonElement defaultMysteryBoxCostJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG);
        JsonElement defaultPackAPunchCostJsonElement = jsonObject.get(MapConfigEntity.DEFAULT_PACK_A_PUNCH_COST_JSON_TAG);

        if (defaultMysteryBoxCostJsonElement != null)
            config.setDefaultMysteryBoxCost(defaultMysteryBoxCostJsonElement.getAsInt());

        if (defaultPackAPunchCostJsonElement != null)
            config.setDefaultDefaultPackAPunchCost(defaultPackAPunchCostJsonElement.getAsInt());

        return config;
    }

    @Override
    public JsonElement serialize(MapConfigEntity mapConfigEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(MapConfigEntity.DEFAULT_MYSTERY_BOX_COST_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultMysteryBoxCost()));
        jsonObject.add(MapConfigEntity.DEFAULT_PACK_A_PUNCH_COST_JSON_TAG, new JsonPrimitive(mapConfigEntity.getDefaultPackAPunchCost()));

        return jsonObject;
    }
    //endregion
}
