package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.BlockLocationEntity;

import java.lang.reflect.Type;

public class BlockLocationEntityJsonSerializer implements JsonSerializer<BlockLocationEntity>, JsonDeserializer<BlockLocationEntity> {

    @Override
    public BlockLocationEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        BlockLocationEntity location = new BlockLocationEntity();

        location.setBlock(jsonObject.get(BlockLocationEntity.BLOCK_JSON_TAG).getAsString());
        location.setX(jsonObject.get(BlockLocationEntity.X_JSON_TAG).getAsInt());
        location.setY(jsonObject.get(BlockLocationEntity.Y_JSON_TAG).getAsInt());
        location.setZ(jsonObject.get(BlockLocationEntity.Z_JSON_TAG).getAsInt());

        return location;
    }

    @Override
    public JsonElement serialize(BlockLocationEntity locationEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(BlockLocationEntity.X_JSON_TAG, new JsonPrimitive(locationEntity.getX()));
        jsonObject.add(BlockLocationEntity.Y_JSON_TAG, new JsonPrimitive(locationEntity.getY()));
        jsonObject.add(BlockLocationEntity.Z_JSON_TAG, new JsonPrimitive(locationEntity.getZ()));
        jsonObject.add(BlockLocationEntity.BLOCK_JSON_TAG, new JsonPrimitive(locationEntity.getBlock()));

        return jsonObject;
    }
}
