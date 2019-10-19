package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.PerkServiceCharacteristicEntity;
import com.mango.zombies.entities.PerkServiceEntity;

import java.lang.reflect.Type;
import java.util.UUID;

public class PerkServiceEntityJsonSerializer implements JsonSerializer<PerkServiceEntity>, JsonDeserializer<PerkServiceEntity> {

    @Override
    public PerkServiceEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        PerkServiceEntity service = new PerkServiceEntity();

        service.setDescription(jsonObject.get(PerkServiceEntity.DESCRIPTION_JSON_TAG).getAsString());
        service.setTypeUUID(UUID.fromString(jsonObject.get(PerkServiceEntity.TYPE_UUID_JSON_TAG).getAsString()));

        for (JsonElement jsonCharacteristic : jsonObject.get(PerkServiceEntity.CHARACTERISTICS_JSON_TAG).getAsJsonArray())
            service.getCharacteristics().add(PerkServiceCharacteristicEntity.SERIALIZER.deserialize(jsonCharacteristic, PerkServiceCharacteristicEntity.class, jsonDeserializationContext));

        return service;
    }

    @Override
    public JsonElement serialize(PerkServiceEntity perkServiceEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(PerkServiceEntity.DESCRIPTION_JSON_TAG, new JsonPrimitive(perkServiceEntity.getDescription()));
        jsonObject.add(PerkServiceEntity.TYPE_UUID_JSON_TAG, new JsonPrimitive(perkServiceEntity.getTypeUUID().toString()));

        JsonArray characteristicsArray = new JsonArray();

        for (PerkServiceCharacteristicEntity characteristic : perkServiceEntity.getCharacteristics())
            characteristicsArray.add(PerkServiceCharacteristicEntity.SERIALIZER.serialize(characteristic, PerkServiceCharacteristicEntity.class, jsonSerializationContext));

        jsonObject.add(PerkServiceEntity.CHARACTERISTICS_JSON_TAG, characteristicsArray);

        return jsonObject;
    }
}
