package com.mango.zombies.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;

public class MapEntity
{
	public String name;
	public String id;
	public LocationEntity originPoint;
	public List<LocationEntity> playerSpawns = new ArrayList<LocationEntity>();
	public List<LocationEntity> zombieSpawns = new ArrayList<LocationEntity>();
	public String deleteKey;
	
	public MapEntity(String id, String name, Location origin)
	{
		this.name = name;
		this.id = id;
		originPoint = new LocationEntity(origin);
		deleteKey = Integer.toString(100000 + new Random().nextInt(999999)).substring(0, 6);	// generates a random 6 digit number
	}
}