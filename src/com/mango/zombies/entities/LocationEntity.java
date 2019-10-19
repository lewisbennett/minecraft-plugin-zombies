package com.mango.zombies.entities;

import com.mango.zombies.serializers.LocationEntityJsonSerializer;
import org.bukkit.Location;

public class LocationEntity
{
	public static final String BLOCK_JSON_TAG = "block";
	public static final LocationEntityJsonSerializer SERIALIZER = new LocationEntityJsonSerializer();
	public static final String X_JSON_TAG = "x";
	public static final String Y_JSON_TAG = "y";
	public static final String Z_JSON_TAG = "z";

	private int x;
	private int y;
	private int z;
	private String block;

	/**
	 * Gets the block.
	 */
	public String getBlock() {
		return block;
	}

	/**
	 * Sets the block.
	 */
	public void setBlock(String block) {
		this.block = block;
	}

	/**
	 * Gets the X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the X coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the Z coordinate.
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Sets the Z coordinate.
	 */
	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return Integer.toString(x) + ", " + Integer.toString(y) + ", " + Integer.toString(z);
	}

	public LocationEntity() {
	}

	public LocationEntity(Location location) {
		this();

		x = location.getBlockX();
		y = location.getBlockY();
		z = location.getBlockZ();
		block = location.getBlock().getType().toString();
	}
}