package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.gameplay.GameplayWeapon;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetWeaponCommandExecutor extends BaseCommandExecutor {

	//region Constant Values
	public static final String CORRECT_USAGE_ERROR_CONSOLE = "Correct usage: /getweapon [weapon ID] [player name]";
	public static final String CORRECT_USAGE_ERROR_PLAYER = "Correct usage: /getweapon [weapon ID]";
	public static final String PLAYER_NOT_FOUND_ERROR = "Player not found.";
	public static final String WEAPON_NOT_FOUND_ERROR = "%s is not a valid weapon ID.";
	//endregion

	//region Event Handlers
	@Override
	public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

		Player player;

		if (commandSender instanceof Player) {

			player = (Player)commandSender;

			if (args.length != 1)
				throw new CommandException(CORRECT_USAGE_ERROR_PLAYER);

		} else {

			if (args.length != 2)
				throw new CommandException(CORRECT_USAGE_ERROR_CONSOLE);

			player = Bukkit.getPlayer(args[1]);

			if (player == null)
				throw new CommandException(PLAYER_NOT_FOUND_ERROR);
		}

		// Find the weapon.
		WeaponEntity weaponEntity = null;

		for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

			if (queryWeapon.getId().equals(args[0])) {
				weaponEntity = queryWeapon;
				break;
			}
		}

		if (weaponEntity == null)
			throw new CommandException(String.format(WEAPON_NOT_FOUND_ERROR, args[0]));

		GameplayWeapon gameplayWeapon = new GameplayWeapon(weaponEntity);

		PluginCore.getGameplayService().addRegisterable(gameplayWeapon);

		gameplayWeapon.giveItemStack(player);

		return weaponEntity.getName() + " added to inventory.";
	}
	//endregion
}