package com.mango.zombies;

import org.bukkit.plugin.PluginDescriptionFile;

import com.mango.zombies.entities.ConfigEntity;
import com.mango.zombies.gameplay.Gameplay;

import java.io.File;

public class PluginCore
{
	public static PluginDescriptionFile descriptionFile;
	
	public static File dataFolder;
	public static File mapsFolder;
	public static File importFolder;
	public static File weaponsFolder;
	public static File weaponClassesFolder;
	public static File perksFolder;
	
	public static ConfigEntity config;
	public static Gameplay gameplay = new Gameplay();
	
	public static void setupFolders(File pluginRoot)
	{
		dataFolder = pluginRoot;
		mapsFolder = new File(dataFolder + "/Maps/");
		importFolder = new File(dataFolder + "/Import/");
		weaponsFolder = new File(dataFolder + "/Weapons/");
		weaponClassesFolder = new File(dataFolder + "/Weapon Classes/");
		perksFolder = new File(dataFolder + "/Perks/");
		
		// check if each of the needed folders exists and create if not
		if (!dataFolder.exists() && !dataFolder.mkdir())
			System.out.println("[Zombies] Could not create directory for plugin");
		
		if (!mapsFolder.exists() && !mapsFolder.mkdir())
			System.out.println("[Zombies] Could not create maps directory for plugin");
		
		if (!importFolder.exists() && !importFolder.mkdir())
			System.out.println("[Zombies] Could not create import directory for plugin");
		
		if (!weaponsFolder.exists() && !weaponsFolder.mkdir())
			System.out.println("[Zombies] Could not create weapons directory for plugin");
		
		if (!weaponClassesFolder.exists() && !weaponClassesFolder.mkdir())
			System.out.println("[Zombies] Could not create weapon classes directory for plugin");
		
		if (!perksFolder.exists() && !perksFolder.mkdir())
			System.out.println("[Zombies] Could not create perks directory for plugin");
	}
}