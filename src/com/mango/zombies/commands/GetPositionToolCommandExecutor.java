package com.mango.zombies.commands;

import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.gameplay.GameplayCore;
import com.mango.zombies.gameplay.PositionTool;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.schema.Positionable;
import com.mango.zombies.services.GameplayService;
import com.mango.zombies.services.MessagingService;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GetPositionToolCommandExecutor extends PlayerOnlyCommandExecutor implements Listener {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /getpositiontool [map/door] [map ID]";
    //endregion

    //region Event Handlers
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        ItemStack itemStack = event.getPlayer().getInventory().getItemInMainHand();

        if (!doesItemStackHaveValidLore(itemStack))
            return;

        UUID uuid;

        try {

            uuid = UUID.fromString(HiddenStringUtils.extractHiddenString(itemStack.getItemMeta().getLore().get(0)));

        } catch (Exception e) {
            return;
        }

        if (GameplayCore.getPositionTools().get(uuid) != null)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_BLOCK && doesItemStackHaveValidLore(event.getItem()))
            return;

        UUID uuid;

        try {

            uuid = UUID.fromString(HiddenStringUtils.extractHiddenString(event.getItem().getItemMeta().getLore().get(0)));

        } catch (Exception e) {
            return;
        }

        PositionTool positionTool = GameplayCore.getPositionTools().get(uuid);

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

            positionTool.setTop(new LocationEntity(event.getClickedBlock().getLocation()));
            MessagingService.showSuccess(event.getPlayer(), "Top position set to: " + positionTool.getTop().toString() + ".");
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            positionTool.setBottom(new LocationEntity(event.getClickedBlock().getLocation()));
            MessagingService.showSuccess(event.getPlayer(), "Bottom position set to: " + positionTool.getBottom().toString() + ".");
        }

        if (positionTool.getTop() == null || positionTool.getBottom() == null)
            return;

        event.getPlayer().getInventory().remove(event.getItem());
        GameplayCore.getPositionTools().remove(uuid);

        if (positionTool.getPositionable().equals(Positionable.DOOR)) {

            // Save door.

            MessagingService.showSuccess(event.getPlayer(), "Door added successfully.");

            return;
        }

        if (positionTool.getPositionable().equals(Positionable.MAP)) {

            positionTool.getMap().setTop(positionTool.getTop());
            positionTool.getMap().setBottom(positionTool.getBottom());

            MessagingService.showSuccess(event.getPlayer(), "Top and bottom positions for " + positionTool.getMap().getId() + " set.");
        }
    }

    @Override
    public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

        if (args.length != 2) {
            MessagingService.showError(player, CORRECT_USAGE_ERROR);
            return true;
        }

        Tuple<ItemStack, PositionTool> positionTool = GameplayService.givePositionTool(player, args[1], args[0]);

        if (positionTool == null)
            return true;

        player.getInventory().addItem(positionTool.a());
        MessagingService.showSuccess(player, "With the Position Tool, left click to set the top point and right click to set the bottom point. Changes will be applied once both points have been selected.");

        return true;
    }
    //endregion

    //region Private Methods
    private boolean doesItemStackHaveValidLore(ItemStack itemStack) {

        return itemStack != null
                && itemStack.getItemMeta() != null
                && itemStack.getItemMeta().getLore() != null
                && itemStack.getItemMeta().getLore().size() > 0
                && itemStack.getItemMeta().getLore().get(0) != null;
    }
    //endregion
}
