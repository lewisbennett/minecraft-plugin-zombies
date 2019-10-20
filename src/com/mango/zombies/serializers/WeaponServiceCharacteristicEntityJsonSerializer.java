package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;

import java.lang.reflect.Type;
import java.util.UUID;

public class WeaponServiceCharacteristicEntityJsonSerializer implements JsonSerializer<WeaponServiceCharacteristicEntity>, JsonDeserializer<WeaponServiceCharacteristicEntity> {

    @Override
    public WeaponServiceCharacteristicEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponServiceCharacteristicEntity characteristicEntity = new WeaponServiceCharacteristicEntity();

        characteristicEntity.setTypeUUID(UUID.fromString(jsonObject.get(WeaponServiceCharacteristicEntity.TYPE_UUID_JSON_TAG).getAsString()));

        Object o;
        JsonElement jsonValue = jsonObject.get(WeaponServiceCharacteristicEntity.VALUE_JSON_TAG);

        if (jsonValue.getAsJsonPrimitive().isBoolean())
            o = jsonValue.getAsBoolean();
        else if (jsonValue.getAsJsonPrimitive().isNumber())
            o = jsonValue.getAsInt();
        else
            o = jsonValue.getAsString();

        characteristicEntity.setValue(o);

        return characteristicEntity;
    }

    @Override
    public JsonElement serialize(WeaponServiceCharacteristicEntity weaponServiceCharacteristicEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        JsonPrimitive valueJson;

        if (weaponServiceCharacteristicEntity.getValue() instanceof Boolean)
            valueJson = new JsonPrimitive((Boolean)weaponServiceCharacteristicEntity.getValue());
        else if (weaponServiceCharacteristicEntity.getValue() instanceof Integer)
            valueJson = new JsonPrimitive((Integer)weaponServiceCharacteristicEntity.getValue());
        else
            valueJson = new JsonPrimitive((String)weaponServiceCharacteristicEntity.getValue());

        jsonObject.add(WeaponServiceCharacteristicEntity.VALUE_JSON_TAG, valueJson);
        jsonObject.add(WeaponServiceCharacteristicEntity.TYPE_UUID_JSON_TAG, new JsonPrimitive(weaponServiceCharacteristicEntity.getTypeUUID().toString()));

        return jsonObject;
    }
}
