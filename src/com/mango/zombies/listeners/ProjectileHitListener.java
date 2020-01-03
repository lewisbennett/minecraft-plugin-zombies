package com.mango.zombies.listeners;

import com.mango.zombies.gameplay.GameplayEnemy;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;
import java.util.List;

public class ProjectileHitListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        if (!(event.getEntity() instanceof Snowball) || !(event.getHitEntity() instanceof LivingEntity))
            return;

        Snowball snowball = (Snowball)event.getEntity();
        LivingEntity impactEntity = (LivingEntity)event.getHitEntity();

        String[] breakdown = snowball.getName().split(":");

        if (breakdown.length != 3 || !breakdown[0].equals("zombies") || !breakdown[1].equals("bullet"))
            return;

        int damage = 0;

        try{

            damage = Integer.parseInt(breakdown[2]);

        } catch (Exception ignored) { }

        if (damage < 1)
            damage = 1;

        GameplayEnemy gameplayEnemy = null;

        for (GameplayEnemy queryEnemy : gameplayEnemies) {

            if (queryEnemy.getEntity().getUniqueId().equals(impactEntity.getUniqueId())) {
                gameplayEnemy = queryEnemy;
                break;
            }
        }

        if (gameplayEnemy == null) {
            impactEntity.damage(damage);
            return;
        }

        gameplayEnemy.damage(damage);
    }
    //endregion

    //region Static Fields
    private static List<GameplayEnemy> gameplayEnemies = new ArrayList<GameplayEnemy>();
    //endregion

    //region Public Static Methods
    /**
     * Gets the active gameplay enemies.
     */
    public static List<GameplayEnemy> getGameplayEnemies() {
        return gameplayEnemies;
    }
    //endregion
}
