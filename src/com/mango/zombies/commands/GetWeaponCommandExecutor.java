package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.Messaging;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetWeaponCommandExecutor extends PlayerOnlyCommandExecutor {

	public static final String CORRECT_USAGE_ERROR = "Correct usage: /getweapon [weapon ID]";
	public static final String WEAPON_CLASS_DOES_NOT_EXIST_ERROR = "Could not find weapon class \"%s\". This weapon cannot be used.";
	public static final String WEAPON_DOES_NOT_EXIST_ERROR = "%s is not a valid weapon ID.";

	@Override
	public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

		if (args.length != 1) {
			Messaging.showError(player, CORRECT_USAGE_ERROR);
			return true;
		}

		WeaponEntity weapon = null;

		for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

			if (queryWeapon.getId().equalsIgnoreCase(args[0])) {
				weapon = queryWeapon;
				break;
			}
		}

		if (weapon == null) {
			Messaging.showError(player, String.format(WEAPON_DOES_NOT_EXIST_ERROR, args[0]));
			return true;
		}

		WeaponClassEntity weaponClass = weapon.getWeaponClass();

		if (weaponClass == null) {
			Messaging.showError(player, String.format(WEAPON_CLASS_DOES_NOT_EXIST_ERROR, weapon.getWeaponClassId()));
			return true;
		}

		ItemStack itemStack = new ItemStack(Material.getMaterial(weapon.getItem()), 1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.RESET + "" + ChatColor.valueOf(weaponClass.getColor()) + weapon.getName());
		itemStack.setItemMeta(itemMeta);

		player.getInventory().addItem(itemStack);
		Messaging.showSuccess(player, weapon.getName() + " added to inventory.");

		return true;
	}
}