package com.mango.zombies.listeners;

import com.mango.zombies.PluginCore;
import com.mango.zombies.gameplay.base.BaseGameplayRegisterable;
import com.mango.zombies.gameplay.base.FoodLevelChangeEventRegisterable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onFoodLevelChanged(FoodLevelChangeEvent event) {
        handOffToRegisterables(event);
    }
    //endregion

    //region Private Methods
    private void handOffToRegisterables(FoodLevelChangeEvent event) {

        for (BaseGameplayRegisterable queryRegisterable : PluginCore.getGameplayService().getRegisterables()) {

            if (queryRegisterable instanceof FoodLevelChangeEventRegisterable)
                ((FoodLevelChangeEventRegisterable)queryRegisterable).onFoodLevelChanged(event);
        }
    }
    //endregion
}
