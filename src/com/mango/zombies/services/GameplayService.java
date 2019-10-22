package com.mango.zombies.services;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.gameplay.GameplayCore;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.helper.HiddenStringUtils;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameplayService {

    //region Constant Values
    public static final String WEAPON_CLASS_DOES_NOT_EXIST_ERROR = "Could not find weapon class \"%s\". This weapon cannot be used.";
    public static final String WEAPON_DOES_NOT_EXIST_ERROR = "%s is not a valid weapon ID.";
    //endregion

    //region Public Methods
    /**
     * Gives a player a weapon.
     */
    public static Tuple<ItemStack, GameplayWeapon> giveWeapon(Player player, String weaponId) {

        WeaponEntity weapon = null;

        for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

            if (queryWeapon.getId().equalsIgnoreCase(weaponId)) {
                weapon = queryWeapon;
                break;
            }
        }

        if (weapon == null) {
            MessagingService.showError(player, String.format(WEAPON_DOES_NOT_EXIST_ERROR, weaponId));
            return null;
        }

        WeaponClassEntity weaponClass = weapon.getWeaponClass();

        if (weaponClass == null) {
            MessagingService.showError(player, String.format(WEAPON_CLASS_DOES_NOT_EXIST_ERROR, weapon.getWeaponClassId()));
            return null;
        }

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = new ItemStack(Material.getMaterial(weapon.getItem()), 1);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.valueOf(weaponClass.getColor()) + weapon.getName());

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        GameplayWeapon gameplayWeapon = new GameplayWeapon(weapon);
        GameplayCore.getWeapons().put(uuid, gameplayWeapon);

        return new Tuple<ItemStack, GameplayWeapon>(itemStack, gameplayWeapon);
    }
    //endregion
}
