package com.mango.zombies.gameplay.base;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.*;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.Positionable;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BasePositionTool extends GameplayRegisterable implements BlockBreakEventRegisterable, PlayerInteractEventRegisterable {

    //region Fields
    private final MapEntity mapEntity;

    private final UUID uuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets the map.
     */
    public MapEntity getMapEntity() {
        return mapEntity;
    }

    /**
     * Gets the name of this position tool.
     */
    public abstract String getPositionToolName();

    /**
     * Gets the description on how to use this position tool.
     */
    public abstract String getUsageDescription();

    /**
     * Gets the UUID of this gameplay registerable.
     */
    public UUID getUUID() {
        return uuid;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when a block breaks.
     * @param event The event caused by the break.
     */
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(true);
    }

    /**
     * Called when a player interacts with this weapon.
     * @param event The event caused by the interaction.
     */
    public void onPlayerInteract(PlayerInteractEvent event) {

        Block clickedBlock = event.getClickedBlock();
        ItemStack itemStack = event.getItem();

        if (clickedBlock == null || itemStack == null)
            return;

        switch (event.getAction()) {

            case LEFT_CLICK_BLOCK:
                onPlayerLeftClickBlock(event.getPlayer(), clickedBlock, itemStack);
                return;

            case RIGHT_CLICK_BLOCK:
                onPlayerRightClickBlock(event.getPlayer(), clickedBlock, itemStack);
        }
    }

    /**
     * Called when a player left clicks a block.
     * @param player The player who clicked the block.
     * @param block The clicked block.
     * @param itemStack The item stack used to click the block.
     */
    public void onPlayerLeftClickBlock(Player player, Block block, ItemStack itemStack) { }

    /**
     * Called when a player right clicks a block.
     * @param player The player who clicked the block.
     * @param block The clicked block.
     * @param itemStack The item stack used to click the block.
     */
    public void onPlayerRightClickBlock(Player player, Block block, ItemStack itemStack) { }
    //endregion

    //region Public Methods
    /**
     * Gives a player this weapon as a usable item.
     * @param player The player to give the weapon to.
     */
    public void giveItemStack(Player player) {

        ItemStack itemStack = new ItemStack(PluginCore.getConfig().getPositionToolItem(), 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);

        itemMeta.setDisplayName(ChatColor.AQUA + "Position Tool: " + ChatColor.RESET + "" + ChatColor.GREEN + getPositionToolName());

        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }
    //endregion

    //region Constructors
    protected BasePositionTool(MapEntity mapEntity) {

        this.mapEntity = mapEntity;

        uuid = UUID.randomUUID();
    }
    //endregion

    //region Public Static Methods
    /**
     * Gets a Position Tool for a specific positionable.
     * @param mapEntity The map that the location is being configured for.
     * @param positionable The positionable to configure.
     */
    public static BasePositionTool getPositionToolForPositionable(MapEntity mapEntity, String positionable) {

        switch (positionable) {

            case Positionable.MAP_BOUNDS:
                return new MapBoundsPositionTool(mapEntity);

            case Positionable.MAP_ORIGIN:
                return new MapOriginPositionTool(mapEntity);

            case Positionable.PLAYER_SPAWN_STANDARD:
                return new PlayerSpawnStandardPositionTool(mapEntity);

            case Positionable.PLAYER_SPAWN_TURNED:
                return new PlayerSpawnTurnedPositionTool(mapEntity);

            case Positionable.ZOMBIE_CURE:
                return new ZombieCurePositionTool(mapEntity);

            default:
                return null;
        }
    }
    //endregion
}
