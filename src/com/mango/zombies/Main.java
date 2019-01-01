package com.mango.zombies;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mango.zombies.commands.CreateMapCommandExecutor;
import com.mango.zombies.commands.CreatePerkCommandExecutor;
import com.mango.zombies.commands.CreateWeaponClassCommandExecutor;
import com.mango.zombies.commands.CreateWeaponCommandExecutor;
import com.mango.zombies.commands.GetWeaponCommandExecutor;
import com.mango.zombies.commands.InfoCommandExecutor;
import com.mango.zombies.commands.MapInfoCommandExecutor;
import com.mango.zombies.entities.ConfigEntity;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.PerkEntity;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.FileManager;
import com.mango.zombies.listeners.BlockEvents;
import com.mango.zombies.schema.FileNames;

public class Main extends JavaPlugin
{
	public void onEnable()
	{
		PluginCore.descriptionFile = getDescription();
		PluginCore.setupFolders(getDataFolder());
		
		// get the config file or create if not there
		for (File file : PluginCore.dataFolder.listFiles())
		{
			if (file.getName().equals(FileNames.configFile))
			{
				try
				{
					PluginCore.config = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), ConfigEntity.class);
				}
				catch (JsonSyntaxException ex)
				{
					PluginCore.config = new ConfigEntity();
					PluginCore.config.worldName = Bukkit.getWorlds().get(0).getName();
				}
				
				break;
			}
		}
		
		// import maps, weapons, weapon classes, perks
		for (File file : PluginCore.mapsFolder.listFiles())
		{
			try
			{
				MapEntity map = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), MapEntity.class);
				PluginCore.gameplay.maps.add(map);
			}
			catch (JsonSyntaxException ex)
			{
				System.out.println("[Zombies] Failed to parse map file: " + file.getName());
			}
		}
		
		for (File file : PluginCore.weaponsFolder.listFiles())
		{
			try
			{
				WeaponEntity weapon = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), WeaponEntity.class);
				PluginCore.gameplay.weapons.add(weapon);
			}
			catch (JsonSyntaxException ex)
			{
				System.out.println("[Zombies] Failed to parse weapon file: " + file.getName());
			}
		}
		
		for (File file : PluginCore.weaponClassesFolder.listFiles())
		{
			try
			{
				WeaponClassEntity weaponClass = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), WeaponClassEntity.class);
				PluginCore.gameplay.weaponClasses.add(weaponClass);
			}
			catch (JsonSyntaxException ex)
			{
				System.out.println("[Zombies] Failed to parse weapon class file: " + file.getName());
			}
		}
		
		for (File file : PluginCore.perksFolder.listFiles())
		{
			try
			{
				PerkEntity perk = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), PerkEntity.class);
				PluginCore.gameplay.perks.add(perk);
			}
			catch (JsonSyntaxException ex)
			{
				System.out.println("[Zombies] Failed to parse perk file: " + file.getName());
			}
		}
		
		// set event handlers
		getServer().getPluginManager().registerEvents(new BlockEvents(), this);
		
		// set executors for commands
		this.getCommand("info").setExecutor(new InfoCommandExecutor());
		this.getCommand("mapinfo").setExecutor(new MapInfoCommandExecutor());
		this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
		this.getCommand("createweaponclass").setExecutor(new CreateWeaponClassCommandExecutor());
		this.getCommand("createweapon").setExecutor(new CreateWeaponCommandExecutor());		
		this.getCommand("createperk").setExecutor(new CreatePerkCommandExecutor());
		this.getCommand("getweapon").setExecutor(new GetWeaponCommandExecutor());
	}
	
	public void onDisable()
	{		
		// write config file
		FileManager.WriteFile(PluginCore.dataFolder, FileNames.configFile, PluginCore.config);
		
		// save maps, weapons, weapon classes, perks
		for (MapEntity map : PluginCore.gameplay.maps)
			FileManager.WriteFile(PluginCore.mapsFolder, map.id+ ".json", map);
		
		for (WeaponEntity weapon : PluginCore.gameplay.weapons)
			FileManager.WriteFile(PluginCore.weaponsFolder, weapon.id + ".json", weapon);
		
		for (WeaponClassEntity weaponClass : PluginCore.gameplay.weaponClasses)
			FileManager.WriteFile(PluginCore.weaponClassesFolder, weaponClass.name + ".json", weaponClass);
		
		for (PerkEntity perk : PluginCore.gameplay.perks)
			FileManager.WriteFile(PluginCore.perksFolder, perk.id + ".json", perk);
	}
}