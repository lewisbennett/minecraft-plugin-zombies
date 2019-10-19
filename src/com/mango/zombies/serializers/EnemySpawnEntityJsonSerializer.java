package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.entities.EnemySpawnEntity;

import java.lang.reflect.Type;

public class EnemySpawnEntityJsonSerializer implements JsonSerializer<EnemySpawnEntity>, JsonDeserializer<EnemySpawnEntity> {

    @Override
    public EnemySpawnEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        EnemySpawnEntity enemy = new EnemySpawnEntity();

        enemy.setBlock(jsonObject.get(EnemySpawnEntity.BLOCK_JSON_TAG).getAsString());
        enemy.setEnemy(EnemyEntity.SERIALIZER.deserialize(jsonObject.get(EnemySpawnEntity.ENEMY_JSON_TAG), EnemyEntity.class, jsonDeserializationContext));
        enemy.setX(jsonObject.get(EnemySpawnEntity.X_JSON_TAG).getAsInt());
        enemy.setY(jsonObject.get(EnemySpawnEntity.Y_JSON_TAG).getAsInt());
        enemy.setZ(jsonObject.get(EnemySpawnEntity.Z_JSON_TAG).getAsInt());

        return enemy;
    }

    @Override
    public JsonElement serialize(EnemySpawnEntity enemySpawnEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(EnemySpawnEntity.X_JSON_TAG, new JsonPrimitive(enemySpawnEntity.getX()));
        jsonObject.add(EnemySpawnEntity.Y_JSON_TAG, new JsonPrimitive(enemySpawnEntity.getY()));
        jsonObject.add(EnemySpawnEntity.Z_JSON_TAG, new JsonPrimitive(enemySpawnEntity.getZ()));
        jsonObject.add(EnemySpawnEntity.BLOCK_JSON_TAG, new JsonPrimitive(enemySpawnEntity.getBlock()));
        jsonObject.add(EnemySpawnEntity.ENEMY_JSON_TAG, EnemyEntity.SERIALIZER.serialize(enemySpawnEntity.getEnemy(), EnemyEntity.class, jsonSerializationContext));

        return jsonObject;
    }
}
