package com.mango.zombies;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.mango.zombies.commands.CreateMapCommand;
import com.mango.zombies.commands.DeleteMapCommand;
import com.mango.zombies.commands.EditMapDescriptionCommand;
import com.mango.zombies.commands.ListMapsCommand;
import com.mango.zombies.entities.MapEntity;

public class Main extends JavaPlugin
{
	// define data folder root
	public static File dataFolder;
	
	// define map list
	public static List<MapEntity> mapList;
	
	// ran on startup of the plugin
	public void onEnable()
	{
		// initialise directory for data folder
		dataFolder = getDataFolder();
		
		// check if the directory is not present
		if (!dataFolder.exists())
		{
			// try to create directory and get true/false response
			if (!dataFolder.mkdir())
			{
				// creating the directory failed so notify the user
				System.out.println("[Zombies] Could not create directory for plugin");
			}
		}
		
		// initialise the map list
		mapList = new ArrayList<MapEntity>();
		
		// get list of map files
		File[] fileList = new File(dataFolder + "/Maps/").listFiles();
		
		// run this for every entry in the file list
		for (File file : fileList)
		{
			// check if the file type is .yml
			if (file.getName().endsWith(".yml"))
			{
				// build new YamlConfiguration for the map file
				YamlConfiguration mapFile = YamlConfiguration.loadConfiguration(new File(dataFolder + "/Maps/" +  file.getName()));
				
				// build new map entity using the map file
				MapEntity loadedMap = new MapEntity(mapFile.getString("name"));
				loadedMap.setDescription(mapFile.getString("description"));
				loadedMap.setDeleteKey(mapFile.getString("delete_key"));
				loadedMap.setEnabled(mapFile.getBoolean("enabled"));
				loadedMap.setUuid(mapFile.getString("uuid"));
				
				// add the loaded map to the list
				mapList.add(loadedMap);
			}
		}
				
		// set executors for commands
		this.getCommand("z_createmap").setExecutor(new CreateMapCommand());
		this.getCommand("z_listmaps").setExecutor(new ListMapsCommand());
		this.getCommand("z_editmap_description").setExecutor(new EditMapDescriptionCommand());
		this.getCommand("z_deletemap").setExecutor(new DeleteMapCommand());
	}
	
	// ran on shutdown of the plugin
	public void onDisable()
	{		
		//System.out.println("[Zombies] Zombies has been disabled");
	}
}