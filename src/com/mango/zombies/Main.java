package com.mango.zombies;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mango.zombies.commands.CreateMapCommandExecutor;
import com.mango.zombies.commands.CreateWeaponCommandExecutor;
import com.mango.zombies.commands.GetWeaponCommandExecutor;
import com.mango.zombies.commands.InfoCommandExecutor;
import com.mango.zombies.entities.ConfigEntity;

import com.google.gson.Gson;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.helper.FileManager;
import com.mango.zombies.schema.FileNames;

public class Main extends JavaPlugin
{
	public void onEnable()
	{
		PluginCore.descriptionFile = getDescription();
		PluginCore.setupFolders(getDataFolder());
		
		for (File file : PluginCore.dataFolder.listFiles())
		{
			// get the config file or create if not there
			if (file.getName().equals(FileNames.configFile))
				PluginCore.config = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), ConfigEntity.class);
			else
			{
				PluginCore.config = new ConfigEntity();
				PluginCore.config.worldName = Bukkit.getWorlds().get(0).getName();
			}
		}
		
		for (File file : PluginCore.mapsFolder.listFiles())
		{
			MapEntity map = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), MapEntity.class);
			
			// if parsing was successful the map won't be null
			if (map != null)
				PluginCore.gameplay.maps.add(map);
			else
				System.out.println("[Zombies] Failed to parse map file: " + file.getName());
		}
		
		for (File file : PluginCore.weaponsFolder.listFiles())
		{
			WeaponEntity weapon = new Gson().fromJson(FileManager.ReadContentsAsString(file.toString()), WeaponEntity.class);
			
			if (weapon != null)
				PluginCore.gameplay.weapons.add(weapon);
			else
				System.out.println("[Zombies] Failed to parse weapon file: " + file.getName());
		}
				
		// executor for base command - we handle subcommands in CommandHandler
		this.getCommand("info").setExecutor(new InfoCommandExecutor());
		this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
		this.getCommand("createweapon").setExecutor(new CreateWeaponCommandExecutor());
		this.getCommand("getweapon").setExecutor(new GetWeaponCommandExecutor());
	}
	
	public void onDisable()
	{		
		FileManager.WriteFile(PluginCore.dataFolder, FileNames.configFile, PluginCore.config);
		
		for (MapEntity map : PluginCore.gameplay.maps)
			FileManager.WriteFile(PluginCore.mapsFolder, map.name + ".json", map);
		
		for (WeaponEntity weapon : PluginCore.gameplay.weapons)
			FileManager.WriteFile(PluginCore.weaponsFolder, weapon.name + ".json", weapon);
	}
}