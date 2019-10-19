package com.mango.zombies.entities;

import com.mango.zombies.serializers.MapEntityJsonSerializer;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapEntity
{
	public static final String DELETE_KEY_JSON_TAG = "delete_key";
	public static final String ID_JSON_TAG = "id";
	public static final MapEntityJsonSerializer SERIALIZER = new MapEntityJsonSerializer();
	public static final String NAME_JSON_TAG = "name";
	public static final String ORIGIN_POINT_JSON_TAG = "origin_point";
	public static final String PLAYER_SPAWNS_JSON_TAG = "player_spawns";
	public static final String ENEMY_SPAWNS_JSON_TAG = "enemy_spawns";

	private String deleteKey;
	private String id;
	private String name;
	private LocationEntity originPoint;
	private List<LocationEntity> playerSpawns = new ArrayList<LocationEntity>();
	private List<EnemySpawnEntity> enemySpawns = new ArrayList<EnemySpawnEntity>();

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
	public LocationEntity getOriginPoint() {
		return originPoint;
	}

	/**
	 * Sets the map's origin point.
	 */
	public void setOriginPoint(LocationEntity originPoint) {
		this.originPoint = originPoint;
	}

	/**
	 * Sets the map's origin point.
	 */
	public void setOriginPoint(Location origin) {
		originPoint = new LocationEntity(origin);
	}

	/**
	 * Gets the locations where players can spawn in the map.
	 */
	public List<LocationEntity> getPlayerSpawns() {
		return playerSpawns;
	}

	/**
	 * Gets the locations where enemies can spawn in the map.
	 */
	public List<EnemySpawnEntity> getEnemySpawns() {
		return enemySpawns;
	}

	public MapEntity() {
	}

	public MapEntity(String id, String name, Location origin) {
		this();

		this.id = id;
		this.name = name;
		originPoint = new LocationEntity(origin);
		deleteKey = Integer.toString(100000 + new Random().nextInt(999999)).substring(0, 6);
	}
}