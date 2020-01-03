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
        enemy.setId(jsonObject.get(EnemyEntity.ID_JSON_TAG).getAsString());
        enemy.setDespawnTime(jsonObject.get(EnemyEntity.DESPAWN_TIME_JSON_TAG).getAsInt());
        enemy.setEntityType(EntityType.valueOf(jsonObject.get(EnemyEntity.ENTITY_TYPE_JSON_TAG).getAsString()));
        enemy.setMaxHealth(jsonObject.get(EnemyEntity.MAX_HEALTH_JSON_TAG).getAsInt());
        enemy.setRoundOneHealth(jsonObject.get(EnemyEntity.ROUND_ONE_HEALTH_JSON_TAG).getAsInt());

        return enemy;
    }

    @Override
    public JsonElement serialize(EnemyEntity enemyEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(EnemyEntity.ID_JSON_TAG, new JsonPrimitive(enemyEntity.getId()));
        jsonObject.add(EnemyEntity.ENTITY_TYPE_JSON_TAG, new JsonPrimitive(enemyEntity.getEntityType().name()));
        jsonObject.add(EnemyEntity.ROUND_ONE_HEALTH_JSON_TAG, new JsonPrimitive(enemyEntity.getRoundOneHealth()));
        jsonObject.add(EnemyEntity.MAX_HEALTH_JSON_TAG, new JsonPrimitive(enemyEntity.getMaxHealth()));
        jsonObject.add(EnemyEntity.DESPAWN_TIME_JSON_TAG, new JsonPrimitive(enemyEntity.getDespawnTime()));

        return jsonObject;
    }
    //endregion
}
