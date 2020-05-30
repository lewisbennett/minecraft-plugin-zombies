package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;
import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import org.bukkit.Location;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapEntity {

	//region Constant Values
	public static final String INVALID_BOTTOM_POINT_ERROR = "Map not enabled. The bottom point of the map has not been configured.";
	public static final String INVALID_LOBBY_SPAWN_POINT_ERROR = "Map not enabled. The lobby spawn location has not been configured.";
	public static final String INVALID_MAX_PLAYERS_ERROR = "Map not enabled. Max players must be at least 1.";
	public static final String INVALID_TOP_POINT_ERROR = "Map not enabled. The top point of the map has not been configured.";
	public static final String NO_GAMEMODE_ERROR = "Map not enabled. At least 1 gamemode is required.";
	public static final String NO_ENEMY_SPAWN_POINTS_ERROR = "Map not enabled. At least 1 enemy spawn point is required.";
	public static final String NO_LOADOUTS_ERROR = "Map not enabled. At least 1 loadout is required.";
	public static final String NO_PLAYER_SPAWN_POINTS_ERROR = "Map not enabled. At least 1 player spawn point is required.";
	public static final String NO_STARTING_LOADOUT_ERROR = "Map not enabled. The starting loadout has not been configured.";
	public static final String SESSION_IN_PROGRESS_ERROR = "Map not disabled. A session is currently active.";
	//endregion

	//region Fields
	private boolean isEnabled;

	@Expose private int maxPlayers;
	@Expose private int mysteryBoxCost;
	@Expose private int packAPunchCost;

	@Expose private final List<DoorEntity> doors = new ArrayList<DoorEntity>();

	@Expose private final List<LocationEntity> spectatorSpawns = new ArrayList<LocationEntity>();

	@Expose private final List<SignEntity> signs = new ArrayList<SignEntity>();

	@Expose private final List<String> weaponBlacklist = new ArrayList<String>();
	@Expose private final List<String> weaponWhitelist = new ArrayList<String>();

	@Expose private LocationEntity lobbyPoint = new LocationEntity();
	@Expose private LocationEntity bottomPoint = new LocationEntity();
	@Expose private LocationEntity topPoint = new LocationEntity();
	@Expose private LocationEntity originPoint;

	@Expose private Sound roundEndSound;
	@Expose private Sound roundStartSound;

	@Expose private StandardGamemodeConfigEntity standardGamemodeConfig;

	@Expose private TurnedGamemodeConfigEntity turnedGamemodeConfig;

	@Expose private String deleteKey;
	@Expose private String id;
	@Expose private String name;
	@Expose private String worldName;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the map's doors.
	 */
	public DoorEntity[] getDoors() {
		return doors.toArray(new DoorEntity[0]);
	}

	/**
	 * Gets whether the map is enabled.
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Gets the bottom corner of the map.
	 */
	public LocationEntity getBottomPoint() {
		return bottomPoint;
	}

	/**
	 * Sets the bottom corner of the map.
	 */
	public void setBottomPoint(LocationEntity bottomPoint) {
		this.bottomPoint = bottomPoint;
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
	 * Gets the lobby spawn location.
	 */
	public LocationEntity getLobbyPoint() {
		return lobbyPoint;
	}

	/**
	 * Sets the lobby spawn location.
	 */
	public void setLobbyPoint(LocationEntity lobbyPoint) {
		this.lobbyPoint = lobbyPoint;
	}

	/**
	 * Gets the max player count.
	 */
	public int getMaxPlayers() {
		return maxPlayers;
	}

	/**
	 * Sets the max player count.
	 */
	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	/**
	 * Gets the mystery box cost.
	 */
	public int getMysteryBoxCost() {
		return mysteryBoxCost;
	}

	/**
	 * Sets the mystery box cost.
	 */
	public void setMysteryBoxCost(int mysteryBoxCost) {
		this.mysteryBoxCost = mysteryBoxCost;
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
	 * Gets the Pack-A-Punch cost.
	 */
	public int getPackAPunchCost() {
		return packAPunchCost;
	}

	/**
	 * Sets the Pack-A-Punch cost.
	 */
	public void setPackAPunchCost(int packAPunchCost) {
		this.packAPunchCost = packAPunchCost;
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
	 * Gets the signs, if any.
	 */
	public SignEntity[] getSigns() {
		return signs.toArray(new SignEntity[0]);
	}

	/**
	 * Gets the spectator spawn points.
	 */
	public LocationEntity[] getSpectatorSpawns() {
		return spectatorSpawns.toArray(new LocationEntity[0]);
	}

	/**
	 * Gets the standard gamemode configuration.
	 */
	public StandardGamemodeConfigEntity getStandardGamemodeConfig() {
		return standardGamemodeConfig;
	}

	/**
	 * Sets the standard gamemode configuration.
	 */
	public void setStandardGamemodeConfig(StandardGamemodeConfigEntity standardGamemodeConfig) {
		this.standardGamemodeConfig = standardGamemodeConfig;
	}

	/**
	 * Gets the top corner of the map.
	 */
	public LocationEntity getTopPoint() {
		return topPoint;
	}

	/**
	 * Sets the top corner of the map.
	 */
	public void setTopPoint(LocationEntity topPoint) {
		this.topPoint = topPoint;
	}

	/**
	 * Gets the turned gamemode configuration.
	 */
	public TurnedGamemodeConfigEntity getTurnedGamemodeConfig() {
		return turnedGamemodeConfig;
	}

	/**
	 * Sets the turned gamemode configuration.
	 */
	public void setTurnedGamemodeConfig(TurnedGamemodeConfigEntity turnedGamemodeConfig) {
		this.turnedGamemodeConfig = turnedGamemodeConfig;
	}

	/**
	 * Gets the weapon blacklist.
	 */
	public String[] getWeaponBlacklist() {
		return weaponBlacklist.toArray(new String[0]);
	}

	/**
	 * Gets the weapon whitelist.
	 */
	public String[] getWeaponWhitelist() {
		return weaponWhitelist.toArray(new String[0]);
	}

	/**
	 * Gets the world name.
	 */
	public String getWorldName() {
		return worldName;
	}

	/**
	 * Sets the world name.
	 */
	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}
	//endregion

	//region Public Methods
	/**
	 * Adds a door.
	 */
	public void addDoor(DoorEntity door) {
		doors.add(door);
	}

	/**
	 * Adds a sign.
	 */
	public void addSign(SignEntity sign) {
		signs.add(sign);
	}

	/**
	 * Adds a spectator spawn location.
	 */
	public void addSpectatorSpawnLocation(LocationEntity location) {
		spectatorSpawns.add(location);
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
	 * Disables the map, if it can.
	 */
	public void disableMap() {

		for (BaseGameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

			if (!(queryRegisterable instanceof GameplaySession))
				continue;

			GameplaySession querySession = (GameplaySession)queryRegisterable;

			if (querySession.getMap().getId().equals(id))
				throw new IllegalStateException(SESSION_IN_PROGRESS_ERROR);
		}

		isEnabled = false;
	}

	/**
	 * Enables the map, if it can.
	 */
	public void enableMap() {

		if (topPoint == null || topPoint.isEmpty())
			throw new IllegalStateException(INVALID_TOP_POINT_ERROR);

		if (bottomPoint == null || bottomPoint.isEmpty())
			throw new IllegalStateException(INVALID_BOTTOM_POINT_ERROR);

		if (standardGamemodeConfig == null && turnedGamemodeConfig == null)
			throw new IllegalStateException(NO_GAMEMODE_ERROR);

		if (standardGamemodeConfig != null) {

			if (standardGamemodeConfig.getEnemySpawns().length < 1)
				throw new IllegalStateException(NO_ENEMY_SPAWN_POINTS_ERROR);

			if (standardGamemodeConfig.getPlayerSpawns().length < 1)
				throw new IllegalStateException(NO_PLAYER_SPAWN_POINTS_ERROR);

			if (standardGamemodeConfig.getStartingLoadout() == null || standardGamemodeConfig.getStartingLoadout().isEmpty())
				throw new IllegalStateException(NO_STARTING_LOADOUT_ERROR);
		}

		if (turnedGamemodeConfig != null) {

			if (turnedGamemodeConfig.getPlayerSpawns().length < 1)
				throw new IllegalStateException(NO_PLAYER_SPAWN_POINTS_ERROR);

			if (turnedGamemodeConfig.getLoadouts().length < 1)
				throw new IllegalStateException(NO_LOADOUTS_ERROR);
		}

		if (maxPlayers < 1)
			throw new IllegalStateException(INVALID_MAX_PLAYERS_ERROR);

		if (lobbyPoint == null || lobbyPoint.isEmpty())
			throw new IllegalStateException(INVALID_LOBBY_SPAWN_POINT_ERROR);

		isEnabled = true;
	}

	/**
	 * Gets whether a location is within the map's bounds.
	 */
	public boolean isWithinMapBounds(String worldName, int x, int y, int z) {

		boolean isWithinWorld = this.worldName.equals(worldName);

		boolean isWithinX = x >= Math.min(topPoint.getX(), bottomPoint.getX()) && x <= Math.max(topPoint.getX(), bottomPoint.getX());
		boolean isWithinY = y >= Math.min(topPoint.getY(), bottomPoint.getY()) && y <= Math.max(topPoint.getY(), bottomPoint.getY());
		boolean isWithinZ = z >= Math.min(topPoint.getZ(), bottomPoint.getZ()) && z <= Math.max(topPoint.getZ(), bottomPoint.getZ());

		return isWithinWorld && isWithinX && isWithinY && isWithinZ;
	}

	/**
	 * Gets whether a location is within the map's bounds.
	 */
	public boolean isWithinMapBounds(Location location) {
		return isWithinMapBounds(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	/**
	 * Removes a door.
	 */
	public void removeDoor(DoorEntity door) {
		doors.remove(door);
	}

	/**
	 * Removes a sign.
	 */
	public void removeSign(SignEntity sign) {
		signs.remove(sign);
	}

	/**
	 * Removes a spectator spawn location.
	 */
	public void removeSpectatorSpawn(LocationEntity location) {
		spectatorSpawns.remove(location);
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

	public MapEntity(String id, String name, Location origin, String worldName) {
		this();

		this.id = id;
		this.name = name;
		this.worldName = worldName;

		deleteKey = Integer.toString(100000 + new Random().nextInt(999999)).substring(0, 6);
		originPoint = new LocationEntity(origin);

		MapConfigEntity config = PluginCore.getMapConfig();

		maxPlayers = config.getDefaultMaxPlayers();
		mysteryBoxCost = config.getDefaultMysteryBoxCost();
		packAPunchCost = config.getDefaultPackAPunchCost();

		roundEndSound = config.getDefaultRoundEndSound();
		roundStartSound = config.getDefaultRoundStartSound();
	}
	//endregion
}