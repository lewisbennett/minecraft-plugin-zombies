package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BasePositionTool;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerSpawnTurnedPositionTool extends BasePositionTool {

    //region Getters/Setters
    /**
     * Gets the name of this position tool, formatted.
     */
    public String getPositionToolName() {
        return getMapEntity().getName() + " (Turned player spawn)";
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

        LocationEntity locationEntity = null;

        for (LocationEntity queryLocation : getMapEntity().getTurnedGamemodeConfig().getPlayerSpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                locationEntity = queryLocation;
                break;
            }
        }

        if (locationEntity == null)
            return;

        getMapEntity().getTurnedGamemodeConfig().removePlayerSpawnLocation(locationEntity);

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

        LocationEntity locationEntity = null;

        for (LocationEntity queryLocation : getMapEntity().getTurnedGamemodeConfig().getPlayerSpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                locationEntity = queryLocation;
                break;
            }
        }

        if (locationEntity != null) {
            PluginCore.getMessagingService().error(player, "A player spawn point already exists here.");
            return;
        }

        getMapEntity().getTurnedGamemodeConfig().addPlayerSpawnLocation(new LocationEntity(block.getLocation()));

        PluginCore.getMessagingService().success(player, "Player spawn point added.");
    }
    //endregion

    //region Constructors
    public PlayerSpawnTurnedPositionTool(MapEntity mapEntity) {
        super(mapEntity);
    }
    //endregion
}
