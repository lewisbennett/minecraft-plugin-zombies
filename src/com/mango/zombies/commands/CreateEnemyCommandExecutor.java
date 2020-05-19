package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.commands.base.BaseCommandExecutor;
import com.mango.zombies.entities.EnemyEntity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class CreateEnemyCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createenemy [ID] [entity type]";
    public static final String ENEMY_ID_ALREADY_EXISTS_ERROR = "Enemy not created. %s already exists.";
    public static final String INVALID_ENTITY_TYPE_ERROR = "Enemy not crated. %s is not a valid entity type.";
    //endregion

    //region Event Handlers
    @Override
    public String executeCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length < 2)
            throw new CommandException(CORRECT_USAGE_ERROR);

        if (!isValidEnemyId(args[0]))
            throw new CommandException(String.format(ENEMY_ID_ALREADY_EXISTS_ERROR, args[0]));

        EntityType entityType = null;

        try{
            entityType = EntityType.valueOf(args[1]);
        } catch (Exception ignored) { }

        if (entityType == null)
            throw new CommandException(String.format(INVALID_ENTITY_TYPE_ERROR, args[1]));

        EnemyEntity enemy = new EnemyEntity(args[0], entityType);
        PluginCore.addEnemy(enemy);

        return "Successfully created enemy: " + ChatColor.BOLD + enemy.getId();
    }
    //endregion

    //region Private Methods
    private boolean isValidEnemyId(String enemyId) {

        for (EnemyEntity enemy : PluginCore.getEnemies()) {

            if (enemy.getId().equals(enemyId))
                return false;
        }

        return true;
    }
    //endregion
}
