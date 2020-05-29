package com.mango.zombies.gamemodes;

import com.mango.zombies.Time;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.gameplay.ZombieCureDrop;
import com.mango.zombies.gameplay.base.GameplayDrop;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class TurnedGamemode extends ZombiesGamemode {

    //region Constant Values
    public static final String GAMEMODE_NAME = "Turned";
    //endregion

    //region Fields
    private GameplayPlayer currentSurvivor;
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
        return 2;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when a drop is picked up.
     * @param gameplayPlayer The player who picked up the drop.
     * @param gameplayDrop The picked up drop.
     */
    @Override
    public void onDropPickedUp(GameplayPlayer gameplayPlayer, GameplayDrop gameplayDrop) {

        super.onDropPickedUp(gameplayPlayer, gameplayDrop);

        if (gameplayDrop instanceof ZombieCureDrop)
            handleZombieCureDropPickedUp(gameplayPlayer, (ZombieCureDrop)gameplayDrop);
    }
    //endregion

    //region Lifecycle
    @Override
    public void prepare() {

        super.prepare();

        LocationEntity[] zombieCureSpawns = getGameplaySession().getMap().getTurnedGamemodeConfig().getZombieCureSpawns();

        LocationEntity zombieCureLocation = zombieCureSpawns[new Random().nextInt(zombieCureSpawns.length)];

        World world = Bukkit.getWorld(getGameplaySession().getMap().getWorldName());

        if (world == null)
            return;

        ZombieCureDrop zombieCureDrop = new ZombieCureDrop(this);

        zombieCureDrop.register();

        world.dropItem(new Location(world, zombieCureLocation.getX(), zombieCureLocation.getY(), zombieCureLocation.getZ()), zombieCureDrop.createItemStack());

        for (GameplayPlayer queryGameplayPlayer : getGameplaySession().getPlayers())
            configureZombie(queryGameplayPlayer);
    }
    //endregion

    //region Public Methods
    /**
     * Swaps the current survivor.
     * @param newSurvivor The new survivor.
     */
    public void swapCurrentSurvivor(GameplayPlayer newSurvivor) {

        if (currentSurvivor != null)
            configureZombie(currentSurvivor);

        currentSurvivor = newSurvivor;

        configureSurvivor(currentSurvivor);
    }
    //endregion

    //region Private Methods
    private void configureSurvivor(GameplayPlayer gameplayPlayer) {

        Player player = gameplayPlayer.getPlayer();

        // give random weapon

        // reset skin

        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, (int)Time.fromMinutes(6).totalTicks(), 1, true, false));

        player.setWalkSpeed(0.2f);

        // refill health
    }

    private void configureZombie(GameplayPlayer gameplayPlayer) {

        Player player = gameplayPlayer.getPlayer();

        player.getInventory().clear();

        // change skin

        if (player.hasPotionEffect(PotionEffectType.GLOWING))
            player.removePotionEffect(PotionEffectType.GLOWING);

        player.setWalkSpeed(0.5f);

        // refill health

        LocationEntity[] playerSpawns = getGameplaySession().getMap().getTurnedGamemodeConfig().getPlayerSpawns();

        LocationEntity spawnPoint = playerSpawns[new Random().nextInt(playerSpawns.length)];

        player.teleport(new Location(player.getWorld(), spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ()));
    }

    private void handleZombieCureDropPickedUp(GameplayPlayer gameplayPlayer, ZombieCureDrop zombieCureDrop){
        swapCurrentSurvivor(gameplayPlayer);
    }
    //endregion
}
