package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.PerkEntity;
import com.mango.zombies.entities.PerkServiceEntity;

import java.lang.reflect.Type;

public class PerkEntityJsonSerializer implements JsonSerializer<PerkEntity>, JsonDeserializer<PerkEntity> {

    @Override
    public PerkEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        PerkEntity perk = new PerkEntity();

        perk.setCost(jsonObject.get(PerkEntity.COST_JSON_TAG).getAsInt());
        perk.setDoesRequirePower(jsonObject.get(PerkEntity.DOES_REQUIRE_POWER_JSON_TAG).getAsBoolean());
        perk.setId(jsonObject.get(PerkEntity.ID_JSON_TAG).getAsString());
        perk.setItem(jsonObject.get(PerkEntity.ITEM_JSON_TAG).getAsString());
        perk.setName(jsonObject.get(PerkEntity.NAME_JSON_TAG).getAsString());

        for (JsonElement jsonService : jsonObject.get(PerkEntity.SERVICES_JSON_TAG).getAsJsonArray())
            perk.getServices().add(PerkServiceEntity.SERIALIZER.deserialize(jsonService, PerkServiceEntity.class, jsonDeserializationContext));

        return perk;
    }

    @Override
    public JsonElement serialize(PerkEntity perkEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(PerkEntity.ID_JSON_TAG, new JsonPrimitive(perkEntity.getId()));
        jsonObject.add(PerkEntity.NAME_JSON_TAG, new JsonPrimitive(perkEntity.getName()));
        jsonObject.add(PerkEntity.COST_JSON_TAG, new JsonPrimitive(perkEntity.getCost()));
        jsonObject.add(PerkEntity.DOES_REQUIRE_POWER_JSON_TAG, new JsonPrimitive(perkEntity.doesRequirePower()));
        jsonObject.add(PerkEntity.ITEM_JSON_TAG, new JsonPrimitive(perkEntity.getItem()));

        JsonArray servicesArray = new JsonArray();

        for (PerkServiceEntity service : perkEntity.getServices())
            servicesArray.add(PerkServiceEntity.SERIALIZER.serialize(service, PerkServiceEntity.class, jsonSerializationContext));

        jsonObject.add(PerkEntity.SERVICES_JSON_TAG, servicesArray);

        return jsonObject;
    }
}
