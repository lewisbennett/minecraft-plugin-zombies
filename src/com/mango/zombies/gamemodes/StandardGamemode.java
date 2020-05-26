package com.mango.zombies.gamemodes;

import com.mango.zombies.Log;
import com.mango.zombies.Main;
import com.mango.zombies.PluginCore;
import com.mango.zombies.Time;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.WeaponEntity;
import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.GameplayEnemy;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.GameplayWeapon;
import com.mango.zombies.schema.WeaponService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StandardGamemode extends ZombiesGamemode {

    //region Constant Values
    public static final String GAMEMODE_NAME = "Zombies";
    //endregion

    //region Fields
    private int totalEnemiesInRound;
    private int enemiesKilledInRound;
    private int enemiesSpawnedInRound;
    private int spawnEnemyTaskReference;
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
            LocationEntity[] playerSpawns = getGameplaySession().getMap().getPlayerSpawns();

            LocationEntity playerSpawn = playerSpawns[new Random().nextInt(playerSpawns.length)];

            player.teleport(new Location(player.getWorld(), playerSpawn.getX(), playerSpawn.getY(), playerSpawn.getZ()));

            WeaponEntity[] weaponEntities = PluginCore.getWeapons();

            GameplayWeapon gameplayWeapon = new GameplayWeapon(weaponEntities[new Random().nextInt(weaponEntities.length)]);

            PluginCore.getGameplayService().register(gameplayWeapon);

            gameplayWeapon.setInitialMagazineCount(20);

            gameplayWeapon.giveItemStack(player);
        }

        startRound(1);
    }

    @Override
    public void startRound(int round) {

        super.startRound(round);

        for (GameplayPlayer gameplayPlayer : getGameplaySession().getPlayers()) {

            Player player = gameplayPlayer.getPlayer();

            playSound(player, getGameplaySession().getMap().getRoundStartSound(), 10);

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
            playSound(gameplayPlayer.getPlayer(), getGameplaySession().getMap().getRoundEndSound(), 10);

        instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, this::roundDelay_runnable, Time.fromSeconds(10).totalTicks());
    }
    //endregion

    //region Runnables
    private void roundDelay_runnable() {
        startRound(getCurrentRound() + 1);
    }

    private void spawnEnemy_runnable() {

        if (enemiesSpawnedInRound >= totalEnemiesInRound || enemiesSpawnedInRound - enemiesKilledInRound > 24)
            return;

        GameplayPlayer[] gameplayPlayers = getGameplaySession().getPlayers();

        LocationEntity spawnLocation = null;
        Random random = new Random();

        GameplayPlayer gameplayPlayer = gameplayPlayers[random.nextInt(gameplayPlayers.length)];

        Player player = gameplayPlayer.getPlayer();

        Location top = player.getLocation().add(20, 20, 20);
        Location bottom = player.getLocation().subtract(20, 20, 20);

        LocationEntity[] enemySpawns = getEnemySpawnsBetweenPoints(top, bottom);

        if (enemySpawns.length > 0)
            spawnLocation = enemySpawns[random.nextInt(enemySpawns.length)];

        if (spawnLocation == null)
            return;

        EnemyEntity[] enemyEntities = PluginCore.getEnemies();

        EnemyEntity enemyEntity = enemyEntities[random.nextInt(enemyEntities.length)];

        GameplayEnemy gameplayEnemy = new GameplayEnemy(enemyEntity);

        gameplayEnemy.setGameplaySession(getGameplaySession());

        gameplayEnemy.setSpawnLocation(new Location(Bukkit.getWorld(getGameplaySession().getMap().getWorldName()), spawnLocation.getX(), spawnLocation.getY() + 1, spawnLocation.getZ()));

        gameplayEnemy.setCurrentHealth(PluginCore.getGameplayService().calculateHealthForRound(getCurrentRound(), enemyEntity.getRoundMultiplier(), enemyEntity.getMaxHealth()));

        gameplayEnemy.spawn();

        enemiesSpawnedInRound++;
    }
    //endregion

    //region Private Methods
    private LocationEntity[] getEnemySpawnsBetweenPoints(Location top, Location bottom) {

        List<LocationEntity> enemySpawns = new ArrayList<LocationEntity>();

        for (LocationEntity enemySpawn : getGameplaySession().getMap().getEnemySpawns()) {

            boolean isWithinX = enemySpawn.getX() >= Math.min(top.getBlockX(), bottom.getBlockX()) && enemySpawn.getX() <= Math.max(top.getBlockX(), bottom.getBlockX());
            boolean isWithinY = enemySpawn.getY() >= Math.min(top.getBlockY(), bottom.getBlockY()) && enemySpawn.getY() <= Math.max(top.getBlockY(), bottom.getBlockY());
            boolean isWithinZ = enemySpawn.getZ() >= Math.min(top.getBlockZ(), bottom.getBlockZ()) && enemySpawn.getZ() <= Math.max(top.getBlockZ(), bottom.getBlockZ());

            if (isWithinX && isWithinY && isWithinZ)
                enemySpawns.add(enemySpawn);
        }

        return enemySpawns.toArray(new LocationEntity[0]);
    }

    private void playSound(Player player, Sound sound, float volume) {
        player.getWorld().playSound(player.getLocation(), sound, volume, 1);
    }
    //endregion
}
