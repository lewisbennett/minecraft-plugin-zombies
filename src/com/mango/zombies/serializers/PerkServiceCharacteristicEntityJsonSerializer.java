package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.PerkServiceCharacteristicEntity;
import com.mango.zombies.entities.PerkServiceEntity;

import java.lang.reflect.Type;
import java.util.UUID;

public class PerkServiceCharacteristicEntityJsonSerializer implements JsonSerializer<PerkServiceCharacteristicEntity>, JsonDeserializer<PerkServiceCharacteristicEntity> {

    @Override
    public PerkServiceCharacteristicEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        PerkServiceCharacteristicEntity characteristicEntity = new PerkServiceCharacteristicEntity();

        characteristicEntity.setTypeUUID(UUID.fromString(jsonObject.get(PerkServiceCharacteristicEntity.TYPE_UUID_JSON_TAG).getAsString()));

        Object o;
        JsonElement jsonValue = jsonObject.get(PerkServiceCharacteristicEntity.VALUE_JSON_TAG);

        if (jsonElement.getAsJsonPrimitive().isBoolean())
            o = jsonElement.getAsBoolean();
        else if (jsonElement.getAsJsonPrimitive().isNumber())
            o = jsonElement.getAsDouble();
        else
            o = jsonElement.getAsString();

        characteristicEntity.setValue(o);

        return characteristicEntity;
    }

    @Override
    public JsonElement serialize(PerkServiceCharacteristicEntity perkServiceCharacteristicEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        JsonPrimitive valueJson;

        if (perkServiceCharacteristicEntity.getValue() instanceof Boolean)
            valueJson = new JsonPrimitive((boolean)perkServiceCharacteristicEntity.getValue());
        else if (perkServiceCharacteristicEntity.getValue() instanceof Integer)
            valueJson = new JsonPrimitive((double)perkServiceCharacteristicEntity.getValue());
        else
            valueJson = new JsonPrimitive((String)perkServiceCharacteristicEntity.getValue());

        jsonObject.add(PerkServiceCharacteristicEntity.VALUE_JSON_TAG, valueJson);
        jsonObject.add(PerkServiceEntity.TYPE_UUID_JSON_TAG, new JsonPrimitive(perkServiceCharacteristicEntity.getTypeUUID().toString()));

        return jsonObject;
    }
}
