package com.mango.zombies.assets;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.mango.zombies.Main;
import com.mango.zombies.entities.MapEntity;

public class MapInterface
{
	public static boolean WriteMapFile(MapEntity map)
	{
		// define success boolean
		boolean isSuccess = false;
		
		// create a new YamlConfiguration
		YamlConfiguration yaml = new YamlConfiguration();
		
		// create the sections
		yaml.createSection("name");
		yaml.createSection("description");
		yaml.createSection("origin_point");
		yaml.createSection("player_spawn_points");
		yaml.createSection("zombie_spawn_points");
		yaml.createSection("delete_key");
		yaml.createSection("enabled");
		yaml.createSection("uuid");

		// set the information
		yaml.set("name", map.getName());
		yaml.set("description", map.getDescription());
		yaml.set("origin_point", map.getOriginPointStr());
		yaml.set("player_spawn_points", map.getPlayerSpawnPoints());
		yaml.set("zombie_spawn_points", map.getZombieSpawnPoints());
		yaml.set("delete_key", map.getDeleteKey());
		yaml.set("enabled", map.isEnabled());
		yaml.set("uuid", map.getUuid());
		
		try
		{
			// save the file
			yaml.save(Main.mapsFolder + "/" + map.getName() + ".yml");
			
			// validate the success bool
			isSuccess = true;
		}
		catch (IOException e)
		{	
			// notify the console and print the error
			System.out.println("[Zombies] Error writing map file: " + map.getName() + ".yml");
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	public static boolean DeleteMapFile(MapEntity map)
	{
		// define success boolean - this will be invalidated if there is an error
		boolean isSuccess = true;
		
		// get list of map files
		File[] fileList = Main.mapsFolder.listFiles();
		
		// run this for each file in the list
		for (File file : fileList)
		{
			// check if the file matches the one we're searching for
			if (file.getName().equals(map.getName() + ".yml"))
			{
				// delete the file
				file.delete();
				
				break;
			}
		}
		
		return isSuccess;
	}
}
