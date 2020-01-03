package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.Positionable;
import com.mango.zombies.services.MessagingService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PositionTool implements Listener {

    //region Fields
    private LocationEntity bottom, top;
    private ItemStack itemStack;
    private MapEntity map;
    private String positionable;
    private UUID uuid;
    //endregion

    //region Getters/Setters
    /**
     * Gets the selected bottom point, if any.
     */
    public LocationEntity getBottom() {
        return bottom;
    }

    /**
     * Sets the selected bottom point.
     */
    public void setBottom(LocationEntity bottom) {
        this.bottom = bottom;
    }

    /**
     * Gets the position tool ItemStack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the position tool ItemStack.
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Gets the map.
     */
    public MapEntity getMap() {
        return map;
    }

    /**
     * Gets the positionable being configured by this position tool.
     */
    public String getPositionable() {
        return positionable;
    }

    /**
     * Gets the selected top point, if any.
     */
    public LocationEntity getTop() {
        return top;
    }

    /**
     * Sets the selected top position.
     */
    public void setTop(LocationEntity top) {
        this.top = top;
    }
    //endregion

    //region Event Handlers
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        UUID uuid = extractUuidFromItemStack(event.getPlayer().getInventory().getItemInMainHand());

        if (uuid != null && uuid.equals(this.uuid))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        UUID uuid = extractUuidFromItemStack(event.getItem());

        if (uuid == null || !uuid.equals(this.uuid))
            return;

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

            top = new LocationEntity(event.getClickedBlock().getLocation());
            MessagingService.showSuccess(event.getPlayer(), "Top position set to: " + top.toString() + ".");
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            bottom = new LocationEntity(event.getClickedBlock().getLocation());
            MessagingService.showSuccess(event.getPlayer(), "Bottom position set to: " + bottom.toString() + ".");
        }

        if (top == null || bottom == null)
            return;

        event.getPlayer().getInventory().remove(event.getItem());

        if (positionable.equals(Positionable.DOOR)) {

            // Save door.

            MessagingService.showSuccess(event.getPlayer(), "Door added successfully.");

            return;
        }

        if (positionable.equals(Positionable.MAP)) {

            map.setTop(top);
            map.setBottom(bottom);

            MessagingService.showSuccess(event.getPlayer(), "Top and bottom positions for " + map.getId() + " set.");
        }
    }
    //endregion

    //region Constructors
    public PositionTool(ItemStack itemStack, String mapId, String positionable, UUID uuid) {

        MapEntity map = null;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            if (queryMap.getId().equals(mapId)) {
                map = queryMap;
                break;
            }
        }

        this.itemStack = itemStack;
        this.map = map;
        this.positionable = positionable;
        this.uuid = uuid;
    }

    public PositionTool(ItemStack itemStack, MapEntity map, String positionable, UUID uuid) {

        this.itemStack = itemStack;
        this.map = map;
        this.positionable = positionable;
        this.uuid = uuid;
    }
    //endregion

    //region Private Methods
    private UUID extractUuidFromItemStack(ItemStack itemStack) {

        if (itemStack == null
                || itemStack.getItemMeta() == null
                || itemStack.getItemMeta().getLore() == null
                || itemStack.getItemMeta().getLore().size() < 1
                || itemStack.getItemMeta().getLore().get(0) == null)
            return null;

        try{
            return UUID.fromString(HiddenStringUtils.extractHiddenString(itemStack.getItemMeta().getLore().get(0)));
        } catch (Exception e) {
            return null;
        }
    }
    //endregion
}
