package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import com.mango.zombies.PluginCore;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapEntity {

	//region Fields
	@Expose private List<String> enemyBlacklist = new ArrayList<String>();
	@Expose private List<String> enemyWhitelist = new ArrayList<String>();
	@Expose private List<LocationEntity> enemySpawns = new ArrayList<LocationEntity>();
	@Expose private List<LocationEntity> playerSpawns = new ArrayList<LocationEntity>();
	@Expose private List<String> weaponBlacklist = new ArrayList<String>();
	@Expose private List<String> weaponWhitelist = new ArrayList<String>();

	@Expose private LocationEntity bottom = new LocationEntity();
	@Expose private LocationEntity top = new LocationEntity();
	@Expose private LocationEntity originPoint;

	@Expose private Sound roundEndSound;
	@Expose private Sound roundStartSound;

	@Expose private String deleteKey;
	@Expose private String id;
	@Expose private String name;
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
	 * Gets the enemy blacklist.
	 */
	public List<String> getEnemyBlacklist() {
		return new ArrayList<String>(enemyBlacklist);
	}

	/**
	 * Gets the locations where enemies can spawn in the map.
	 */
	public List<LocationEntity> getEnemySpawns() {
		return new ArrayList<LocationEntity>(enemySpawns);
	}

	/**
	 * Gets the enemy whitelist.
	 */
	public List<String> getEnemyWhitelist() {
		return new ArrayList<String>(enemyWhitelist);
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
		return new ArrayList<LocationEntity>(playerSpawns);
	}

	/**
	 * Gets the round end sound.
	 */
	public Sound getRoundEndSound() {
		return roundEndSound;
	}

	/**
	 * Sets the round end sound.
	 */
	public void setRoundEndSound(Sound roundEndSound) {
		this.roundEndSound = roundEndSound;
	}

	/**
	 * Gets the round start sound.
	 */
	public Sound getRoundStartSound() {
		return roundStartSound;
	}

	/**
	 * Sets the round start sound.
	 */
	public void setRoundStartSound(Sound roundStartSound) {
		this.roundStartSound = roundStartSound;
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

	/**
	 * Gets the weapon blacklist.
	 */
	public List<String> getWeaponBlacklist() {
		return new ArrayList<String>(weaponBlacklist);
	}

	/**
	 * Gets the weapon whitelist.
	 */
	public List<String> getWeaponWhitelist() {
		return new ArrayList<String>(weaponWhitelist);
	}
	//endregion

	//region Public Methods
	/**
	 * Adds an entry to the enemy blacklist.
	 * @param enemyId The ID of the enemy to add.
	 */
	public void addEnemyBlacklistEntry(String enemyId) {
		enemyBlacklist.add(enemyId);
	}

	/**
	 * Adds an entry to the enemy whitelist.
	 * @param enemyId The ID of the enemy to add.
	 */
	public void addEnemyWhitelistEntry(String enemyId) {
		enemyWhitelist.add(enemyId);
	}

	/**
	 * Adds an enemy spawn location.
	 * @param location The enemy spawn location.
	 */
	public void addEnemySpawnLocation(LocationEntity location) {
		enemySpawns.add(location);
	}

	/**
	 * Adds a player spawn location.
	 * @param location The player spawn location.
	 */
	public void addPlayerSpawnLocation(LocationEntity location) {
		playerSpawns.add(location);
	}

	/**
	 * Adds an entry to the weapon blacklist.
	 * @param weaponId The ID of the enemy to add.
	 */
	public void addWeaponBlacklistEntry(String weaponId) {
		weaponBlacklist.add(weaponId);
	}

	/**
	 * Adds an entry to the weapon whitelist.
	 * @param weaponId The ID of the weapon to add.
	 */
	public void addWeaponWhitelistEntry(String weaponId) {
		weaponWhitelist.add(weaponId);
	}

	/**
	 * Removes an entry from the enemy blacklist.
	 * @param enemyId The ID of the enemy to remove.
	 */
	public void removeEnemyBlacklistEntry(String enemyId) {
		enemyBlacklist.remove(enemyId);
	}

	/**
	 * Removes an entry from the enemy whitelist.
	 * @param enemyId The ID of the enemy to remove.
	 */
	public void removeEnemyWhitelistEntry(String enemyId) {
		enemyWhitelist.remove(enemyId);
	}

	/**
	 * Removes an enemy spawn location.
	 * @param location The enemy spawn location to remove.
	 */
	public void removeEnemySpawnLocation(LocationEntity location) {
		enemySpawns.remove(location);
	}

	/**
	 * Removes a player spawn location.
	 * @param location The player spawn location to remove.
	 */
	public void removePlayerSpawnLocation(LocationEntity location) {
		playerSpawns.remove(location);
	}

	/**
	 * Removes an entry from the weapon blacklist.
	 * @param weaponId The ID of the weapon to remove.
	 */
	public void removeWeaponBlacklistEntry(String weaponId) {
		weaponBlacklist.remove(weaponId);
	}

	/**
	 * Removes an entry from the weapon whitelist.
	 * @param weaponId The ID of the weapon to remove.
	 */
	public void removeWeaponWhitelistEntry(String weaponId) {
		weaponWhitelist.remove(weaponId);
	}
	//endregion

	//region Constructors
	public MapEntity() { }

	public MapEntity(String id, String name, Location origin) {
		this();

		this.id = id;
		this.name = name;
		originPoint = new LocationEntity(origin);
		deleteKey = Integer.toString(100000 + new Random().nextInt(999999)).substring(0, 6);

		MapConfigEntity config = PluginCore.getMapConfig();

		roundEndSound = config.getDefaultRoundEndSound();
		roundStartSound = config.getDefaultRoundStartSound();
	}
	//endregion
}