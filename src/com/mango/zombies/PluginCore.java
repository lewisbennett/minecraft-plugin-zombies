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
	public static File installFolder;
	public static File weaponsFolder;
	
	public static ConfigEntity config;
	public static Gameplay gameplay = new Gameplay();
	
	public static void setupFolders(File pluginRoot)
	{
		dataFolder = pluginRoot;
		mapsFolder = new File(dataFolder + "/Maps/");
		installFolder = new File(dataFolder + "/Install/");
		weaponsFolder = new File(dataFolder + "/Weapons/");
		
		// check if each of the needed folders exists and create if not
		if (!dataFolder.exists() && !dataFolder.mkdir())
			System.out.println("[Zombies] Could not create directory for plugin");
		
		if (!mapsFolder.exists() && !mapsFolder.mkdir())
			System.out.println("[Zombies] Could not create Maps directory for plugin");
		
		if (!installFolder.exists() && !installFolder.mkdir())
			System.out.println("[Zombies] Could not create Install directory for plugin");
		
		if (!weaponsFolder.exists() && !weaponsFolder.mkdir())
			System.out.println("[Zombies] Could not create Weapons directory for plugin");
	}
}