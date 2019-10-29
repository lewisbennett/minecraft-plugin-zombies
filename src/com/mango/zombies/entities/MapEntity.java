package com.mango.zombies.entities;

import com.mango.zombies.serializers.MapEntityJsonSerializer;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapEntity {

	//region Constant Values
	public static final String BOTTOM_JSON_TAG = "bottom";
	public static final String DELETE_KEY_JSON_TAG = "delete_key";
	public static final String ENABLED_JSON_TAG = "enabled";
	public static final String ENEMY_SPAWNS_JSON_TAG = "enemy_spawns";
	public static final String ID_JSON_TAG = "id";
	public static final MapEntityJsonSerializer SERIALIZER = new MapEntityJsonSerializer();
	public static final String NAME_JSON_TAG = "name";
	public static final String ORIGIN_POINT_JSON_TAG = "origin_point";
	public static final String PLAYER_SPAWNS_JSON_TAG = "player_spawns";
	public static final String TOP_JSON_TAG = "top";
	//endregion

	//region Fields
	private LocationEntity bottom, top;
	private String deleteKey, id, name;
	private boolean enabled = false;
	private List<EnemySpawnEntity> enemySpawns = new ArrayList<EnemySpawnEntity>();
	private BlockLocationEntity originPoint;
	private List<BlockLocationEntity> playerSpawns = new ArrayList<BlockLocationEntity>();
	//endregion

	//region Getters/Setters
	/**
	 * Gets the bottom corner of the map.
	 */
	public LocationEntity getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom corner of the map.
	 */
	public void setBottom(LocationEntity bottom) {
		this.bottom = bottom;
	}

	/**
	 * Gets the map's delete key.
	 */
	public String getDeleteKey() {
		return deleteKey;
	}

	/**
	 * Sets the map's delete key.
	 */
	public void setDeleteKey(String deleteKey) {
		this.deleteKey = deleteKey;
	}

	/**
	 * Gets whether the map is enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets whether the map is enabled.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the locations where enemies can spawn in the map.
	 */
	public List<EnemySpawnEntity> getEnemySpawns() {
		return enemySpawns;
	}

	/**
	 * Gets the map's ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the map's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name of the map.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the map.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the map's origin point.
	 */
	public BlockLocationEntity getOriginPoint() {
		return originPoint;
	}

	/**
	 * Sets the map's origin point.
	 */
	public void setOriginPoint(BlockLocationEntity originPoint) {
		this.originPoint = originPoint;
	}

	/**
	 * Sets the map's origin point.
	 */
	public void setOriginPoint(Location origin) {
		originPoint = new BlockLocationEntity(origin);
	}

	/**
	 * Gets the locations where players can spawn in the map.
	 */
	public List<BlockLocationEntity> getPlayerSpawns() {
		return playerSpawns;
	}

	/**
	 * Gets the top corner of the map.
	 */
	public LocationEntity getTop() {
		return top;
	}

	/**
	 * Sets the top corner of the map.
	 */
	public void setTop(LocationEntity top) {
		this.top = top;
	}
	//endregion

	//region Public Methods
	/**
	 * Disables the map if it's in an acceptable state.
	 */
	public Tuple<Boolean, String> disableMap() {
		enabled = false;
		return new Tuple<Boolean, String>(true, null);
	}

	/**
	 * Enables the map if it's in an acceptable state.
	 */
	public Tuple<Boolean, String> enableMap() {

		if (playerSpawns.size() < 1)
			return configureEnabled(false, "At least 1 player spawn point is required.");

		if (enemySpawns.size() < 1)
			return configureEnabled(false, "At least 1 enemy spawn point is required.");

		return configureEnabled(true, null);
	}
	//endregion

	//region Constructors
	public MapEntity() {
	}

	public MapEntity(String id, String name, Location origin) {
		this();

		this.id = id;
		this.name = name;
		originPoint = new BlockLocationEntity(origin);
		deleteKey = Integer.toString(100000 + new Random().nextInt(999999)).substring(0, 6);
	}
	//endregion

	//region Private Methods
	private Tuple<Boolean, String> configureEnabled(boolean enabled, String error) {
		this.enabled = enabled;
		return new Tuple<Boolean, String>(enabled, "Map not enabled (" + id + "). " + error);
	}
	//endregion
}