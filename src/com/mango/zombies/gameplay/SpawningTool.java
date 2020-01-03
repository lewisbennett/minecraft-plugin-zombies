package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.helper.HiddenStringUtils;
import com.mango.zombies.services.GameplayService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SpawningTool implements Listener {

    //region Fields
    private ItemStack itemStack;
    private EnemyEntity enemy;
    private int spawnRound;
    private UUID uuid;
    //endregion

    //region Event Handlers
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        UUID uuid = HiddenStringUtils.extractUuidFromItemStack(event.getPlayer().getInventory().getItemInMainHand());

        if (uuid != null && uuid.equals(this.uuid))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        UUID uuid = HiddenStringUtils.extractUuidFromItemStack(event.getItem());

        if (uuid == null || !uuid.equals(this.uuid))
            return;

        itemStack = event.getItem();

        GameplayEnemy gameplayEnemy = new GameplayEnemy(enemy);
        gameplayEnemy.setSpawnLocation(event.getClickedBlock().getLocation().add(0, 1, 0));

        gameplayEnemy.setHealth(GameplayService.calculateHealthForRound(spawnRound, enemy));
        gameplayEnemy.spawn();
    }
    //endregion

    //region Constructors
    public SpawningTool(ItemStack itemStack, String enemyId, UUID uuid, int spawnRound) {

        EnemyEntity enemy = null;

        for (EnemyEntity queryEnemy : PluginCore.getEnemies()) {

            if (queryEnemy.getId().equals(enemyId)) {
                enemy = queryEnemy;
                break;
            }
        }

        this.itemStack = itemStack;
        this.enemy = enemy;
        this.spawnRound = spawnRound;
        this.uuid = uuid;
    }

    public SpawningTool(ItemStack itemStack, EnemyEntity enemy, UUID uuid, int spawnRound) {

        this.itemStack = itemStack;
        this.enemy = enemy;
        this.spawnRound = spawnRound;
        this.uuid = uuid;
    }
    //endregion
}
