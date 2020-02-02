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

        JsonElement accuracyJson = jsonObject.get(WeaponServiceEntity.ACCURACY_JSON_TAG);
        JsonElement damageJson = jsonObject.get(WeaponServiceEntity.DAMAGE_JSON_TAG);
        JsonElement doesRequirePackAPunchJson = jsonObject.get(WeaponServiceEntity.DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG);
        JsonElement typeJson = jsonObject.get(WeaponServiceEntity.TYPE_JSON_TAG);
        JsonElement usageSoundJson = jsonObject.get(WeaponServiceEntity.USAGE_SOUND_JSON_TAG);

        if (accuracyJson != null)
            service.setAccuracy(accuracyJson.getAsInt());

        if (damageJson != null)
            service.setDamage(damageJson.getAsInt());

        if (doesRequirePackAPunchJson != null)
            service.setDoesRequirePackAPunch(doesRequirePackAPunchJson.getAsBoolean());

        if (typeJson != null)
            service.setType(typeJson.getAsString());

        if (usageSoundJson != null)
            service.setUsageSound(Sound.valueOf(usageSoundJson.getAsString()));

        for (JsonElement jsonCharacteristic : jsonObject.get(WeaponServiceEntity.CHARACTERISTICS_JSON_TAG).getAsJsonArray())
            service.getCharacteristics().add(WeaponServiceCharacteristicEntity.SERIALIZER.deserialize(jsonCharacteristic, WeaponServiceCharacteristicEntity.class, jsonDeserializationContext));

        return service;
    }

    @Override
    public JsonElement serialize(WeaponServiceEntity weaponServiceEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponServiceEntity.DAMAGE_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getDamage()));
        jsonObject.add(WeaponServiceEntity.ACCURACY_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getAccuracy()));
        jsonObject.add(WeaponServiceEntity.DOES_REQUIRE_PACK_A_PUNCH_JSON_TAG, new JsonPrimitive(weaponServiceEntity.doesRequirePackAPunch()));
        jsonObject.add(WeaponServiceEntity.TYPE_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getType()));
        jsonObject.add(WeaponServiceEntity.USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponServiceEntity.getUsageSound().name()));

        JsonArray characteristicsArray = new JsonArray();

        for (WeaponServiceCharacteristicEntity characteristic : weaponServiceEntity.getCharacteristics())
            characteristicsArray.add(WeaponServiceCharacteristicEntity.SERIALIZER.serialize(characteristic, WeaponServiceCharacteristicEntity.class, jsonSerializationContext));

        jsonObject.add(WeaponServiceEntity.CHARACTERISTICS_JSON_TAG, characteristicsArray);

        return jsonObject;
    }
    //endregion
}
