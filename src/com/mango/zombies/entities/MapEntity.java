package com.mango.zombies.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;

public class MapEntity
{
	public String mapName;
	public String description;
	public LocationEntity originPoint;
	public List<LocationEntity> playerSpawns = new ArrayList<LocationEntity>();
	public List<LocationEntity> zombieSpawns = new ArrayList<LocationEntity>();
	public boolean isEnabled = false;
	public String deleteKey;
	
	public MapEntity(String name, Location origin)
	{
		mapName = name;
		description = "";
		originPoint = new LocationEntity(origin);
		deleteKey = Integer.toString(100000 + new Random().nextInt(999999));	// generates a random 6 digit number
	}
}