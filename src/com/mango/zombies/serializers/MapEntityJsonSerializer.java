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

        JsonElement bottomJsonElement = jsonObject.get(MapEntity.BOTTOM_JSON_TAG);
        JsonElement deleteKeyJsonElement = jsonObject.get(MapEntity.DELETE_KEY_JSON_TAG);
        JsonElement enemyBlacklistJsonElement = jsonObject.get(MapEntity.ENEMY_BLACKLIST_JSON_TAG);
        JsonElement enemySpawnsJsonElement = jsonObject.get(MapEntity.ENEMY_SPAWNS_JSON_TAG);
        JsonElement enemyWhitelistJsonElement = jsonObject.get(MapEntity.ENEMY_WHITELIST_JSON_TAG);
        JsonElement idJsonElement = jsonObject.get(MapEntity.ID_JSON_TAG);
        JsonElement nameJsonElement = jsonObject.get(MapEntity.NAME_JSON_TAG);
        JsonElement originPointJsonElement = jsonObject.get(MapEntity.ORIGIN_POINT_JSON_TAG);
        JsonElement playerSpawnsJsonElement = jsonObject.get(MapEntity.PLAYER_SPAWNS_JSON_TAG);
        JsonElement topJsonElement = jsonObject.get(MapEntity.TOP_JSON_TAG);
        JsonElement weaponBlacklistJsonElement = jsonObject.get(MapEntity.WEAPON_BLACKLIST_JSON_TAG);
        JsonElement weaponWhitelistJsonElement = jsonObject.get(MapEntity.WEAPON_WHITELIST_JSON_TAG);

        if (bottomJsonElement != null)
            map.setBottom(LocationEntity.SERIALIZER.deserialize(bottomJsonElement, LocationEntity.class, jsonDeserializationContext));

        if (deleteKeyJsonElement != null)
            map.setDeleteKey(deleteKeyJsonElement.getAsString());

        if (enemyBlacklistJsonElement != null) {

            for (JsonElement j : enemyBlacklistJsonElement.getAsJsonArray())
                map.addEnemyBlacklistEntry(j.getAsString());
        }

        if (enemySpawnsJsonElement != null) {

            for (JsonElement j : enemySpawnsJsonElement.getAsJsonArray())
                map.addEnemySpawnLocation(LocationEntity.SERIALIZER.deserialize(j, LocationEntity.class, jsonDeserializationContext));
        }

        if (enemyWhitelistJsonElement != null) {

            for (JsonElement j : enemyWhitelistJsonElement.getAsJsonArray())
                map.addEnemyWhitelistEntry(j.getAsString());
        }

        if (idJsonElement != null)
            map.setId(idJsonElement.getAsString());

        if (nameJsonElement != null)
            map.setName(nameJsonElement.getAsString());

        if (originPointJsonElement != null)
            map.setOriginPoint(LocationEntity.SERIALIZER.deserialize(originPointJsonElement, LocationEntity.class, jsonDeserializationContext));

        if (playerSpawnsJsonElement != null) {

            for (JsonElement j : playerSpawnsJsonElement.getAsJsonArray())
                map.addPlayerSpawnLocation(LocationEntity.SERIALIZER.deserialize(j, LocationEntity.class, jsonDeserializationContext));
        }

        if (topJsonElement != null)
            map.setTop(LocationEntity.SERIALIZER.deserialize(topJsonElement, LocationEntity.class, jsonDeserializationContext));

        if (weaponBlacklistJsonElement != null) {

            for (JsonElement j : weaponBlacklistJsonElement.getAsJsonArray())
                map.addWeaponBlacklistEntry(j.getAsString());
        }

        if (weaponWhitelistJsonElement != null) {

            for (JsonElement j : weaponWhitelistJsonElement.getAsJsonArray())
                map.addEnemyWhitelistEntry(j.getAsString());
        }

        return map;
    }

    @Override
    public JsonElement serialize(MapEntity mapEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(MapEntity.ID_JSON_TAG, new JsonPrimitive(mapEntity.getId()));
        jsonObject.add(MapEntity.NAME_JSON_TAG, new JsonPrimitive(mapEntity.getName()));
        jsonObject.add(MapEntity.DELETE_KEY_JSON_TAG, new JsonPrimitive(mapEntity.getDeleteKey()));
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
