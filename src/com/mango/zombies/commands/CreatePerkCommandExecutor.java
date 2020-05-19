package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.PerkEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public class CreatePerkCommandExecutor extends BaseCommandExecutor {

	//region Constant Values
	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createperk [ID] [cost] [name]";
	public static final String INVALID_COST = "Perk not created. %s is not a valid cost.";
	public static final String PERK_ID_ALREADY_EXISTS_ERROR = "Perk not created. %s already exists.";
	//endregion

	//region Event Handlers
	@Override
	public String executeCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 3)
			throw new CommandException(CORRECT_USAGE_ERROR);

		if (!isValidPerkId(args[0]))
			throw new CommandException(String.format(PERK_ID_ALREADY_EXISTS_ERROR, args[0]));

		int cost = 0;

		try {
			cost = Integer.parseInt(args[1]);
		} catch (Exception ignored) { }

		if (cost < 1)
			throw new CommandException(String.format(INVALID_COST, args[1]));

		StringBuilder name = new StringBuilder();

		for (int i = 2; i < args.length; i++) {

			if (i > 2)
				name.append(" ");

			name.append(args[i]);
		}

		PerkEntity perk = new PerkEntity(args[0], name.toString(), cost);
		PluginCore.addPerk(perk);

		return "Successfully created perk: " + ChatColor.BOLD + perk.getName();
	}
	//endregion

	//region Private Methods
	private boolean isValidPerkId(String perkId) {

		for (PerkEntity perk : PluginCore.getPerks()) {

			if (perk.getId().equals(perkId))
				return false;
		}

		return true;
	}
	//endregion
}