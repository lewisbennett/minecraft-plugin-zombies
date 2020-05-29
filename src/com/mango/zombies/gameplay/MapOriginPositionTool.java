package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BasePositionTool;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MapOriginPositionTool extends BasePositionTool {

    //region Getters/Setters
    /**
     * Gets the name of this position tool.
     */
    public String getPositionToolName() {
        return getMapEntity().getName() + " (map origin)";
    }

    /**
     * Gets the description on how to use this position tool.
     */
    public String getUsageDescription() {
        return "With the Position Tool, left or right click to set the origin point. Changes will be applied as soon as a point is selected.";
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
        onPlayerClickBlock(player, block, itemStack);
    }

    /**
     * Called when a player right clicks a block.
     * @param player The player who clicked the block.
     * @param block The clicked block.
     * @param itemStack The item stack used to click the block.
     */
    @Override
    public void onPlayerRightClickBlock(Player player, Block block, ItemStack itemStack) {
        onPlayerClickBlock(player, block, itemStack);
    }
    //endregion

    //region Constructors
    public MapOriginPositionTool(MapEntity mapEntity) {
        super(mapEntity);
    }
    //endregion

    //region Private Methods
    private void onPlayerClickBlock(Player player, Block block, ItemStack itemStack) {

        MapEntity mapEntity = getMapEntity();

        mapEntity.setOriginPoint(new LocationEntity(block.getLocation()));

        player.getInventory().remove(itemStack);

        PluginCore.getMessagingService().success(player, "Origin point for " + mapEntity.getId() + " set.");
    }
    //endregion
}
