package com.mango.zombies.commands;

import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.gameplay.PositionTool;
import com.mango.zombies.services.GameplayService;
import com.mango.zombies.services.MessagingService;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetPositionToolCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /getpositiontool [map/door] [map ID]";
    //endregion

    //region Event Handlers
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
}
