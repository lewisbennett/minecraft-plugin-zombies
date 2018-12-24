package com.mango.zombies;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mango.zombies.commands.CreateMapCommandExecutor;
import com.mango.zombies.commands.InfoCommandExecutor;
import com.mango.zombies.entities.ConfigEntity;

import com.google.gson.Gson;
import com.mango.zombies.entities.MapEntity;
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
				PluginCore.mapList.add(map);
			else
				System.out.println("[Zombies] Failed to parse map file: " + file.getName());
		}
				
		// executor for base command - we handle subcommands in CommandHandler
		this.getCommand("info").setExecutor(new InfoCommandExecutor());
		this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
	}
	
	public void onDisable()
	{		
		FileManager.WriteFile(PluginCore.dataFolder, FileNames.configFile, new Gson().toJson(PluginCore.config));
		
		for (MapEntity map : PluginCore.mapList)
			FileManager.WriteFile(PluginCore.mapsFolder, map.mapName + ".json", new Gson().toJson(map));
	}
}