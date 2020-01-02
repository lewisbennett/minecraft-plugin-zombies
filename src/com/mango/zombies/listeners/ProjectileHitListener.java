package com.mango.zombies.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    //region Event Handlers
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {

        if (!(event.getEntity() instanceof Snowball) || !(event.getHitEntity() instanceof LivingEntity))
            return;

        Snowball snowball = (Snowball)event.getEntity();
        LivingEntity impactEntity = (LivingEntity)event.getHitEntity();

        String[] breakdown = snowball.getName().split(":");

        if (breakdown.length != 2 || !breakdown[0].equals("zombies"))
            return;

        int damage = 0;

        try{

            damage = Integer.parseInt(breakdown[1]);

        } catch (Exception ignored) { }

        if (damage < 1)
            damage = 1;

        impactEntity.damage(damage);
    }
    //endregion
}
