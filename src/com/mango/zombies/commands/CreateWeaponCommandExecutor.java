package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWeaponCommandExecutor extends BaseCommandExecutor {

	//region Constant Values
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createweapon [ID] [weapon type] [cost] [name]";
	public static final String WEAPON_ID_ALREADY_EXISTS_ERROR = "Weapon not created. %s already exists.";
	public static final String INVALID_COST_ERROR = "Weapon not created. %s is not a valid cost.";
	public static final String INVALID_WEAPON_TYPE_ERROR = "Weapon not created. Weapon type must be one of the following: %s";
	//endregion

	//region Event Handlers
	@Override
	public String executeCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 3)
			throw new CommandException(CORRECT_USAGE_ERROR);

		if (!isValidWeaponId(args[0]))
			throw new CommandException(String.format(WEAPON_ID_ALREADY_EXISTS_ERROR, args[0]));

		if (!isValidWeaponType(args[1]))
			throw new CommandException(String.format(INVALID_WEAPON_TYPE_ERROR, String.join(", ", WeaponService.toArray())));

		int weaponCost = parseWeaponCost(args[2]);

		if (weaponCost < 1)
			throw new CommandException(String.format(INVALID_COST_ERROR, args[2]));

		Material material = null;

		if (sender instanceof Player) {

			Player player = (Player)sender;
			Material mainHandItemMaterial = player.getInventory().getItemInMainHand().getType();

			if (!mainHandItemMaterial.isBlock() && !mainHandItemMaterial.isAir() && !mainHandItemMaterial.isEdible())
				material = mainHandItemMaterial;
		}

		StringBuilder name = new StringBuilder();

		for (int i = 3; i < args.length; i++) {

			if (i > 3)
				name.append(" ");

			name.append(args[i]);
		}

		WeaponEntity weapon = new WeaponEntity(args[0], args[1], name.toString(), weaponCost, material);
		PluginCore.addWeapon(weapon);

		return "Successfully created weapon: " + ChatColor.BOLD + weapon.getName();
	}
	//endregion

	//region Private Methods
	private boolean isValidWeaponId(String weaponId) {

		for (WeaponEntity weapon : PluginCore.getWeapons()) {

			if (weapon.getId().equals(weaponId))
				return false;
		}
		
		return true;
	}

	private boolean isValidWeaponType(String weaponType) {

		for (String queryType : WeaponService.toArray()) {

			if (weaponType.equals(queryType))
				return true;
		}

		return false;
	}

	private int parseWeaponCost(String rawWeaponCost) {

		try {
			return Integer.parseInt(rawWeaponCost);
		} catch (Exception ignored) {
			return -1;
		}
	}
	//endregion
}