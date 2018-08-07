package com.mango.zombies.assets;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.mango.zombies.Main;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.PlayerSpawnPointEntity;
import com.mango.zombies.entities.ZombieSpawnPointEntity;

public class SpawnPointInterface
{
	public static void ShowSpawnPoints(MapEntity map)
	{
		// define World
		World world = Bukkit.getServer().getWorld(Main.worldName);
		
		// define Location and Block objects for the origin point
		Location originPointLocation = new Location(world, map.getOriginPoint().getXCoord(), map.getOriginPoint().getYCoord(), map.getOriginPoint().getZCoord());
		Block originPointBlock = originPointLocation.getBlock();
		
		// set the origin point block
		originPointBlock.setType(Material.DIAMOND_BLOCK);
		
		// run this for every Player spawn point
		for (PlayerSpawnPointEntity spawnPoint : map.listPlayerSpawnPoints())
		{
			// define Location and Block objects
			Location location = new Location(world, spawnPoint.getXCoord(), spawnPoint.getYCoord(), spawnPoint.getZCoord());
			Block block = location.getBlock();
			
			// set the block to a gold block
			block.setType(Material.GOLD_BLOCK);
		}
		
		// run this for every Zombie spawn point
		for (ZombieSpawnPointEntity spawnPoint : map.listZombieSpawnPoints())
		{
			// define Location and Block objects
			Location location = new Location(world, spawnPoint.getXCoord(), spawnPoint.getYCoord(), spawnPoint.getZCoord());
			Block block = location.getBlock();
			
			// set the block to a redstone block
			block.setType(Material.REDSTONE_BLOCK);
		}
	}
	
	public static void HideSpawnPoints(MapEntity map)
	{
		// define World
		World world = Bukkit.getServer().getWorld(Main.worldName);
		
		// define Location and Block objects for the origin point
		Location originPointLocation = new Location(world, map.getOriginPoint().getXCoord(), map.getOriginPoint().getYCoord(), map.getOriginPoint().getZCoord());
		Block originPointBlock = originPointLocation.getBlock();
		
		// set the origin point block
		originPointBlock.setType(Material.matchMaterial(map.getOriginPoint().getBlock()));
		
		// run this for every Player spawn point
		for (PlayerSpawnPointEntity spawnPoint : map.listPlayerSpawnPoints())
		{
			// define Location and Block objects
			Location location = new Location(world, spawnPoint.getXCoord(), spawnPoint.getYCoord(), spawnPoint.getZCoord());
			Block block = location.getBlock();
			
			// set the block back to its original state
			block.setType(Material.matchMaterial(spawnPoint.getBlock()));
		}
		
		// run this for every Zombie spawn point
		for (ZombieSpawnPointEntity spawnPoint : map.listZombieSpawnPoints())
		{
			// define Location and Block objects
			Location location = new Location(world, spawnPoint.getXCoord(), spawnPoint.getYCoord(), spawnPoint.getZCoord());
			Block block = location.getBlock();
			
			// set the block back to its original state
			block.setType(Material.matchMaterial(spawnPoint.getBlock()));
		}
	}
}
