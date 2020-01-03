package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;

import java.lang.reflect.Type;

public class MapEntityJsonSerializer implements JsonSerializer<MapEntity>, JsonDeserializer<MapEntity> {

    //region Public Methods
    @Override
    public MapEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        MapEntity map = new MapEntity();

        map.setBottom(LocationEntity.SERIALIZER.deserialize(jsonObject.get(MapEntity.BOTTOM_JSON_TAG), LocationEntity.class, jsonDeserializationContext));
        map.setDeleteKey(jsonObject.get(MapEntity.DELETE_KEY_JSON_TAG).getAsString());
        map.setEnabled(jsonObject.get(MapEntity.ENABLED_JSON_TAG).getAsBoolean());
        map.setId(jsonObject.get(MapEntity.ID_JSON_TAG).getAsString());
        map.setName(jsonObject.get(MapEntity.NAME_JSON_TAG).getAsString());
        map.setOriginPoint(LocationEntity.SERIALIZER.deserialize(jsonObject.get(MapEntity.ORIGIN_POINT_JSON_TAG), LocationEntity.class, jsonDeserializationContext));
        map.setTop(LocationEntity.SERIALIZER.deserialize(jsonObject.get(MapEntity.TOP_JSON_TAG), LocationEntity.class, jsonDeserializationContext));

        for (JsonElement j : jsonObject.get(MapEntity.ENEMY_WHITELIST_JSON_TAG).getAsJsonArray())
            map.getEnemyWhitelist().add(j.getAsString());

        for (JsonElement j : jsonObject.get(MapEntity.ENEMY_BLACKLIST_JSON_TAG).getAsJsonArray())
            map.getEnemyBlacklist().add(j.getAsString());

        for (JsonElement j : jsonObject.get(MapEntity.WEAPON_WHITELIST_JSON_TAG).getAsJsonArray())
            map.getWeaponWhitelist().add(j.getAsString());

        for (JsonElement j : jsonObject.get(MapEntity.WEAPON_BLACKLIST_JSON_TAG).getAsJsonArray())
            map.getWeaponBlacklist().add(j.getAsString());

        for (JsonElement j : jsonObject.get(MapEntity.PLAYER_SPAWNS_JSON_TAG).getAsJsonArray())
            map.getPlayerSpawns().add(LocationEntity.SERIALIZER.deserialize(j, LocationEntity.class, jsonDeserializationContext));

        for (JsonElement j : jsonObject.get(MapEntity.ENEMY_SPAWNS_JSON_TAG).getAsJsonArray())
            map.getEnemySpawns().add(LocationEntity.SERIALIZER.deserialize(j, LocationEntity.class, jsonDeserializationContext));

        return map;
    }

    @Override
    public JsonElement serialize(MapEntity mapEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(MapEntity.ID_JSON_TAG, new JsonPrimitive(mapEntity.getId()));
        jsonObject.add(MapEntity.NAME_JSON_TAG, new JsonPrimitive(mapEntity.getName()));
        jsonObject.add(MapEntity.DELETE_KEY_JSON_TAG, new JsonPrimitive(mapEntity.getDeleteKey()));
        jsonObject.add(MapEntity.ENABLED_JSON_TAG, new JsonPrimitive(mapEntity.isEnabled()));
        jsonObject.add(MapEntity.TOP_JSON_TAG, LocationEntity.SERIALIZER.serialize(mapEntity.getTop(), LocationEntity.class, jsonSerializationContext));
        jsonObject.add(MapEntity.BOTTOM_JSON_TAG, LocationEntity.SERIALIZER.serialize(mapEntity.getBottom(), LocationEntity.class, jsonSerializationContext));
        jsonObject.add(MapEntity.ORIGIN_POINT_JSON_TAG, LocationEntity.SERIALIZER.serialize(mapEntity.getOriginPoint(), LocationEntity.class, jsonSerializationContext));

        JsonArray enemyWhitelistJsonArray = new JsonArray();
        JsonArray enemyBlacklistJsonArray = new JsonArray();
        JsonArray weaponWhitelistJsonArray = new JsonArray();
        JsonArray weaponBlacklistJsonArray = new JsonArray();
        JsonArray playerSpawnsJsonArray = new JsonArray();
        JsonArray enemySpawnsJsonArray = new JsonArray();

        for (String s : mapEntity.getEnemyWhitelist())
            enemyWhitelistJsonArray.add(s);

        for (String s : mapEntity.getEnemyBlacklist())
            enemyBlacklistJsonArray.add(s);

        for (String s : mapEntity.getWeaponWhitelist())
            weaponWhitelistJsonArray.add(s);

        for (String s : mapEntity.getWeaponBlacklist())
            weaponBlacklistJsonArray.add(s);

        for (LocationEntity playerSpawn : mapEntity.getPlayerSpawns())
            playerSpawnsJsonArray.add(LocationEntity.SERIALIZER.serialize(playerSpawn, LocationEntity.class, jsonSerializationContext));

        for (LocationEntity enemySpawn : mapEntity.getEnemySpawns())
            enemySpawnsJsonArray.add(LocationEntity.SERIALIZER.serialize(enemySpawn, LocationEntity.class, jsonSerializationContext));

        jsonObject.add(MapEntity.ENEMY_WHITELIST_JSON_TAG, enemyWhitelistJsonArray);
        jsonObject.add(MapEntity.ENEMY_BLACKLIST_JSON_TAG, enemyBlacklistJsonArray);
        jsonObject.add(MapEntity.WEAPON_WHITELIST_JSON_TAG, weaponWhitelistJsonArray);
        jsonObject.add(MapEntity.WEAPON_BLACKLIST_JSON_TAG, weaponBlacklistJsonArray);
        jsonObject.add(MapEntity.PLAYER_SPAWNS_JSON_TAG, playerSpawnsJsonArray);
        jsonObject.add(MapEntity.ENEMY_SPAWNS_JSON_TAG, enemySpawnsJsonArray);

        return jsonObject;
    }
    //endregion
}
