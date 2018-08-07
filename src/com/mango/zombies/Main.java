package com.mango.zombies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.mango.zombies.assets.SpawnPointInterface;
import com.mango.zombies.commands.SetOriginPointCommand;
import com.mango.zombies.commands.AddSpawnPointPlayerCommand;
import com.mango.zombies.commands.AddSpawnPointZombieCommand;
import com.mango.zombies.commands.CreateMapCommand;
import com.mango.zombies.commands.DeleteMapCommand;
import com.mango.zombies.commands.EditMapDescriptionClearCommand;
import com.mango.zombies.commands.EditMapDescriptionCommand;
import com.mango.zombies.commands.EditMapNameCommand;
import com.mango.zombies.commands.ListMapsCommand;
import com.mango.zombies.commands.MapInfoCommand;
import com.mango.zombies.entities.MapEntity;

public class Main extends JavaPlugin
{
	// define data folder root
	public static File dataFolder;
	public static File mapsFolder;
	public static File installFolder;
	
	// define config
	public static String worldName;
	
	// define map list
	public static List<MapEntity> mapList;
	
	// ran on startup of the plugin
	public void onEnable()
	{
		// initialise File directories
		dataFolder = getDataFolder();
		mapsFolder = new File(dataFolder + "/Maps/");
		installFolder = new File(dataFolder + "/Install/");
		
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
		
		// check if the maps directory is not present
		if (!mapsFolder.exists())
		{
			// try to create maps directory and get true/false response
			if (!mapsFolder.mkdir())
			{
				// creating the maps directory failed so notify the user
				System.out.println("[Zombies] Could not create Maps directory for plugin");
			}
		}
		
		// check if the install directory is not present
		if (!installFolder.exists())
		{
			// try to create install directory and get true/false response
			if (!installFolder.mkdir())
			{
				// creating the install directory failed so notify the user
				System.out.println("[Zombies] Could not create Install directory for plugin");
			}
		}
		
		// get a list of files in all directories
		File[] dataFolderFiles = dataFolder.listFiles();
		File[] mapFolderFiles = mapsFolder.listFiles();
		//File[] installFolderFiles = installFolder.listFiles();
		
		// initialise the map list
		mapList = new ArrayList<MapEntity>();
		
		// run this for every entry in the data folder
		for (File file : dataFolderFiles)
		{
			// define boolean for file check
			boolean doesExist = false;
			
			// check if the config file exists
			if (file.getName().equals("config.yml"))
			{
				// validate the file check boolean
				doesExist = true;
				
				// create new YamlConfiguration for the config file
				YamlConfiguration configFile = YamlConfiguration.loadConfiguration(file);
				
				// get the name of the world
				worldName = configFile.getString("world_name");
				
				break;
			}
			
			// check if the file does not exist
			if (!doesExist)
			{
				// create a new YamlConfiguration
				YamlConfiguration yaml = new YamlConfiguration();
				
				// create the sections
				yaml.createSection("world_name");

				// set the information
				yaml.set("world_name", Bukkit.getWorlds().get(0).getName());
				
				try
				{
					// save the file
					yaml.save(Main.dataFolder + "/" + "config.yml");
				}
				catch (IOException e)
				{	
					// notify the console and print the error
					System.out.println("[Zombies] Error creating config file");
					e.printStackTrace();
				}
			}
		}
		
		// run this for every entry in the maps directory
		for (File file : mapFolderFiles)
		{
			// check if the file type is .yml
			if (file.getName().endsWith(".yml"))
			{
				// build new YamlConfiguration for the map file
				YamlConfiguration mapFile = YamlConfiguration.loadConfiguration(new File(mapsFolder + "/" + file.getName()));
				
				// create lists for spawn points
				String[] originPointParts = mapFile.getString("origin_point").split(",");
				List<String> playerSpawns = mapFile.getStringList("player_spawn_points");
				List<String> zombieSpawns = mapFile.getStringList(("zombie_spawn_points"));
				
				// build new map entity using the map file
				MapEntity loadedMap = new MapEntity(mapFile.getString("name"));
				loadedMap.setOriginPointCoords(Integer.parseInt(originPointParts[0]), Integer.parseInt(originPointParts[1]), Integer.parseInt(originPointParts[2]), originPointParts[3]);
				loadedMap.setDescription(mapFile.getString("description"));
				loadedMap.setDeleteKey(mapFile.getString("delete_key"));
				loadedMap.setEnabled(mapFile.getBoolean("enabled"));
				loadedMap.setUuid(mapFile.getString("uuid"));
				
				// run this for each String in the player spawns list
				for (String spawnPointCoords : playerSpawns)
				{
					// create new String array
					String[] spawnInfo = spawnPointCoords.split(",");
					
					// add the coordinates to the list
					loadedMap.addPlayerSpawnCoords(Integer.parseInt(spawnInfo[0]), Integer.parseInt(spawnInfo[1]), Integer.parseInt(spawnInfo[2]), spawnInfo[3]);
				}
				
				// run this for each String in the zombie spawns list
				for (String spawnPointCoords : zombieSpawns)
				{
					// create new String array
					String[] spawnInfo = spawnPointCoords.split(",");
					
					// add the coordinates to the list
					loadedMap.addZombieSpawnCoords(Integer.parseInt(spawnInfo[0]), Integer.parseInt(spawnInfo[1]), Integer.parseInt(spawnInfo[2]), spawnInfo[3]);
				};
				
				// add the loaded map to the list
				mapList.add(loadedMap);
			}
		}
		
		// run this for every map in the map list
		for (MapEntity map : mapList)
		{
			// check if the map is enabled
			if (map.isEnabled())
			{
				// hide the spawn points
				SpawnPointInterface.HideSpawnPoints(map);
			}
			// the map is disabled
			else
			{
				// show the spawn points
				SpawnPointInterface.ShowSpawnPoints(map);
			}
		}
				
		// set executors for commands
		this.getCommand("z_createmap").setExecutor(new CreateMapCommand());
		this.getCommand("z_addspawnpoint_player").setExecutor(new AddSpawnPointPlayerCommand());
		this.getCommand("z_addspawnpoint_zombie").setExecutor(new AddSpawnPointZombieCommand());
		this.getCommand("z_listmaps").setExecutor(new ListMapsCommand());
		this.getCommand("z_mapinfo").setExecutor(new MapInfoCommand());
		this.getCommand("z_editmap_name").setExecutor(new EditMapNameCommand());
		this.getCommand("z_editmap_description").setExecutor(new EditMapDescriptionCommand());
		this.getCommand("z_editmap_description_clear").setExecutor(new EditMapDescriptionClearCommand());
		this.getCommand("z_deletemap").setExecutor(new DeleteMapCommand());
		this.getCommand("z_setoriginpoint").setExecutor(new SetOriginPointCommand());
	}
	
	// ran on shutdown of the plugin
	public void onDisable()
	{		
		
	}
}