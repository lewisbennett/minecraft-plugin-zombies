package com.mango.zombies.entities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class MapEntity
{
	private String MapName;
	private String Description;
	private OriginPointEntity originPoint;
	private List<PlayerSpawnPointEntity> playerSpawns;
	private List<ZombieSpawnPointEntity> zombieSpawns;
	private boolean Enabled;
	private String DeleteKey;
	private String Uuid;
	
	public MapEntity(String name)
	{
		MapName = name;
		playerSpawns = new ArrayList<PlayerSpawnPointEntity>();
		zombieSpawns = new ArrayList<ZombieSpawnPointEntity>();
		originPoint = new OriginPointEntity(0, 0, 0, "GRASS");
	}
	
	// name
	public void setName(String name)
	{
		this.MapName = name;
	}
	
	public String getName()
	{
		return MapName;
	}
	
	// description
	public void setDescription(String description)
	{
		this.Description = description;
	}
	
	public String getDescription()
	{
		return Description;
	}
	
	// origin point
	public void setOriginPointCoords(int x, int y, int z, String block)
	{
		originPoint = new OriginPointEntity(x, y, z, block);
	}
	
	public void setOriginPointLocation(Location location, Block block)
	{
		originPoint = new OriginPointEntity(location.getBlockX(), location.getBlockY(), location.getBlockZ(), block.getType().toString());
	}
	
	public OriginPointEntity getOriginPoint()
	{
		return originPoint;
	}
	
	public String getOriginPointStr()
	{
		return originPoint.getXCoord() + "," + originPoint.getYCoord() + "," + originPoint.getZCoord() + "," + originPoint.getBlock();
	}
	
	public String getOriginPointDisplay()
	{
		return originPoint.getXCoord() + " " + originPoint.getYCoord() + " " + originPoint.getZCoord();
	}
	
	// player spawn points
	public void addPlayerSpawnCoords(int x, int y, int z, String block)
	{
		this.playerSpawns.add(new PlayerSpawnPointEntity(x, y, z, block));
	}
	
	public void addPlayerSpawnLocation(Location location, Block block)
	{
		this.playerSpawns.add(new PlayerSpawnPointEntity(location.getBlockX(), location.getBlockY(), location.getBlockZ(), block.getType().toString()));
	}
	
	public List<PlayerSpawnPointEntity> listPlayerSpawnPoints()
	{
		return playerSpawns;
	}
	
	public List<String> getPlayerSpawnPoints()
	{
		List<String> spawns = new ArrayList<String>();
		
		for (PlayerSpawnPointEntity spawnPoint : playerSpawns)
		{
			spawns.add(spawnPoint.getXCoord() + "," + spawnPoint.getYCoord() + "," + spawnPoint.getZCoord() + "," + spawnPoint.getBlock());
		}
		
		return spawns;
	}
	
	// zombie spawn points
	public void addZombieSpawnCoords(int x, int y, int z, String block)
	{
		this.zombieSpawns.add(new ZombieSpawnPointEntity(x, y, z, block));
	}
	
	public void addZombieSpawnLocation(Location location, Block block)
	{
		this.zombieSpawns.add(new ZombieSpawnPointEntity(location.getBlockX(), location.getBlockY(), location.getBlockZ(), block.getType().toString()));
	}
	
	public List<ZombieSpawnPointEntity> listZombieSpawnPoints()
	{
		return zombieSpawns;
	}
	
	public List<String> getZombieSpawnPoints()
	{
		List<String> spawns = new ArrayList<String>();
		
		for (ZombieSpawnPointEntity spawnPoint : zombieSpawns)
		{
			spawns.add(spawnPoint.getXCoord() + "," + spawnPoint.getYCoord() + "," + spawnPoint.getZCoord() + "," + spawnPoint.getBlock());
		}
		
		return spawns;
	}
		
	// enabled
	public void setEnabled(boolean enabled)
	{
		this.Enabled = enabled;
	}
	
	public boolean isEnabled()
	{
		return this.Enabled;
	}
	
	// delete key
	public void setDeleteKey(String deleteKey)
	{
		this.DeleteKey = deleteKey;
	}
	
	// UUID
	public String getDeleteKey()
	{
		return DeleteKey;
	}
	
	public void setUuid(String uuid)
	{
		this.Uuid = uuid;
	}
	
	public String getUuid()
	{
		return Uuid;
	}
}
