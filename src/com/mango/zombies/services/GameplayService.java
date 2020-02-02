package com.mango.zombies.services;

import com.mango.zombies.Main;
import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.gameplay.PositionTool;
import com.mango.zombies.gameplay.SpawningTool;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.Positionable;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.Bukkit;
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
    public static final String ENEMY_DOES_NOT_EXIST_ERROR = "%s is not a valid enemy ID.";
    public static final String INVALID_POSITIONABLE_ERROR = "Could not get position tool. You can only configure the position of the following: %s.";
    public static final String MAP_DOES_NOT_EXIST_ERROR = "Could not get position tool. %s is not a valid map ID.";
    public static final String MAP_ENABLED_ERROR = "Could not get position tool. %s must be disabled first.";
    public static final String WEAPON_CLASS_DOES_NOT_EXIST_ERROR = "Could not find weapon class \"%s\". This weapon cannot be used.";
    public static final String WEAPON_DOES_NOT_EXIST_ERROR = "%s is not a valid weapon ID.";
    //endregion

    //region Public Methods
    /**
     * Calculates the health for an enemy on a particular round.
     * @param round The round.
     * @param enemy The enemy to calculate health for.
     */
    public static int calculateHealthForRound(int round, EnemyEntity enemy) {

        int health;

        if (round < 10) {

            health = round * 100 + 50;

            if (enemy.getMaxHealth() > 0)
                return Math.min(enemy.getMaxHealth(), health);

            return health;
        }

        health = (int)(Math.round(950 * Math.pow(1.1, round - 9)));

        if (enemy.getMaxHealth() > 0)
            return Math.min(enemy.getMaxHealth(), health);

        return health;
    }

    /**
     * Gives a player a position tool.
     */
    public static Tuple<ItemStack, PositionTool> givePositionTool(Player player, String mapId, String positionable) {

        List<String> positionables = Positionable.toList();

        if (!positionables.contains(positionable)) {
            MessagingService.showError(player, String.format(INVALID_POSITIONABLE_ERROR, String.join(", ", positionables)));
            return null;
        }

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equalsIgnoreCase(mapId)) {
                map = queryMap;
                break;
            }
        }

        if (map == null) {
            MessagingService.showError(player, String.format(MAP_DOES_NOT_EXIST_ERROR, mapId));
            return null;
        }

        if (map.isEnabled()) {
            MessagingService.showError(player, String.format(MAP_ENABLED_ERROR, map.getId()));
            return null;
        }

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = new ItemStack(Material.STICK, 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Position Tool: " + ChatColor.RESET + "" + ChatColor.GREEN + map.getId() + " (" + positionable + ")");

        itemStack.setItemMeta(itemMeta);

        PositionTool positionTool = new PositionTool(itemStack, map, positionable, uuid);

        Bukkit.getPluginManager().registerEvents(positionTool, Main.getInstance());

        return new Tuple<>(itemStack, positionTool);
    }

    /**
     * Gives a player a spawning tool.
     */
    public static Tuple<ItemStack, SpawningTool> giveSpawningTool(Player player, String enemyId, int spawnRound) {

        EnemyEntity enemy = null;

        for (EnemyEntity queryEnemy : PluginCore.getEnemies()) {

            if (queryEnemy.getId().equalsIgnoreCase(enemyId)) {
                enemy = queryEnemy;
                break;
            }
        }

        if (enemy == null) {
            MessagingService.showError(player, String.format(ENEMY_DOES_NOT_EXIST_ERROR, enemyId));
            return null;
        }

        UUID uuid = UUID.randomUUID();

        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);
        itemMeta.setDisplayName(ChatColor.AQUA + "Spawning Tool: " + ChatColor.RESET + "" + ChatColor.GREEN + enemy.getId() + " (Spawn round: " + spawnRound + ")");

        itemStack.setItemMeta(itemMeta);

        SpawningTool spawningTool = new SpawningTool(itemStack, enemy, uuid, spawnRound);

        Bukkit.getPluginManager().registerEvents(spawningTool, Main.getInstance());

        return new Tuple<>(itemStack, spawningTool);
    }

    /**
     * Gives a player a weapon.
     */
    public static GameplayWeapon giveWeapon(Player player, String weaponId) {

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

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
        GameplayWeapon gameplayWeapon = new GameplayWeapon(player, weapon, uuid);

        Bukkit.getPluginManager().registerEvents(gameplayWeapon, Main.getInstance());

        return gameplayWeapon;
    }
    //endregion
}
