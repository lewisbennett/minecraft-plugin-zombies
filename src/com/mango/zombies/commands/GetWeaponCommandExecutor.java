package com.mango.zombies.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.CustomMessaging;
import com.mango.zombies.helper.GlobalErrors;

public class GetWeaponCommandExecutor implements CommandExecutor
{
	// errors specific to this command
	public static final String CorrectUsageError = "Correct usage: /getweapon [weapon name]";
	public static final String DoesNotExistError = "This weapon does not exist";
	
	private Player _player;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
			_player = (Player)sender;
		else
		{
			CustomMessaging.showError(sender, GlobalErrors.PlayerOnlyCommand);
			return true;
		}
		
		if (args.length != 1)
		{
			CustomMessaging.showError(sender, CorrectUsageError);
			return true;
		}
		
		WeaponEntity weapon = null;
		
		for (WeaponEntity queryWeapon : PluginCore.gameplay.weapons)
		{
			if (queryWeapon.name.equals(args[0]))
				weapon = queryWeapon;
		}
		
		if (weapon == null)
		{
			CustomMessaging.showError(sender, DoesNotExistError);
			return true;
		}
		
		// gets the class that the weapon belongs to
		WeaponClassEntity weaponClass = null;
		for (WeaponClassEntity queryClass : PluginCore.gameplay.weaponClasses)
		{
			if (queryClass.name.equals(weapon.weaponClass))
				weaponClass = queryClass;
		}
		
		// gets the item and formats it ready to be added to the user's inventory
		ItemStack item = new ItemStack(Material.getMaterial(weapon.item), 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "" + ChatColor.valueOf(weaponClass.color) + weapon.name);
		item.setItemMeta(meta);
		
		_player.getInventory().addItem(item);
		CustomMessaging.showSuccess(sender, "Weapon added to inventory");
		
		return true;
	}
}