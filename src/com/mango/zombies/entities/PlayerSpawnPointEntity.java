package com.mango.zombies.entities;

public class PlayerSpawnPointEntity
{
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private String blockName;
	
	public PlayerSpawnPointEntity(int x, int y, int z, String block)
	{
		xCoord = x;
		yCoord = y;
		zCoord = z;
		blockName = block;
	}
	
	// x coordinate
	public int getXCoord()
	{
		return xCoord;
	}
	
	// y coordinate
	public int getYCoord()
	{
		return yCoord;
	}
	
	// z coordinate
	public int getZCoord()
	{
		return zCoord;
	}
	
	// block
	public String getBlock()
	{
		return blockName;
	}
}
