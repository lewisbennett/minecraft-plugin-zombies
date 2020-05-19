package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.EnemyConfigEntity;

import java.lang.reflect.Type;

public class EnemyConfigEntityJsonSerializer implements JsonSerializer<EnemyConfigEntity>, JsonDeserializer<EnemyConfigEntity> {

    //region Public Methods
    @Override
    public EnemyConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        EnemyConfigEntity config = new EnemyConfigEntity();

        JsonElement defaultDespawnTimeJsonElement = jsonObject.get(EnemyConfigEntity.DEFAULT_DESPAWN_TIME_JSON_TAG);
        JsonElement defaultMaxHealthJsonElement = jsonObject.get(EnemyConfigEntity.DEFAULT_MAX_HEALTH_JSON_TAG);
        JsonElement defaultRoundMultiplierJsonElement = jsonObject.get(EnemyConfigEntity.DEFAULT_ROUND_MULTIPLIER_JSON_TAG);

        if (defaultDespawnTimeJsonElement != null)
            config.setDefaultDespawnTime(defaultDespawnTimeJsonElement.getAsInt());

        if (defaultMaxHealthJsonElement != null)
            config.setDefaultMaxHealth(defaultMaxHealthJsonElement.getAsInt());

        if (defaultRoundMultiplierJsonElement != null)
            config.setDefaultRoundMultiplier(defaultRoundMultiplierJsonElement.getAsDouble());

        return config;
    }

    @Override
    public JsonElement serialize(EnemyConfigEntity enemyConfigEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(EnemyConfigEntity.DEFAULT_DESPAWN_TIME_JSON_TAG, new JsonPrimitive(enemyConfigEntity.getDefaultDespawnTime()));
        jsonObject.add(EnemyConfigEntity.DEFAULT_MAX_HEALTH_JSON_TAG, new JsonPrimitive(enemyConfigEntity.getDefaultMaxHealth()));
        jsonObject.add(EnemyConfigEntity.DEFAULT_ROUND_MULTIPLIER_JSON_TAG, new JsonPrimitive(enemyConfigEntity.getDefaultRoundMultiplier()));

        return jsonObject;
    }
    //endregion
}
