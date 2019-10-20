package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponServiceEntity;

import java.lang.reflect.Type;

public class WeaponClassEntityJsonSerializer implements JsonSerializer<WeaponClassEntity>, JsonDeserializer<WeaponClassEntity> {

    @Override
    public WeaponClassEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponClassEntity weaponClass = new WeaponClassEntity();

        weaponClass.setColor(jsonObject.get(WeaponClassEntity.COLOR_JSON_TAG).getAsString());
        weaponClass.setDefaultItem(jsonObject.get(WeaponClassEntity.DEFAULT_ITEM_JSON_TAG).getAsString());
        weaponClass.setDefaultWeaponCost(jsonObject.get(WeaponClassEntity.DEFAULT_WEAPON_COST_JSON_TAG).getAsInt());
        weaponClass.setId(jsonObject.get(WeaponClassEntity.ID_JSON_TAG).getAsString());
        weaponClass.setName(jsonObject.get(WeaponClassEntity.NAME_JSON_TAG).getAsString());

        for (JsonElement jsonService : jsonObject.get(WeaponClassEntity.DEFAULT_SERVICES_JSON_TAG).getAsJsonArray())
            weaponClass.getDefaultServices().add(WeaponServiceEntity.SERIALIZER.deserialize(jsonService, WeaponServiceEntity.class, jsonDeserializationContext));

        return weaponClass;
    }

    @Override
    public JsonElement serialize(WeaponClassEntity weaponClassEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponClassEntity.ID_JSON_TAG, new JsonPrimitive(weaponClassEntity.getId()));
        jsonObject.add(WeaponClassEntity.NAME_JSON_TAG, new JsonPrimitive(weaponClassEntity.getName()));
        jsonObject.add(WeaponClassEntity.DEFAULT_WEAPON_COST_JSON_TAG, new JsonPrimitive(weaponClassEntity.getDefaultWeaponCost()));
        jsonObject.add(WeaponClassEntity.COLOR_JSON_TAG, new JsonPrimitive(weaponClassEntity.getColor()));
        jsonObject.add(WeaponClassEntity.DEFAULT_ITEM_JSON_TAG, new JsonPrimitive(weaponClassEntity.getDefaultItem()));

        JsonArray servicesArray = new JsonArray();

        for (WeaponServiceEntity service : weaponClassEntity.getDefaultServices())
            servicesArray.add(WeaponServiceEntity.SERIALIZER.serialize(service, WeaponServiceEntity.class, jsonSerializationContext));

        jsonObject.add(WeaponClassEntity.DEFAULT_SERVICES_JSON_TAG, servicesArray);

        return jsonObject;
    }
}
