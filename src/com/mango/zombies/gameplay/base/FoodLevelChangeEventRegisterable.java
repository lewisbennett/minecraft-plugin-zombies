package com.mango.zombies.gameplay.base;

import org.bukkit.event.entity.FoodLevelChangeEvent;

public interface FoodLevelChangeEventRegisterable {

    //region Event Handlers
    /**
     * Called when the food level of a HumanEntity changes.
     * @param event The event.
     */
    void onFoodLevelChanged(FoodLevelChangeEvent event);
    //endregion
}
