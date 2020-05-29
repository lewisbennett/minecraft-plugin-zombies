package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.LockedLocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BasePositionTool;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ZombieCurePositionTool extends BasePositionTool {

    //region Getters/Setters
    /**
     * Gets the name of this position tool.
     */
    public String getPositionToolName() {
        return getMapEntity().getName() + " (zombie cure spawn)";
    }

    /**
     * Gets the description on how to use this position tool.
     */
    public String getUsageDescription() {
        return "With the Position Tool, left click to remove a zombie cure spawn point and right click to add one. Changes will be applied immediately.";
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

        LocationEntity lockedLocationEntity = null;

        for (LocationEntity queryLocation : getMapEntity().getTurnedGamemodeConfig().getZombieCureSpawns()) {

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

        getMapEntity().getTurnedGamemodeConfig().removeZombieCureLocation(lockedLocationEntity);

        PluginCore.getMessagingService().success(player, "Removed zombie cure spawn point.");
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

        for (LocationEntity queryLocation : getMapEntity().getTurnedGamemodeConfig().getZombieCureSpawns()) {

            boolean doesMatchX = queryLocation.getX() == block.getLocation().getBlockX();
            boolean doesMatchY = queryLocation.getY() == block.getLocation().getBlockY();
            boolean doesMatchZ = queryLocation.getZ() == block.getLocation().getBlockZ();

            if (doesMatchX && doesMatchY && doesMatchZ) {
                locationEntity = queryLocation;
                break;
            }
        }

        if (locationEntity != null) {
            PluginCore.getMessagingService().error(player, "A zombie cure spawn point already exists here.");
            return;
        }

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

        getMapEntity().getTurnedGamemodeConfig().addZombieCureSpawn(new LockedLocationEntity(block.getLocation()));

        PluginCore.getMessagingService().success(player, "Zombie cure spawn point added.");
    }
    //endregion

    //region Constructors
    public ZombieCurePositionTool(MapEntity mapEntity) {
        super(mapEntity);
    }
    //endregion
}
