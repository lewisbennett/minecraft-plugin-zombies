package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;

import java.lang.reflect.Type;

public class WeaponServiceCharacteristicEntityJsonSerializer implements JsonSerializer<WeaponServiceCharacteristicEntity>, JsonDeserializer<WeaponServiceCharacteristicEntity> {

    //region Public Methods
    @Override
    public WeaponServiceCharacteristicEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        WeaponServiceCharacteristicEntity characteristic = new WeaponServiceCharacteristicEntity();
        
        JsonElement typeJson = jsonObject.get(WeaponServiceCharacteristicEntity.TYPE_JSON_TAG);
        JsonElement valueJson = jsonObject.get(WeaponServiceCharacteristicEntity.VALUE_JSON_TAG);
        
        if (typeJson != null)
            characteristic.setType(typeJson.getAsString());
        
        if (valueJson != null) {

            Object o = null;
            JsonPrimitive valuePrimitive = valueJson.getAsJsonPrimitive();
            
            if (valuePrimitive.isBoolean())
                o = valueJson.getAsBoolean();
            else if (valuePrimitive.isNumber())
                o = valueJson.getAsInt();
            else if (valuePrimitive.isString())
                o = valueJson.getAsString();

            characteristic.setValue(o);
        }

        return characteristic;
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
        jsonObject.add(WeaponServiceCharacteristicEntity.TYPE_JSON_TAG, new JsonPrimitive(weaponServiceCharacteristicEntity.getType()));

        return jsonObject;
    }
    //endregion
}
