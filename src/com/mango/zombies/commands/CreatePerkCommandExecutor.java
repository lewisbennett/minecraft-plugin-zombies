package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.PerkEntity;
import com.mango.zombies.helper.Messaging;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CreatePerkCommandExecutor extends BaseCommandExecutor {

	public static final String CORRECT_USAGE_ERROR = "Correct usage: /createperk [ID] [cost] [name]";
	public static final String INVALID_COST = "Perk not created. You need to enter a valid cost.";
	public static final String PERK_ID_ALREADY_EXISTS_ERROR = "Perk not created. %s already exists.";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length < 3) {
			Messaging.showError(sender, CORRECT_USAGE_ERROR);
			return true;
		}
		
		if (!isValidPerkId(args[0])) {
			Messaging.showError(sender, String.format(PERK_ID_ALREADY_EXISTS_ERROR, args[0]));
			return true;
		}

		Integer cost;

		try {
			cost = Integer.parseInt(args[1]);
		} catch (Exception ex) {
			Messaging.showError(sender, INVALID_COST);
			return true;
		}

		StringBuilder name = new StringBuilder();

		for (int i = 2; i < args.length; i++) {

			if (i > 2)
				name.append(" ");

			name.append(args[i]);
		}

		PerkEntity perk = new PerkEntity(args[0], name.toString(), cost);
		PluginCore.getPerks().add(perk);
		Messaging.showSuccess(sender, "Successfully created perk: " + ChatColor.BOLD + perk.getName());
		
		return true;
	}
	
	private boolean isValidPerkId(String perkId) {

		for (PerkEntity perk : PluginCore.getPerks()) {

			if (perk.getId().equalsIgnoreCase(perkId))
				return false;
		}
		
		return true;
	}
}