package com.mango.zombies.commands;

import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.services.MessagingService;
import com.mango.zombies.services.GameplayService;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class GetWeaponCommandExecutor extends PlayerOnlyCommandExecutor {

	public static final String CORRECT_USAGE_ERROR = "Correct usage: /getweapon [weapon ID]";

	@Override
	public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

		if (args.length != 1) {
			MessagingService.showError(player, CORRECT_USAGE_ERROR);
			return true;
		}

		GameplayWeapon gameplayWeapon = GameplayService.giveWeapon(player, args[0]);

		if (gameplayWeapon == null)
			return true;

		player.getInventory().addItem(gameplayWeapon.getItem());
		MessagingService.showSuccess(player, gameplayWeapon.getWeapon().getName() + " added to inventory.");

		return true;
	}
}