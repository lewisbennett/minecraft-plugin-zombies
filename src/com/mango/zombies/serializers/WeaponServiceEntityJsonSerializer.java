package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponServiceCharacteristicEntity;
import com.mango.zombies.entities.WeaponServiceEntity;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class WeaponServiceEntityJsonSerializer implements JsonSerializer<WeaponServiceEntity>, JsonDeserializer<WeaponServiceEntity> {

    //region Public Methods
    @Override
    public WeaponServiceEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponServiceEntity service = new WeaponServiceEntity();

        JsonElement characteristicsJsonElement = jsonObject.get(WeaponServiceEntity.CHARACTERISTICS_JSON_TAG);
        JsonElement damageJsonElement = jsonObject.get(WeaponServiceEntity.DAMAGE_JSON_TAG);
        JsonElement doesRequirePackAPunchJsonElement = jsonObject.get(WeaponServiceEntity.DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG);
        JsonElement typeJsonElement = jsonObject.get(WeaponServiceEntity.TYPE_JSON_TAG);
        JsonElement usageSoundJsonElement = jsonObject.get(WeaponServiceEntity.USAGE_SOUND_JSON_TAG);

        if (characteristicsJsonElement != null) {

            for (JsonElement jsonCharacteristic : characteristicsJsonElement.getAsJsonArray())
                service.addCharacteristic(WeaponServiceCharacteristicEntity.SERIALIZER.deserialize(jsonCharacteristic, WeaponServiceCharacteristicEntity.class, jsonDeserializationContext));
        }

        if (damageJsonElement != null)
            service.setDamage(damageJsonElement.getAsInt());

        if (doesRequirePackAPunchJsonElement != null)
            service.setDoesRequirePackAPunch(doesRequirePackAPunchJsonElement.getAsBoolean());

        if (typeJsonElement != null)
            service.setType(typeJsonElement.getAsString());

        if (usageSoundJsonElement != null)
            service.setUsageSound(Sound.valueOf(usageSoundJsonElement.getAsString()));

        return service;
    }

    @Override
    public JsonElement serialize(WeaponServiceEntity weaponServiceEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponServiceEntity.DAMAGE_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getDamage()));
        jsonObject.add(WeaponServiceEntity.DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG, new JsonPrimitive(weaponServiceEntity.doesRequirePackAPunch()));
        jsonObject.add(WeaponServiceEntity.USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getUsageSound().name()));
        jsonObject.add(WeaponServiceEntity.TYPE_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getType()));

        JsonArray characteristicsArray = new JsonArray();

        for (WeaponServiceCharacteristicEntity characteristic : weaponServiceEntity.getCharacteristics())
            characteristicsArray.add(WeaponServiceCharacteristicEntity.SERIALIZER.serialize(characteristic, WeaponServiceCharacteristicEntity.class, jsonSerializationContext));

        jsonObject.add(WeaponServiceEntity.CHARACTERISTICS_JSON_TAG, characteristicsArray);

        return jsonObject;
    }
    //endregion
}
