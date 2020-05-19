package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.entities.WeaponServiceEntity;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class WeaponEntityJsonSerializer implements JsonSerializer<WeaponEntity>, JsonDeserializer<WeaponEntity> {

    //region Public Methods
    @Override
    public WeaponEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponEntity weapon = new WeaponEntity();

        JsonElement costJsonElement = jsonObject.get(WeaponEntity.COST_JSON_TAG);
        JsonElement idJsonElement = jsonObject.get(WeaponEntity.ID_JSON_TAG);
        JsonElement itemJsonElement = jsonObject.get(WeaponEntity.ITEM_JSON_TAG);
        JsonElement nameJsonElement = jsonObject.get(WeaponEntity.NAME_JSON_TAG);
        JsonElement packAPunchNameJsonElement = jsonObject.get(WeaponEntity.PACK_A_PUNCH_NAME_JSON_TAG);
        JsonElement servicesJsonElement = jsonObject.get(WeaponEntity.SERVICES_JSON_TAG);
        JsonElement weaponColorJsonElement = jsonObject.get(WeaponEntity.WEAPON_COLOR_JSON_TAG);

        if (costJsonElement != null)
            weapon.setCost(costJsonElement.getAsInt());

        if (idJsonElement != null)
            weapon.setId(idJsonElement.getAsString());

        if (itemJsonElement != null)
            weapon.setItem(Material.valueOf(itemJsonElement.getAsString()));

        if (nameJsonElement != null)
            weapon.setName(nameJsonElement.getAsString());

        if (packAPunchNameJsonElement != null)
            weapon.setPackAPunchName(packAPunchNameJsonElement.getAsString());

        if (servicesJsonElement != null) {

            for (JsonElement jsonService : servicesJsonElement.getAsJsonArray())
                weapon.addService(WeaponServiceEntity.SERIALIZER.deserialize(jsonService, WeaponServiceEntity.class, jsonDeserializationContext));
        }

        if (weaponColorJsonElement != null)
            weapon.setWeaponColor(ChatColor.valueOf(weaponColorJsonElement.getAsString()));

        return weapon;
    }

    @Override
    public JsonElement serialize(WeaponEntity weaponEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponEntity.ID_JSON_TAG, new JsonPrimitive(weaponEntity.getId()));
        jsonObject.add(WeaponEntity.NAME_JSON_TAG, new JsonPrimitive(weaponEntity.getName()));
        jsonObject.add(WeaponEntity.COST_JSON_TAG, new JsonPrimitive(weaponEntity.getCost()));
        jsonObject.add(WeaponEntity.PACK_A_PUNCH_NAME_JSON_TAG, new JsonPrimitive(weaponEntity.getPackAPunchName()));
        jsonObject.add(WeaponEntity.ITEM_JSON_TAG, new JsonPrimitive(weaponEntity.getItem().name()));
        jsonObject.add(WeaponEntity.WEAPON_COLOR_JSON_TAG, new JsonPrimitive(weaponEntity.getWeaponColor().name()));

        JsonArray servicesArray = new JsonArray();

        for (WeaponServiceEntity service : weaponEntity.getServices())
            servicesArray.add(WeaponServiceEntity.SERIALIZER.serialize(service, WeaponServiceEntity.class, jsonSerializationContext));

        jsonObject.add(WeaponEntity.SERVICES_JSON_TAG, servicesArray);

        return jsonObject;
    }
    //endregion
}
