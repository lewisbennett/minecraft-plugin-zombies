package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.EnemySpawnEntity;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;

import java.lang.reflect.Type;

public class MapEntityJsonSerializer implements JsonSerializer<MapEntity>, JsonDeserializer<MapEntity> {

    @Override
    public MapEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        MapEntity map = new MapEntity();

        map.setDeleteKey(jsonObject.get(MapEntity.DELETE_KEY_JSON_TAG).getAsString());
        map.setEnabled(jsonObject.get(MapEntity.ENABLED_JSON_TAG).getAsBoolean());
        map.setId(jsonObject.get(MapEntity.ID_JSON_TAG).getAsString());
        map.setName(jsonObject.get(MapEntity.NAME_JSON_TAG).getAsString());
        map.setOriginPoint(LocationEntity.SERIALIZER.deserialize(jsonObject.get(MapEntity.ORIGIN_POINT_JSON_TAG), LocationEntity.class, jsonDeserializationContext));

        for (JsonElement j : jsonObject.get(MapEntity.PLAYER_SPAWNS_JSON_TAG).getAsJsonArray())
            map.getPlayerSpawns().add(LocationEntity.SERIALIZER.deserialize(j, LocationEntity.class, jsonDeserializationContext));

        for (JsonElement j : jsonObject.get(MapEntity.ENEMY_SPAWNS_JSON_TAG).getAsJsonArray())
            map.getEnemySpawns().add(EnemySpawnEntity.SERIALIZER.deserialize(j, EnemySpawnEntity.class, jsonDeserializationContext));

        return map;
    }

    @Override
    public JsonElement serialize(MapEntity mapEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(MapEntity.ID_JSON_TAG, new JsonPrimitive(mapEntity.getId()));
        jsonObject.add(MapEntity.NAME_JSON_TAG, new JsonPrimitive(mapEntity.getName()));
        jsonObject.add(MapEntity.DELETE_KEY_JSON_TAG, new JsonPrimitive(mapEntity.getDeleteKey()));
        jsonObject.add(MapEntity.ENABLED_JSON_TAG, new JsonPrimitive(mapEntity.isEnabled()));
        jsonObject.add(MapEntity.ORIGIN_POINT_JSON_TAG, LocationEntity.SERIALIZER.serialize(mapEntity.getOriginPoint(), LocationEntity.class, jsonSerializationContext));

        JsonArray playerSpawnsJsonArray = new JsonArray();

        for (LocationEntity playerSpawn : mapEntity.getPlayerSpawns())
            playerSpawnsJsonArray.add(LocationEntity.SERIALIZER.serialize(playerSpawn, LocationEntity.class, jsonSerializationContext));

        jsonObject.add(MapEntity.PLAYER_SPAWNS_JSON_TAG, playerSpawnsJsonArray);

        JsonArray enemySpawnsJsonArray = new JsonArray();

        for (EnemySpawnEntity enemySpawn : mapEntity.getEnemySpawns())
            enemySpawnsJsonArray.add(EnemySpawnEntity.SERIALIZER.serialize(enemySpawn, EnemySpawnEntity.class, jsonSerializationContext));

        jsonObject.add(MapEntity.ENEMY_SPAWNS_JSON_TAG, enemySpawnsJsonArray);

        return jsonObject;
    }
}
