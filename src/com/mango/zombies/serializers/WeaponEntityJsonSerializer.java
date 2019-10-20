package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.entities.WeaponServiceEntity;

import java.lang.reflect.Type;

public class WeaponEntityJsonSerializer implements JsonSerializer<WeaponEntity>, JsonDeserializer<WeaponEntity> {

    @Override
    public WeaponEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponEntity weapon = new WeaponEntity();

        weapon.setId(jsonObject.get(WeaponEntity.ID_JSON_TAG).getAsString());
        weapon.setItem(jsonObject.get(WeaponEntity.ITEM_JSON_TAG).getAsString());
        weapon.setName(jsonObject.get(WeaponEntity.NAME_JSON_TAG).getAsString());
        weapon.setIsWonderWeapon(jsonObject.get(WeaponEntity.IS_WONDER_WEAPON_JSON_TAG).getAsBoolean());
        weapon.setWeaponClassId(jsonObject.get(WeaponEntity.WEAPON_CLASS_ID_JSON_TAG).getAsString());

        for (JsonElement jsonService : jsonObject.get(WeaponEntity.SERVICES_JSON_TAG).getAsJsonArray())
            weapon.getServices().add(WeaponServiceEntity.SERIALIZER.deserialize(jsonService, WeaponServiceEntity.class, jsonDeserializationContext));

        return weapon;
    }

    @Override
    public JsonElement serialize(WeaponEntity weaponEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponEntity.ID_JSON_TAG, new JsonPrimitive(weaponEntity.getId()));
        jsonObject.add(WeaponEntity.NAME_JSON_TAG, new JsonPrimitive(weaponEntity.getName()));
        jsonObject.add(WeaponEntity.WEAPON_CLASS_ID_JSON_TAG, new JsonPrimitive(weaponEntity.getWeaponClassId()));
        jsonObject.add(WeaponEntity.IS_WONDER_WEAPON_JSON_TAG, new JsonPrimitive(weaponEntity.isWonderWeapon()));
        jsonObject.add(WeaponEntity.ITEM_JSON_TAG, new JsonPrimitive(weaponEntity.getItem()));

        JsonArray servicesArray = new JsonArray();

        for (WeaponServiceEntity service : weaponEntity.getServices())
            servicesArray.add(WeaponServiceEntity.SERIALIZER.serialize(service, WeaponServiceEntity.class, jsonSerializationContext));

        jsonObject.add(WeaponEntity.SERVICES_JSON_TAG, servicesArray);

        return jsonObject;
    }
}
