package com.mango.zombies.entities;

import com.mango.zombies.serializers.BlockLocationEntityJsonSerializer;
import org.bukkit.Location;

public class BlockLocationEntity extends LocationEntity {

	//region Constant Values
	public static final String BLOCK_JSON_TAG = "block";
	public static final BlockLocationEntityJsonSerializer SERIALIZER = new BlockLocationEntityJsonSerializer();
	public static final String X_JSON_TAG = "x";
	public static final String Y_JSON_TAG = "y";
	public static final String Z_JSON_TAG = "z";
	//endregion

	//region Fields
	private String block;
	//endregion

	//region Getters/Setters
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
	//endregion

	//region Constructors
	public BlockLocationEntity() {
		super();
	}

	public BlockLocationEntity(Location location) {
		super(location);

		block = location.getBlock().getType().toString();
	}
	//endregion
}