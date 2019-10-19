package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.EnemyEntity;

import java.lang.reflect.Type;

public class EnemyEntityJsonSerializer implements JsonSerializer<EnemyEntity>, JsonDeserializer<EnemyEntity> {

    @Override
    public EnemyEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        EnemyEntity enemy = new EnemyEntity();

        return enemy;
    }

    @Override
    public JsonElement serialize(EnemyEntity enemySpawnEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        return jsonObject;
    }
}
