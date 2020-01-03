package com.mango.zombies.commands;

import com.mango.zombies.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.gameplay.PositionTool;
import com.mango.zombies.gameplay.SpawningTool;
import com.mango.zombies.services.GameplayService;
import com.mango.zombies.services.MessagingService;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetSpawningToolCommandExecutor extends PlayerOnlyCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /getspawningtool [enemy ID] [spawn round]";
    public static final String INVALID_SPAWN_ROUND_ERROR = "Could not get Spawning Tool. The specified spawn round was invalid.";
    //endregion

    //region Event Handlers
    @Override
    public boolean onSuccessfulCommand(Player player, Command command, String label, String[] args) {

        if (args.length != 2) {
            MessagingService.showError(player, CORRECT_USAGE_ERROR);
            return true;
        }

        int spawnRound = 0;

        try {
            spawnRound = Integer.parseInt(args[1]);
        } catch (Exception ignored) { }

        if (spawnRound < 1) {
            MessagingService.showError(player, INVALID_SPAWN_ROUND_ERROR);
            return true;
        }

        Tuple<ItemStack, SpawningTool> spawningTool = GameplayService.giveSpawningTool(player, args[0], spawnRound);

        if (spawningTool == null)
            return true;

        player.getInventory().addItem(spawningTool.a());
        MessagingService.showSuccess(player, "With the Spawning Tool, right click to spawn the specified enemy.");

        return true;
    }
    //endregion
}
