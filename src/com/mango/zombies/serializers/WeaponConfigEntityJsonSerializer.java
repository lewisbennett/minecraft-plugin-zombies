package com.mango.zombies.serializers;

import com.google.gson.*;
import com.mango.zombies.entities.WeaponConfigEntity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.lang.reflect.Type;

public class WeaponConfigEntityJsonSerializer implements JsonSerializer<WeaponConfigEntity>, JsonDeserializer<WeaponConfigEntity> {

    //region Public Methods
    @Override
    public WeaponConfigEntity deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        WeaponConfigEntity config = new WeaponConfigEntity();

        JsonElement enableEggProjectileHatchingJsonElement = jsonObject.get(WeaponConfigEntity.ENABLE_EGG_PROJECTILE_HATCHING_JSON_TAG);

        JsonElement ammoIndicatorColorJsonElement = jsonObject.get(WeaponConfigEntity.AMMO_INDICATOR_COLOR_JSON_TAG);
        JsonElement outOfAmmoIndicatorColorJsonElement = jsonObject.get(WeaponConfigEntity.OUT_OF_AMMO_INDICATOR_COLOR_JSON_TAG);
        JsonElement reloadingIndicatorColorJsonElement = jsonObject.get(WeaponConfigEntity.RELOADING_INDICATOR_COLOR_JSON_TAG);
        JsonElement defaultWeaponColorJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_WEAPON_COLOR_JSON_TAG);

        JsonElement defaultAccuracyJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_ACCURACY_JSON_TAG);
        JsonElement defaultPackAPunchAccuracyJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_ACCURACY_JSON_TAG);
        JsonElement defaultGunshotDamageJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_GUNSHOT_DAMAGE_JSON_TAG);
        JsonElement defaultPackAPunchGunshotDamageJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_GUNSHOT_DAMAGE_JSON_TAG);
        JsonElement defaultMagazineCapacityJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_MAGAZINE_CAPACITY_JSON_TAG);
        JsonElement defaultPackAPunchMagazineCapacityJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MAGAZINE_CAPACITY_JSON_TAG);
        JsonElement defaultMeleeDamageJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_MELEE_DAMAGE_JSON_TAG);
        JsonElement defaultPackAPunchMeleeDamageJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MELEE_DAMAGE_JSON_TAG);
        JsonElement defaultProjectileCountJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PROJECTILE_COUNT_JSON_TAG);
        JsonElement defaultPackAPunchProjectileCountJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_PROJECTILE_COUNT_JSON_TAG);
        JsonElement defaultReloadSpeedJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_RELOAD_SPEED_JSON_TAG);
        JsonElement defaultPackAPunchReloadSpeedJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_RELOAD_SPEED_JSON_TAG);
        JsonElement defaultTotalAmmoCapacityJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_TOTAL_AMMO_CAPACITY_JSON_TAG);
        JsonElement defaultPackAPunchTotalAmmoCapacityJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_TOTAL_AMMO_CAPACITY_JSON_TAG);

        JsonElement defaultGunshotItemJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_GUNSHOT_ITEM_JSON_TAG);
        JsonElement defaultMeleeItemJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_MELEE_ITEM_JSON_TAG);

        JsonElement defaultGunshotUsageSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_GUNSHOT_USAGE_SOUND_JSON_TAG);
        JsonElement defaultPackAPunchGunshotUsageSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_GUNSHOT_USAGE_SOUND_JSON_TAG);
        JsonElement defaultMeleeUsageSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_MELEE_USAGE_SOUND_JSON_TAG);
        JsonElement defaultPackAPunchMeleeUsageSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MELEE_USAGE_SOUND_JSON_TAG);
        JsonElement defaultOutOfAmmoSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_OUT_OF_AMMO_SOUND_JSON_TAG);
        JsonElement defaultPackAPunchOutOfAmmoSoundJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_OUT_OF_AMMO_SOUND_JSON_TAG);

        JsonElement defaultProjectieJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PROJECTILE_JSON_TAG);
        JsonElement defaultPackAPunchProjectileJsonElement = jsonObject.get(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_PROJECTILE_JSON_TAG);

        if (enableEggProjectileHatchingJsonElement != null)
            config.setIsEggProjectileHatchingEnabled(enableEggProjectileHatchingJsonElement.getAsBoolean());

        if (ammoIndicatorColorJsonElement != null)
            config.setAmmoIndicatorColor(ChatColor.valueOf(ammoIndicatorColorJsonElement.getAsString()));

        if (outOfAmmoIndicatorColorJsonElement != null)
            config.setOutOfAmmoIndicatorColor(ChatColor.valueOf(outOfAmmoIndicatorColorJsonElement.getAsString()));

        if (reloadingIndicatorColorJsonElement != null)
            config.setReloadingIndicatorColor(ChatColor.valueOf(reloadingIndicatorColorJsonElement.getAsString()));

        if (defaultWeaponColorJsonElement != null)
            config.setDefaultWeaponColor(ChatColor.valueOf(defaultWeaponColorJsonElement.getAsString()));

        if (defaultAccuracyJsonElement != null)
            config.setDefaultAccuracy(defaultAccuracyJsonElement.getAsInt());

        if (defaultPackAPunchAccuracyJsonElement != null)
            config.setDefaultPackAPunchAccuracy(defaultPackAPunchAccuracyJsonElement.getAsInt());

        if (defaultGunshotDamageJsonElement != null)
            config.setDefaultGunshotDamage(defaultGunshotDamageJsonElement.getAsInt());

        if (defaultPackAPunchGunshotDamageJsonElement != null)
            config.setDefaultPackAPunchGunshotDamage(defaultPackAPunchGunshotDamageJsonElement.getAsInt());

        if (defaultMagazineCapacityJsonElement != null)
            config.setDefaultMagazineCapacity(defaultMagazineCapacityJsonElement.getAsInt());

        if (defaultPackAPunchMagazineCapacityJsonElement != null)
            config.setDefaultPackAPunchMagazineCapacity(defaultPackAPunchMagazineCapacityJsonElement.getAsInt());

        if (defaultMeleeDamageJsonElement != null)
            config.setDefaultMeleeDamage(defaultMeleeDamageJsonElement.getAsInt());

        if (defaultPackAPunchMeleeDamageJsonElement != null)
            config.setDefaultPackAPunchMeleeDamage(defaultPackAPunchMeleeDamageJsonElement.getAsInt());

        if (defaultProjectileCountJsonElement != null)
            config.setDefaultProjectileCount(defaultProjectileCountJsonElement.getAsInt());

        if (defaultPackAPunchProjectileCountJsonElement != null)
            config.setDefaultPackAPunchProjectileCount(defaultPackAPunchProjectileCountJsonElement.getAsInt());

        if (defaultReloadSpeedJsonElement != null)
            config.setDefaultReloadSpeed(defaultReloadSpeedJsonElement.getAsInt());

        if (defaultPackAPunchReloadSpeedJsonElement != null)
            config.setDefaultPackAPunchReloadSpeed(defaultPackAPunchReloadSpeedJsonElement.getAsInt());

        if (defaultTotalAmmoCapacityJsonElement != null)
            config.setDefaultTotalAmmoCapacity(defaultTotalAmmoCapacityJsonElement.getAsInt());

        if (defaultPackAPunchTotalAmmoCapacityJsonElement != null)
            config.setDefaultPackAPunchTotalAmmoCapacity(defaultPackAPunchTotalAmmoCapacityJsonElement.getAsInt());

        if (defaultGunshotItemJsonElement != null)
            config.setDefaultGunshotItem(Material.valueOf(defaultGunshotItemJsonElement.getAsString()));

        if (defaultMeleeItemJsonElement != null)
            config.setDefaultMeleeItem(Material.valueOf(defaultMeleeItemJsonElement.getAsString()));

        if (defaultGunshotUsageSoundJsonElement != null)
            config.setDefaultGunshotUsageSound(Sound.valueOf(defaultGunshotUsageSoundJsonElement.getAsString()));

        if (defaultPackAPunchGunshotUsageSoundJsonElement != null)
            config.setDefaultPackAPunchGunshotUsageSound(Sound.valueOf(defaultPackAPunchGunshotUsageSoundJsonElement.getAsString()));

        if (defaultMeleeUsageSoundJsonElement != null)
            config.setDefaultMeleeUsageSound(Sound.valueOf(defaultMeleeUsageSoundJsonElement.getAsString()));

        if (defaultPackAPunchMeleeUsageSoundJsonElement != null)
            config.setDefaultPackAPunchMeleeUsageSound(Sound.valueOf(defaultPackAPunchMeleeUsageSoundJsonElement.getAsString()));

        if (defaultOutOfAmmoSoundJsonElement != null)
            config.setDefaultOutOfAmmoSound(Sound.valueOf(defaultOutOfAmmoSoundJsonElement.getAsString()));

        if (defaultPackAPunchOutOfAmmoSoundJsonElement != null)
            config.setDefaultPackAPunchOutOfAmmoSound(Sound.valueOf(defaultPackAPunchOutOfAmmoSoundJsonElement.getAsString()));

        if (defaultProjectieJsonElement != null)
            config.setDefaultProjectile(defaultProjectieJsonElement.getAsString());

        if (defaultPackAPunchProjectileJsonElement != null)
            config.setDefaultPackAPunchProjectile(defaultPackAPunchProjectileJsonElement.getAsString());

        return config;
    }

    @Override
    public JsonElement serialize(WeaponConfigEntity weaponConfigEntity, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject jsonObject = new JsonObject();

        jsonObject.add(WeaponConfigEntity.ENABLE_EGG_PROJECTILE_HATCHING_JSON_TAG, new JsonPrimitive(weaponConfigEntity.isEggProjectileHatchingEnabled()));

        jsonObject.add(WeaponConfigEntity.AMMO_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getAmmoIndicatorColor().name()));
        jsonObject.add(WeaponConfigEntity.OUT_OF_AMMO_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getOutOfAmmoIndicatorColor().name()));
        jsonObject.add(WeaponConfigEntity.RELOADING_INDICATOR_COLOR_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getReloadingIndicatorColor().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_WEAPON_COLOR_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultWeaponColor().name()));

        jsonObject.add(WeaponConfigEntity.DEFAULT_ACCURACY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultAccuracy()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_ACCURACY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchAccuracy()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_GUNSHOT_DAMAGE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultGunshotDamage()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_GUNSHOT_DAMAGE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchGunshotDamage()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_MAGAZINE_CAPACITY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultMagazineCapacity()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MAGAZINE_CAPACITY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchMagazineCapacity()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_MELEE_DAMAGE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultMeleeDamage()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MELEE_DAMAGE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchMeleeDamage()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PROJECTILE_COUNT_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultProjectileCount()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_PROJECTILE_COUNT_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchProjectileCount()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_RELOAD_SPEED_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultReloadSpeed()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_RELOAD_SPEED_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchReloadSpeed()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_TOTAL_AMMO_CAPACITY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultTotalAmmoCapacity()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_TOTAL_AMMO_CAPACITY_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchTotalAmmoCapacity()));

        jsonObject.add(WeaponConfigEntity.DEFAULT_GUNSHOT_ITEM_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultGunshotItem().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_MELEE_ITEM_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultMeleeItem().name()));

        jsonObject.add(WeaponConfigEntity.DEFAULT_GUNSHOT_USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultGunshotUsageSound().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_GUNSHOT_USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchGunshotUsageSound().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_MELEE_USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultMeleeUsageSound().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_MELEE_USAGE_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchMeleeUsageSound().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_OUT_OF_AMMO_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultOutOfAmmoSound().name()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_OUT_OF_AMMO_SOUND_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchOutOfAmmoSound().name()));

        jsonObject.add(WeaponConfigEntity.DEFAULT_PROJECTILE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultProjectile()));
        jsonObject.add(WeaponConfigEntity.DEFAULT_PACK_A_PUNCH_PROJECTILE_JSON_TAG, new JsonPrimitive(weaponConfigEntity.getDefaultPackAPunchProjectile()));

        return jsonObject;
    }
    //endregion
}
