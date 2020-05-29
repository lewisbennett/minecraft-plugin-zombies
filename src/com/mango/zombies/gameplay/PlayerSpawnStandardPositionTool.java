package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LockedLocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BasePositionTool;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerSpawnStandardPositionTool extends BasePositionTool {

    //region Getters/Setters
    /**
     * Gets the name of this position tool.
     */
    public String getPositionToolName() {
        return getMapEntity().getName() + " (Standard player spawn)";
    }

    /**
     * Gets the description on how to use this position tool.
     */
    public String getUsageDescription() {
        return "With the Position Tool, left click to remove a player spawn point and right click to add one. Changes will be applied immediately.";
    }
    //endregion

    //region Event Handlers
    /**
     * Called when a player left clicks a block.
     * @param player The player who clicked the block.
     * @param block The clicked block.
     * @param itemStack The item stack used to click the block.
     */
    @Override
    public void onPlayerLeftClickBlock(Player player, Block block, ItemStack itemStack) {

        LockedLocationEntity lockedLocationEntity = null;

        for (LockedLocationEntity queryLocation : getMapEntity().getStandardGamemodeConfig().getPlayerSpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                lockedLocationEntity = queryLocation;
                break;
            }
        }

        if (lockedLocationEntity == null)
            return;

        getMapEntity().getStandardGamemodeConfig().removePlayerSpawnLocation(lockedLocationEntity);

        PluginCore.getMessagingService().success(player, "Removed player spawn point.");
    }

    /**
     * Called when a player right clicks a block.
     * @param player The player who clicked the block.
     * @param block The clicked block.
     * @param itemStack The item stack used to click the block.
     */
    @Override
    public void onPlayerRightClickBlock(Player player, Block block, ItemStack itemStack) {

        LockedLocationEntity lockedLocationEntity = null;

        for (LockedLocationEntity queryLocation : getMapEntity().getStandardGamemodeConfig().getPlayerSpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                lockedLocationEntity = queryLocation;
                break;
            }
        }

        if (lockedLocationEntity != null) {
            PluginCore.getMessagingService().error(player, "A player spawn point already exists here.");
            return;
        }

        for (LockedLocationEntity queryLocation : getMapEntity().getStandardGamemodeConfig().getEnemySpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                lockedLocationEntity = queryLocation;
                break;
            }
        }

        if (lockedLocationEntity != null) {
            PluginCore.getMessagingService().error(player, "An enemy spawn point already exists here.");
            return;
        }

        getMapEntity().getStandardGamemodeConfig().addPlayerSpawnLocation(new LockedLocationEntity(block.getLocation()));

        PluginCore.getMessagingService().success(player, "Player spawn point added.");
    }
    //endregion

    //region Constructors
    public PlayerSpawnStandardPositionTool(MapEntity mapEntity) {
        super(mapEntity);
    }
    //endregion
}
