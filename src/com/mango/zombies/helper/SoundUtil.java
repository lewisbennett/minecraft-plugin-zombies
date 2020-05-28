package com.mango.zombies.helper;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundUtil {

    //region Public Static Methods
    /**
     * Plays a sound to a player that won't fade based on the player's location.
     * @param player The player to play the sound to.
     * @param sound The sound to play.
     */
    public static void playInfiniteSound(Player player, Sound sound) {
        playSound(player, sound, 100f);
    }

    /**
     * Plays a sound emitted from a location.
     * @param location The location where the sound originates from.
     * @param sound The sound to play.
     * @param volume The sound volume/distance at which it can be heard.
     */
    public static void playGlobalSound(Location location, Sound sound, float volume) {

        World world = location.getWorld();

        if (world != null)
            world.playSound(location, sound, volume, 1);
    }

    /**
     * Plays a sound to a player.
     * @param player The player to play the sound to.
     * @param sound The sound to play.
     * @param volume The sound volume/distance at which it can be heard.
     */
    public static void playSound(Player player, Sound sound, float volume) {

        Location location = player.getLocation();

        player.playSound(location.add(0, location.getBlockY() + (255 - location.getBlockY()), 0), sound, SoundCategory.MASTER, volume, 1f);
    }
    //endregion
}
