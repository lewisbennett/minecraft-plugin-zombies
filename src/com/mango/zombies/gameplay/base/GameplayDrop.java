package com.mango.zombies.gameplay.base;

import com.mango.zombies.gamemodes.base.ZombiesGamemode;
import com.mango.zombies.gameplay.GameplayPlayer;
import com.mango.zombies.helper.HiddenStringUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class GameplayDrop implements GameplayRegisterable, InventoryPickupItemEventRegisterable {

    //region Fields
    private final UUID uuid;

    private final ZombiesGamemode gamemode;
    //endregion

    //region Getters/Setters
    /**
     * Gets the material for this drop.
     */
    public abstract Material getMaterial();

    /**
     * Gets the sound played when this drop is picked up.
     */
    public abstract Sound getSound();

    /**
     * Gets the UUID of this gameplay registerable.
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Gets the gamemode that this drop belongs to.
     */
    public ZombiesGamemode getGamemode() {
        return gamemode;
    }
    //endregion

    //region Event Handlers
    /**
     * Called when an inventory picks up an item.
     * @param event The event caused by the pickup.
     */
    public void onInventoryPickUpItem(InventoryPickupItemEvent event) {

        event.setCancelled(true);

        if (!(event.getInventory().getHolder() instanceof Player))
            return;

        Player player = (Player)event.getInventory().getHolder();

        GameplayPlayer gameplayPlayer = null;

        for (GameplayPlayer queryPlayer : getGamemode().getGameplaySession().getPlayers()) {

            if (queryPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                gameplayPlayer = queryPlayer;
                break;
            }
        }

        if (gameplayPlayer == null)
            return;

        event.getItem().remove();

        gamemode.onDropPickedUp(gameplayPlayer, this);
    }
    //endregion

    //region Public Methods
    /**
     * Creates an item stack for the drop.
     */
    public ItemStack createItemStack() {

        ItemStack itemStack = new ItemStack(getMaterial(), 1);

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(HiddenStringUtils.encodeString(uuid.toString()));

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
    //endregion

    //region Constructors
    protected GameplayDrop(ZombiesGamemode zombiesGamemode) {

        this.gamemode = zombiesGamemode;

        uuid = UUID.randomUUID();
    }
    //endregion
}
