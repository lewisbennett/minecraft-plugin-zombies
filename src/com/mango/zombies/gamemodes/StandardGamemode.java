package com.mango.zombies.gamemodes;

import com.mango.zombies.Main;
import com.mango.zombies.PluginCore;
import com.mango.zombies.Time;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.LockedLocationEntity;
import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.GameplayLoadout;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.helper.SoundUtil;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class StandardGamemode extends ZombiesGamemode {

    //region Constant Values
    public static final String GAMEMODE_NAME = "Zombies";
    //endregion

    //region Fields
    private int totalEnemiesInRound;
    private int enemiesKilledInRound;
    private int enemiesSpawnedInRound;
    private int roundDelayTaskReference;
    private int spawnEnemyTaskReference;

    private final Random random = new Random();
    //endregion

    //region Getters/Setters
    /**
     * Gets the name of the gamemode.
     */
    public String getName() {
        return GAMEMODE_NAME;
    }

    /**
     * Gets the minimum number of players needed to begin the gamemode
     */
    public int getMinimumPlayers() {
        return 1;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when an enemy is damaged.
     * @param gameplayPlayer The player that damaged the enemy.
     * @param gameplayEnemy The damaged enemy.
     */
    @Override
    public void onEnemyDamaged(GameplayPlayer gameplayPlayer, GameplayEnemy gameplayEnemy) {

        super.onEnemyDamaged(gameplayPlayer, gameplayEnemy);

        gameplayPlayer.addPoints(10);
    }

    /**
     * Called when an enemy despawns.
     * @param gameplayEnemy The despawned enemy.
     */
    @Override
    public void onEnemyDespawned(GameplayEnemy gameplayEnemy) {

        super.onEnemyDespawned(gameplayEnemy);

        enemiesSpawnedInRound--;
    }

    /**
     * Called when an enemy is killed.
     * @param gameplayPlayer The player that killed the enemy.
     * @param gameplayEnemy The killed enemy.
     */
    @Override
    public void onEnemyKilled(GameplayPlayer gameplayPlayer, GameplayEnemy gameplayEnemy, String weaponServiceType) {

        super.onEnemyKilled(gameplayPlayer, gameplayEnemy, weaponServiceType);

        switch (weaponServiceType) {

            case WeaponService.GUNSHOT:
                gameplayPlayer.addPoints(60);
                break;

            case WeaponService.MELEE:
                gameplayPlayer.addPoints(130);
                break;

            default:
                return;
        }

        enemiesKilledInRound++;

        if (enemiesKilledInRound >= totalEnemiesInRound)
            endRound();
    }
    //endregion

    //region Lifecycle
    @Override
    public void prepare() {

        super.prepare();

        for (GameplayPlayer gameplayPlayer : getGameplaySession().getPlayers()) {

            Player player = gameplayPlayer.getPlayer();

            teleportPlayerToRandomSpawnPoint(player);

            gameplayPlayer.setLoadout(new GameplayLoadout(gameplayPlayer, getGameplaySession().getMap().getStandardGamemodeConfig().getStartingLoadout()));

            gameplayPlayer.getLoadout().applyLoadout();
        }

        startRound(1);
    }

    @Override
    public void startRound(int round) {

        super.startRound(round);

        for (GameplayPlayer gameplayPlayer : getGameplaySession().getPlayers()) {

            Player player = gameplayPlayer.getPlayer();

            SoundUtil.playInfiniteSound(player, getGameplaySession().getMap().getRoundStartSound());

            player.sendMessage(ChatColor.RED + "Round " + round);
        }

        enemiesKilledInRound = 0;
        enemiesSpawnedInRound = 0;

        totalEnemiesInRound = PluginCore.getGameplayService().calculateEnemiesForRound(getGameplaySession().getPlayers().length, round);

        Main instance = Main.getInstance();

        long ticks = Time.fromSeconds(PluginCore.getGameplayService().calculateSpawnRateForRound(round)).totalTicks();

        spawnEnemyTaskReference = instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, this::spawnEnemy_runnable, ticks, ticks);
    }

    @Override
    public void endRound() {

        super.endRound();

        Main instance = Main.getInstance();

        instance.getServer().getScheduler().cancelTask(spawnEnemyTaskReference);

        for (GameplayPlayer gameplayPlayer : getGameplaySession().getPlayers())
            SoundUtil.playInfiniteSound(gameplayPlayer.getPlayer(), getGameplaySession().getMap().getRoundEndSound());

        roundDelayTaskReference = instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, this::roundDelay_runnable, Time.fromSeconds(10).totalTicks());
    }

    @Override
    public void endGame() {

        super.endGame();

        Main instance = Main.getInstance();

        if (instance.getServer().getScheduler().isCurrentlyRunning(spawnEnemyTaskReference))
            instance.getServer().getScheduler().cancelTask(spawnEnemyTaskReference);

        if (instance.getServer().getScheduler().isCurrentlyRunning(roundDelayTaskReference))
            instance.getServer().getScheduler().cancelTask(roundDelayTaskReference);
    }
    //endregion

    //region Runnables
    private void roundDelay_runnable() {
        startRound(getCurrentRound() + 1);
    }

    private void spawnEnemy_runnable() {

        if (enemiesSpawnedInRound >= totalEnemiesInRound || enemiesSpawnedInRound - enemiesKilledInRound > 24)
            return;

        GameplayPlayer gameplayPlayer = getRandomPlayer();

        if (gameplayPlayer == null)
            return;

        Player player = gameplayPlayer.getPlayer();

        LockedLocationEntity enemySpawnPoint = getRandomEnemySpawnPoint(player);

        if (enemySpawnPoint == null)
            return;

        GameplayEnemy gameplayEnemy = constructEnemyForSpawnPoint(enemySpawnPoint);

        if (gameplayEnemy == null)
            return;

        gameplayEnemy.spawn();

        enemiesSpawnedInRound++;
    }
    //endregion

    //region Private Methods
    private int calculateDifference(int v, int v1) {
        return Math.max(v, v1) - Math.min(v, v1);
    }

    private GameplayEnemy constructEnemyForSpawnPoint(LockedLocationEntity lockedLocation) {

        EnemyEntity enemyEntity = null;

        if (lockedLocation.getLockId() != null && !lockedLocation.getLockId().isEmpty())
            enemyEntity = getEnemy(lockedLocation.getLockId());

        if (enemyEntity == null)
            enemyEntity = getRandomEnemy();

        if (enemyEntity == null)
            return null;

        GameplayEnemy gameplayEnemy = new GameplayEnemy(enemyEntity);

        gameplayEnemy.setGameplaySession(getGameplaySession());

        gameplayEnemy.setSpawnLocation(new Location(Bukkit.getWorld(getGameplaySession().getMap().getWorldName()), lockedLocation.getX(), lockedLocation.getY() + 1, lockedLocation.getZ()));

        gameplayEnemy.applyHealth(getCurrentRound());

        return gameplayEnemy;
    }

    private EnemyEntity getEnemy(String enemyId) {

        for (EnemyEntity queryEnemy : PluginCore.getEnemies()) {

            if (queryEnemy.getId().equals(enemyId))
                return queryEnemy;
        }

        return null;
    }

    private EnemyEntity getRandomEnemy() {

        EnemyEntity[] enemies = PluginCore.getEnemies();

        return enemies.length > 0 ? enemies[random.nextInt(enemies.length)] : null;
    }

    private GameplayPlayer getRandomPlayer() {

        GameplayPlayer[] gameplayPlayers = getGameplaySession().getPlayers();

        return gameplayPlayers.length > 0 ? gameplayPlayers[random.nextInt(gameplayPlayers.length)] : null;
    }

    private LockedLocationEntity getRandomEnemySpawnPoint(Player player) {

        Map<Integer, LockedLocationEntity> lockedLocationMap = new HashMap<Integer, LockedLocationEntity>();

        for (LockedLocationEntity queryLockedLocation : getGameplaySession().getMap().getStandardGamemodeConfig().getEnemySpawns()) {

            int xDifference = calculateDifference(player.getLocation().getBlockX(), queryLockedLocation.getX());
            int yDifference = calculateDifference(player.getLocation().getBlockY(), queryLockedLocation.getY());
            int zDifference = calculateDifference(player.getLocation().getBlockZ(), queryLockedLocation.getZ());

            if (xDifference < 0)
                xDifference *= -1;

            if (yDifference < 0)
                yDifference *= -1;

            if (zDifference < 0)
                zDifference *= -1;

            lockedLocationMap.put(xDifference + yDifference + zDifference, queryLockedLocation);
        }

        List<Integer> sortedPoints = new ArrayList<Integer>(lockedLocationMap.keySet());

        Collections.sort(sortedPoints);

        List<LockedLocationEntity> lockedLocations = new ArrayList<LockedLocationEntity>();

        for (Integer sortedPoint : sortedPoints) {

            lockedLocations.add(lockedLocationMap.get(sortedPoint));

            if (lockedLocations.size() == 5)
                break;
        }

        return lockedLocations.size() > 0 ? lockedLocations.get(random.nextInt(lockedLocations.size())) : null;
    }

    private void teleportPlayerToRandomSpawnPoint(Player player) {

        // TODO: Evaluate the character assigned to the player, if any.

        LocationEntity[] playerSpawns = getGameplaySession().getMap().getStandardGamemodeConfig().getPlayerSpawns();

        LocationEntity playerSpawn = playerSpawns[random.nextInt(playerSpawns.length)];

        player.teleport(new Location(player.getWorld(), playerSpawn.getX(), playerSpawn.getY(), playerSpawn.getZ()));
    }
    //endregion
}
