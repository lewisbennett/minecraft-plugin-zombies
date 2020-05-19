package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.EnemyEntity;
import com.mango.zombies.gameplay.base.BlockBreakEventRegisterable;
import com.mango.zombies.gameplay.base.GameplayRegisterable;
import com.mango.zombies.gameplay.base.PlayerInteractEventRegisterable;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpawningTool implements GameplayRegisterable, PlayerInteractEventRegisterable, BlockBreakEventRegisterable {

    //region Fields
    private EnemyEntity enemyEntity;

    private int spawnRound;

    private UUID uuid;
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

        if (clickedBlock == null)
            return;

        GameplayEnemy gameplayEnemy = new GameplayEnemy(enemyEntity);
        gameplayEnemy.setSpawnLocation(clickedBlock.getLocation().add(0, 1, 0));

        gameplayEnemy.setHealth(PluginCore.getGameplayService().calculateHealthForRound(spawnRound, enemyEntity.getRoundMultiplier(), enemyEntity.getMaxHealth()));
        gameplayEnemy.spawn();
    }
    //endregion

    //region Getters/Setters
    /**
     * Gets the UUID of this gameplay registerable.
     */
    public UUID getUUID() {
        return uuid;
    }
    //endregion

    //region Public Methods
    /**
     * Gets this spawning tool as a usable item stack.
     */
    public ItemStack createItemStack() {

        ItemStack itemStack = new ItemStack(PluginCore.getConfig().getSpawningToolItem(), 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);

        itemMeta.setDisplayName(ChatColor.AQUA + "Spawning Tool: " + ChatColor.RESET + "" + ChatColor.GREEN + enemyEntity.getId() + " (Spawn round: " + spawnRound + ")");

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    //endregion

    //region Constructors
    public SpawningTool(EnemyEntity enemyEntity, int spawnRound) {

        this.enemyEntity = enemyEntity;
        this.spawnRound = spawnRound;

        uuid = UUID.randomUUID();
    }
    //endregion
}
