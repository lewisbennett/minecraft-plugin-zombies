package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class WeaponServiceCharacteristicEntityJsonSerializer implements JsonSerializer<WeaponServiceCharacteristicEntity>, JsonDeserializer<WeaponServiceCharacteristicEntity> {

    //region Public Methods
    @Override
    public WeaponServiceCharacteristicEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponServiceCharacteristicEntity characteristic = new WeaponServiceCharacteristicEntity();
        
        JsonElement typeJsonElement = jsonObject.get(WeaponServiceCharacteristicEntity.TYPE_JSON_TAG);
        JsonElement valueJsonElement = jsonObject.get(WeaponServiceCharacteristicEntity.VALUE_JSON_TAG);
        
        if (typeJsonElement != null)
            characteristic.setType(typeJsonElement.getAsString());
        
        if (valueJsonElement != null) {

            Object o = null;
            JsonPrimitive valuePrimitive = valueJsonElement.getAsJsonPrimitive();

            if (valuePrimitive.isBoolean())
                o = valueJsonElement.getAsBoolean();

            else if (valuePrimitive.isNumber())
                o = valueJsonElement.getAsInt();

            else if (valuePrimitive.isString())
                o = valueJsonElement.getAsString();

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

        else if (weaponServiceCharacteristicEntity.getValue() instanceof  Double)
            valueJson = new JsonPrimitive((Double)weaponServiceCharacteristicEntity.getValue());

        else if (weaponServiceCharacteristicEntity.getValue() instanceof Sound)
            valueJson = new JsonPrimitive(((Sound)weaponServiceCharacteristicEntity.getValue()).name());

        else
            valueJson = new JsonPrimitive((String)weaponServiceCharacteristicEntity.getValue());

        jsonObject.add(WeaponServiceCharacteristicEntity.VALUE_JSON_TAG, valueJson);
        jsonObject.add(WeaponServiceCharacteristicEntity.TYPE_JSON_TAG, new JsonPrimitive(weaponServiceCharacteristicEntity.getType()));

        return jsonObject;
    }
    //endregion
}
