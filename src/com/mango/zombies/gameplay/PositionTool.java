package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.gameplay.base.BlockBreakEventRegisterable;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.PlayerInteractEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.Positionable;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PositionTool extends GameplayRegisterable implements BlockBreakEventRegisterable, PlayerInteractEventRegisterable {

    //region Fields
    private LocationEntity bottom;
    private LocationEntity top;

    private MapEntity map;

    private String positionable;

    private UUID uuid;
    //endregion

    //region Getters/Setters
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

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

            top = new LocationEntity(clickedBlock.getLocation());
            PluginCore.getMessagingService().success(event.getPlayer(), "Top position set to: " + top.toString() + ".");
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            bottom = new LocationEntity(clickedBlock.getLocation());
            PluginCore.getMessagingService().success(event.getPlayer(), "Bottom position set to: " + bottom.toString() + ".");
        }

        if (top == null || bottom == null)
            return;

        event.getPlayer().getInventory().remove(event.getItem());

        if (positionable.equals(Positionable.DOOR)) {

            // Save door.

            PluginCore.getMessagingService().success(event.getPlayer(), "Door added successfully.");

            return;
        }

        if (positionable.equals(Positionable.MAP)) {

            map.setTopPoint(top);
            map.setBottomPoint(bottom);

            PluginCore.getMessagingService().success(event.getPlayer(), "Top and bottom positions for " + map.getId() + " set.");
        }
    }
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

        itemMeta.setDisplayName(ChatColor.AQUA + "Position Tool: " + ChatColor.RESET + "" + ChatColor.GREEN + map.getId() + " (" + positionable + ")");

        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
    }
    //endregion

    //region Constructors
    public PositionTool(MapEntity map, String positionable) {

        this.map = map;
        this.positionable = positionable;

        uuid = UUID.randomUUID();
    }
    //endregion
}
