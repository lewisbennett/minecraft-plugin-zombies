package com.mango.zombies.commands;

import com.mango.zombies.PluginCore;
import com.mango.zombies.base.BaseCommandExecutor;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.services.MessagingService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class CreateEnemyCommandExecutor extends BaseCommandExecutor {

    //region Constant Values
    public static final String CORRECT_USAGE_ERROR = "Correct usage: /createenemy [ID] [entity type] [round one health]";
    public static final String ENEMY_ID_ALREADY_EXISTS_ERROR = "Enemy not created. %s already exists.";
    public static final String INVALID_ENTITY_TYPE_ERROR = "Enemy not crated. %s is not a valid entity type.";
    public static final String INVALID_ROUND_ONE_HEALTH_ERROR = "Enemy not crated. The round one health was invalid.";
    //endregion

    //region Event Handlers
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (args.length < 2) {
            MessagingService.showError(commandSender, CORRECT_USAGE_ERROR);
            return true;
        }

        if (!isValidEnemyId(args[0])) {
            MessagingService.showError(commandSender, String.format(ENEMY_ID_ALREADY_EXISTS_ERROR, args[0]));
            return true;
        }

        EntityType entityType = null;

        try{
            entityType = EntityType.valueOf(args[1]);
        } catch (Exception ignored) { }

        if (entityType == null) {
            MessagingService.showError(commandSender, String.format(INVALID_ENTITY_TYPE_ERROR, args[1]));
            return true;
        }

        int roundOneHealth = 0;

        try {
            roundOneHealth = Integer.parseInt(args[2]);
        } catch (Exception ignored) { }

        if (roundOneHealth < 1) {
            MessagingService.showError(commandSender, INVALID_ROUND_ONE_HEALTH_ERROR);
            return true;
        }

        EnemyEntity enemy = new EnemyEntity(args[0], entityType, roundOneHealth);

        PluginCore.getEnemies().add(enemy);
        MessagingService.showSuccess(commandSender, "Successfully created enemy: " + ChatColor.BOLD + enemy.getId());

        return true;
    }
    //endregion

    //region Private Methods
    private boolean isValidEnemyId(String enemyId) {

        for (EnemyEntity enemy : PluginCore.getEnemies()) {

            if (enemy.getId().equalsIgnoreCase(enemyId))
                return false;
        }

        return true;
    }
    //endregion
}
