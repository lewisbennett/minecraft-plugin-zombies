package com.mango.zombies.entities;

import org.bukkit.Location;

public class LocationEntity
{
	public int x;
	public int y;
	public int z;
	public String block;
	
	public LocationEntity(Location location)
	{
		x = location.getBlockX();
		y = location.getBlockY();
		z = location.getBlockZ();
		block = location.getBlock().getType().toString();
	}
	
	public String getLocationAsString()
	{
		return Integer.toString(x) + ", " + Integer.toString(y) + ", " + Integer.toString(z);
	}
}