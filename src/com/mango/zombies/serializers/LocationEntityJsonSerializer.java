package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.LocationEntity;

import java.lang.reflect.Type;

public class LocationEntityJsonSerializer implements JsonSerializer<LocationEntity>, JsonDeserializer<LocationEntity> {

    @Override
    public LocationEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        LocationEntity location = new LocationEntity();

        location.setBlock(jsonObject.get(LocationEntity.BLOCK_JSON_TAG).getAsString());
        location.setX(jsonObject.get(LocationEntity.X_JSON_TAG).getAsInt());
        location.setY(jsonObject.get(LocationEntity.Y_JSON_TAG).getAsInt());
        location.setZ(jsonObject.get(LocationEntity.Z_JSON_TAG).getAsInt());

        return location;
    }

    @Override
    public JsonElement serialize(LocationEntity locationEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(LocationEntity.X_JSON_TAG, new JsonPrimitive(locationEntity.getX()));
        jsonObject.add(LocationEntity.Y_JSON_TAG, new JsonPrimitive(locationEntity.getY()));
        jsonObject.add(LocationEntity.Z_JSON_TAG, new JsonPrimitive(locationEntity.getZ()));
        jsonObject.add(LocationEntity.BLOCK_JSON_TAG, new JsonPrimitive(locationEntity.getBlock()));

        return jsonObject;
    }
}
