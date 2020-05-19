package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.EnemyEntity;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Type;

public class EnemyEntityJsonSerializer implements JsonSerializer<EnemyEntity>, JsonDeserializer<EnemyEntity> {

    //region Public Methods
    @Override
    public EnemyEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        EnemyEntity enemy = new EnemyEntity();

        JsonElement despawnTimeJsonElement = jsonObject.get(EnemyEntity.DESPAWN_TIME_JSON_TAG);
        JsonElement entityTypeJsonElement = jsonObject.get(EnemyEntity.ENTITY_TYPE_JSON_TAG);
        JsonElement idJsonElement = jsonObject.get(EnemyEntity.ID_JSON_TAG);
        JsonElement maxHealthJsonElement = jsonObject.get(EnemyEntity.MAX_HEALTH_JSON_TAG);
        JsonElement roundMultiplierJsonElement = jsonObject.get(EnemyEntity.ROUND_MULTIPLIER_JSON_TAG);

        if (despawnTimeJsonElement != null)
            enemy.setDespawnTime(despawnTimeJsonElement.getAsInt());

        if (entityTypeJsonElement != null)
            enemy.setEntityType(EntityType.valueOf(entityTypeJsonElement.getAsString()));

        if (idJsonElement != null)
            enemy.setId(idJsonElement.getAsString());

        if (maxHealthJsonElement != null)
            enemy.setMaxHealth(maxHealthJsonElement.getAsInt());

        if (roundMultiplierJsonElement != null)
            enemy.setRoundMultiplier(roundMultiplierJsonElement.getAsDouble());

        return enemy;
    }

    @Override
    public JsonElement serialize(EnemyEntity enemyEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(EnemyEntity.ID_JSON_TAG, new JsonPrimitive(enemyEntity.getId()));
        jsonObject.add(EnemyEntity.ENTITY_TYPE_JSON_TAG, new JsonPrimitive(enemyEntity.getEntityType().name()));
        jsonObject.add(EnemyEntity.MAX_HEALTH_JSON_TAG, new JsonPrimitive(enemyEntity.getMaxHealth()));
        jsonObject.add(EnemyEntity.ROUND_MULTIPLIER_JSON_TAG, new JsonPrimitive(enemyEntity.getRoundMultiplier()));
        jsonObject.add(EnemyEntity.DESPAWN_TIME_JSON_TAG, new JsonPrimitive(enemyEntity.getDespawnTime()));

        return jsonObject;
    }
    //endregion
}
