package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.PerkEntity;
import org.bukkit.Material;

import java.lang.reflect.Type;

public class PerkEntityJsonSerializer implements JsonSerializer<PerkEntity>, JsonDeserializer<PerkEntity> {

    //region Public Methods
    @Override
    public PerkEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        PerkEntity perk = new PerkEntity();

        JsonElement costJsonElement = jsonObject.get(PerkEntity.COST_JSON_TAG);
        JsonElement doesRequirePowerJsonElement = jsonObject.get(PerkEntity.DOES_REQUIRE_POWER_JSON_TAG);
        JsonElement idJsonElement = jsonObject.get(PerkEntity.ID_JSON_TAG);
        JsonElement itemJsonElement = jsonObject.get(PerkEntity.ITEM_JSON_TAG);
        JsonElement nameJsonElement = jsonObject.get(PerkEntity.NAME_JSON_TAG);

        if (costJsonElement != null)
            perk.setCost(costJsonElement.getAsInt());

        if (doesRequirePowerJsonElement != null)
            perk.setDoesRequirePower(doesRequirePowerJsonElement.getAsBoolean());

        if (idJsonElement != null)
            perk.setId(idJsonElement.getAsString());

        if (itemJsonElement != null)
            perk.setItem(Material.valueOf(itemJsonElement.getAsString()));

        if (nameJsonElement != null)
            perk.setName(nameJsonElement.getAsString());

        return perk;
    }

    @Override
    public JsonElement serialize(PerkEntity perkEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(PerkEntity.ID_JSON_TAG, new JsonPrimitive(perkEntity.getId()));
        jsonObject.add(PerkEntity.NAME_JSON_TAG, new JsonPrimitive(perkEntity.getName()));
        jsonObject.add(PerkEntity.COST_JSON_TAG, new JsonPrimitive(perkEntity.getCost()));
        jsonObject.add(PerkEntity.DOES_REQUIRE_POWER_JSON_TAG, new JsonPrimitive(perkEntity.doesRequirePower()));
        jsonObject.add(PerkEntity.ITEM_JSON_TAG, new JsonPrimitive(perkEntity.getItem().name()));

        return jsonObject;
    }
    //endregion
}
