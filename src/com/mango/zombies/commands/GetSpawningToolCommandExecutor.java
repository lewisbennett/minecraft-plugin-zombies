package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.commands.base.PlayerOnlyCommandExecutor;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.gameplay.SpawningTool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetSpawningToolCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR_CONSOLE = "Correct usage: /getspawningtool [enemy ID] [spawn round] [player name]";
    public static final String CORRECT_USAGE_ERROR_PLAYER = "Correct usage: /getspawningtool [enemy ID] [spawn round]";
    public static final String INVALID_ENEMY_ID_ERROR = "Could not get Spawning Tool. %s is not a valid enemy ID.";
    public static final String INVALID_SPAWN_ROUND_ERROR = "Could not get Spawning Tool. %s is not a valid spawn round.";
    public static final String PLAYER_NOT_FOUND_ERROR = "Player not found.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        Player player;

        if (commandSender instanceof Player) {

            player = (Player)commandSender;

            if (args.length != 1)
                throw new CommandException(CORRECT_USAGE_ERROR_PLAYER);

        } else {

            if (args.length != 3)
                throw new CommandException(CORRECT_USAGE_ERROR_CONSOLE);

            player = Bukkit.getPlayer(args[2]);

            if (player == null)
                throw new CommandException(PLAYER_NOT_FOUND_ERROR);
        }

        EnemyEntity enemyEntity = null;

        for (EnemyEntity queryEnemy : PluginCore.getEnemies()) {

            if (queryEnemy.getId().equals(args[0])) {
                enemyEntity = queryEnemy;
                break;
            }
        }

        if (enemyEntity == null)
            throw new CommandException(String.format(INVALID_ENEMY_ID_ERROR, args[0]));

        int spawnRound = 0;

        try {
            spawnRound = Integer.parseInt(args[1]);
        } catch (Exception ignored) { }

        if (spawnRound < 1)
            throw new CommandException(String.format(INVALID_SPAWN_ROUND_ERROR, args[1]));

        SpawningTool spawningTool = new SpawningTool(enemyEntity, spawnRound);

        PluginCore.getGameplayService().register(spawningTool);

        player.getInventory().addItem(spawningTool.createItemStack());

        return "With the Spawning Tool, right click to spawn the specified enemy.";
    }
    //endregion
}
