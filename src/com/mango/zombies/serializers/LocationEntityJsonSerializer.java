package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.LocationEntity;

import java.lang.reflect.Type;

public class LocationEntityJsonSerializer implements JsonSerializer<LocationEntity>, JsonDeserializer<LocationEntity> {

    //region Public Methods
    @Override
    public LocationEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        LocationEntity location = new LocationEntity();

        JsonElement xJsonElement = jsonObject.get(LocationEntity.X_JSON_TAG);
        JsonElement yJsonElement = jsonObject.get(LocationEntity.Y_JSON_TAG);
        JsonElement zJsonElement = jsonObject.get(LocationEntity.Z_JSON_TAG);

        if (xJsonElement != null)
            location.setX(xJsonElement.getAsInt());

        if (yJsonElement != null)
            location.setY(yJsonElement.getAsInt());

        if (zJsonElement != null)
            location.setZ(zJsonElement.getAsInt());

        return location;
    }

    @Override
    public JsonElement serialize(LocationEntity locationEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(LocationEntity.X_JSON_TAG, new JsonPrimitive(locationEntity.getX()));
        jsonObject.add(LocationEntity.Y_JSON_TAG, new JsonPrimitive(locationEntity.getY()));
        jsonObject.add(LocationEntity.Z_JSON_TAG, new JsonPrimitive(locationEntity.getZ()));

        return jsonObject;
    }
    //endregion
}
