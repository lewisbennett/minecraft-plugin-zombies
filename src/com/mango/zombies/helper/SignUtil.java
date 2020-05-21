package com.mango.zombies.helper;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.*;
import com.mango.zombies.schema.Sign;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class SignUtil {

    //region Constant Values
    public static final String INVALID_PERK_ERROR = "Sign not created. %s is not a valid perk.";
    public static final String INVALID_PERK_ERROR_GENERIC = "Sign not created. Invalid perk.";
    public static final String INVALID_TYPE_ERROR = "Sign not created. %s is not a valid sign type.";
    public static final String INVALID_WEAPON_ERROR = "Sign not created. %s is not a valid weapon.";
    public static final String INVALID_WEAPON_ERROR_GENERIC = "Sign not created. Invalid weapon.";
    //endregion

    //region Public Static Methods
    /**
     * Gets the formatted lines to show on a sign.
     * @param mapEntity The map that the sign belongs to.
     * @param signEntity The sign configuration.
     */
    public static String[] getLinesForSign(MapEntity mapEntity, SignEntity signEntity) throws IllegalArgumentException {

        List<String> lines = new ArrayList<String>();

        lines.add(ChatColor.RED + Sign.HEADER);

        String[] bottomLines;

        switch (signEntity.getType()) {

            case Sign.DOOR:
                bottomLines = createDoorSign(mapEntity, signEntity);
                break;

            case Sign.JOIN:
                bottomLines = createJoinSign(mapEntity, signEntity);
                break;

            case Sign.MYSTERY_BOX:
                bottomLines = createMysteryBoxSign(mapEntity, signEntity);
                break;

            case Sign.PACK_A_PUNCH:
                bottomLines = createPackAPunchSign(mapEntity, signEntity);
                break;

            case Sign.PERK:
                bottomLines = createPerkSign(mapEntity, signEntity);
                break;

            case Sign.POWER:
                bottomLines = createPowerSign(mapEntity, signEntity);
                break;

            case Sign.SPECTATE:
                bottomLines = createSpectateSign(mapEntity, signEntity);
                break;

            case Sign.WEAPON:
                bottomLines = createWeaponSign(mapEntity, signEntity);
                break;

            default:
                throw new IllegalArgumentException(String.format(INVALID_TYPE_ERROR, signEntity.getType()));
        }

        for (int i = 0; i < bottomLines.length; i++)
            lines.add((i == 0 ? ChatColor.AQUA : ChatColor.BLACK) + bottomLines[i]);

        return lines.toArray(new String[0]);
    }
    //endregion

    //region Private Static Methods
    private static String[] createDoorSign(MapEntity mapEntity, SignEntity signEntity) {

        return new String[] {
                "Door",
                "Cost: X"
        };
    }

    private static String[] createJoinSign(MapEntity mapEntity, SignEntity signEntity) {

        return new String[]{
                "Join Game",
                mapEntity.getName()
        };
    }

    private static String[] createMysteryBoxSign(MapEntity mapEntity, SignEntity signEntity) throws IllegalArgumentException {

        return new String[]{
                "Mystery Box",
                "Cost: " + mapEntity.getMysteryBoxCost()
        };
    }

    private static String[] createPackAPunchSign(MapEntity mapEntity, SignEntity signEntity) throws IllegalArgumentException {

        return new String[]{
                "Pack-A-Punch",
                "Cost: " + mapEntity.getPackAPunchCost()
        };
    }

    private static String[] createPerkSign(MapEntity mapEntity, SignEntity signEntity) throws IllegalArgumentException {

        List<String> lines = new ArrayList<String>();

        PerkEntity perkEntity = null;

        for (PerkEntity queryPerk : PluginCore.getPerks()) {

            if (queryPerk.getId().equals(signEntity.getResourceValue())) {
                perkEntity = queryPerk;
                break;
            }
        }

        if (perkEntity == null)
            throw new IllegalArgumentException(signEntity.getResourceValue().isEmpty() ? INVALID_PERK_ERROR_GENERIC : String.format(INVALID_PERK_ERROR, signEntity.getResourceValue()));

        lines.add(perkEntity.getName());
        lines.add("Cost: " + perkEntity.getCost());

        return lines.toArray(new String[0]);
    }

    private static String[] createPowerSign(MapEntity mapEntity, SignEntity signEntity) {

        return new String[] {
                "Power"
        };
    }

    private static String[] createSpectateSign(MapEntity mapEntity, SignEntity signEntity) {

        return new String[]{
                "Spectate Game",
                mapEntity.getName()
        };
    }

    private static String[] createWeaponSign(MapEntity mapEntity, SignEntity signEntity) throws IllegalArgumentException {

        List<String> lines = new ArrayList<String>();

        WeaponEntity weaponEntity = null;

        for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

            if (queryWeapon.getId().equals(signEntity.getResourceValue())) {
                weaponEntity = queryWeapon;
                break;
            }
        }

        if (weaponEntity == null)
            throw new IllegalArgumentException(signEntity.getResourceType().isEmpty() ? INVALID_WEAPON_ERROR_GENERIC : String.format(INVALID_WEAPON_ERROR, signEntity.getResourceValue()));

        lines.add(weaponEntity.getName());
        lines.add("Cost: " + weaponEntity.getCost());

        WeaponServiceEntity standardGunshotService = weaponEntity.getService(WeaponService.GUNSHOT, false);
        WeaponServiceEntity packAPunchGunshotService = weaponEntity.getService(WeaponService.GUNSHOT, true);

        if (standardGunshotService != null) {

            int standardAmmoCost = weaponEntity.getAmmoCost(standardGunshotService);

            String ammoCost = "Ammo: " + standardAmmoCost;

            if (packAPunchGunshotService != null) {

                int packAPunchAmmoCost = weaponEntity.getAmmoCost(packAPunchGunshotService);

                if (packAPunchAmmoCost != standardAmmoCost)
                    ammoCost += "/" + packAPunchAmmoCost;
            }

            lines.add(ammoCost);
        }

        return lines.toArray(new String[0]);
    }
    //endregion
}
