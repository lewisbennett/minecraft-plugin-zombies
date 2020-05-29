package com.mango.zombies.gameplay;

import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LoadoutEntity;
import com.mango.zombies.entities.WeaponEntity;

public class GameplayLoadout {

    //region Fields
    private final GameplayPlayer gameplayPlayer;

    private final LoadoutEntity loadoutEntity;

    private GameplayWeapon meleeWeapon;
    private GameplayWeapon primaryWeapon;
    private GameplayWeapon secondaryWeapon;
    private GameplayWeapon tertiaryWeapon;
    //endregion

    //region Getters/Setters
    /**
     * Gets the melee weapon.
     */
    public GameplayWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    /**
     * Sets the melee weapon, registers the new one and unregisters the old one.
     */
    public void setMeleeWeapon(GameplayWeapon meleeWeapon) {

        if (this.meleeWeapon != null)
            this.meleeWeapon.unregister();

        this.meleeWeapon = meleeWeapon;

        if (this.meleeWeapon == null)
            return;

        this.meleeWeapon.register();

        applyMeleeWeapon();
    }

    /**
     * Gets the primary weapon.
     */
    public GameplayWeapon getPrimaryWeapon() {
        return primaryWeapon;
    }

    /**
     * Sets the primary weapon, registers the new one and unregisters the old one.
     */
    public void setPrimaryWeapon(GameplayWeapon primaryWeapon) {

        if (this.primaryWeapon != null)
            this.primaryWeapon.unregister();

        this.primaryWeapon = primaryWeapon;

        if (this.primaryWeapon == null)
            return;

        this.primaryWeapon.register();

        applyPrimaryWeapon();
    }

    /**
     * Gets the secondary weapon.
     */
    public GameplayWeapon getSecondaryWeapon() {
        return secondaryWeapon;
    }

    /**
     * Sets the secondary weapon, registers the new one and unregisters the old one.
     */
    public void setSecondaryWeapon(GameplayWeapon secondaryWeapon) {

        if (this.secondaryWeapon != null)
            secondaryWeapon.unregister();

        this.secondaryWeapon = secondaryWeapon;

        if (this.secondaryWeapon == null)
            return;

        secondaryWeapon.register();

        applySecondaryWeapon();
    }

    /**
     * Gets the tertiary weapon.
     */
    public GameplayWeapon getTertiaryWeapon() {
        return tertiaryWeapon;
    }

    /**
     * Sets the tertiary weapon, registers the new one and unregisters the old one.
     */
    public void setTertiaryWeapon(GameplayWeapon tertiaryWeapon) {

        if (this.tertiaryWeapon != null)
            tertiaryWeapon.unregister();

        this.tertiaryWeapon = tertiaryWeapon;

        if (this.tertiaryWeapon == null)
            return;

        tertiaryWeapon.register();

        applyTertiaryWeapon();
    }
    //endregion

    //region Public Methods
    /**
     * Applies the loadout to the player's inventory.
     */
    public void applyLoadout() {

        applyMeleeWeapon();
        applyPrimaryWeapon();
        applySecondaryWeapon();
        applyTertiaryWeapon();
    }

    /**
     * Applies the melee weapon to the player's inventory.
     */
    public void applyMeleeWeapon() {

        if (meleeWeapon != null)
            meleeWeapon.giveItemStack(gameplayPlayer.getPlayer(), 0);
    }

    /**
     * Applies the primary weapon to the player's inventory.
     */
    public void applyPrimaryWeapon() {

        if (primaryWeapon != null)
            primaryWeapon.giveItemStack(gameplayPlayer.getPlayer(), 1);
    }

    /**
     * Applies the secondary weapon to the player's inventory.
     */
    public void applySecondaryWeapon() {

        if (secondaryWeapon != null)
            secondaryWeapon.giveItemStack(gameplayPlayer.getPlayer(), 2);
    }

    /**
     * Applies the tertiary weapon to the player's inventory.
     */
    public void applyTertiaryWeapon() {

        if (tertiaryWeapon != null)
            tertiaryWeapon.giveItemStack(gameplayPlayer.getPlayer(), 3);
    }
    //endregion

    //region Constructors
    public GameplayLoadout(GameplayPlayer gameplayPlayer, LoadoutEntity loadoutEntity) {

        this.gameplayPlayer = gameplayPlayer;
        this.loadoutEntity = loadoutEntity;

        breakdownLoadout();
    }
    //endregion

    //region Private Methods
    private void breakdownLoadout() {

        for (WeaponEntity queryWeapon : PluginCore.getWeapons()) {

            if (queryWeapon.getId().equals(loadoutEntity.getMeleeWeaponId())) {

                meleeWeapon = new GameplayWeapon(queryWeapon);
                meleeWeapon.register();

                continue;
            }

            if (queryWeapon.getId().equals(loadoutEntity.getPrimaryWeaponId())) {

                primaryWeapon = new GameplayWeapon(queryWeapon);
                primaryWeapon.register();

                continue;
            }

            if (queryWeapon.getId().equals(loadoutEntity.getSecondaryWeaponId())) {

                secondaryWeapon = new GameplayWeapon(queryWeapon);
                secondaryWeapon.register();

                continue;
            }

            if (queryWeapon.getId().equals(loadoutEntity.getTertiaryWeaponId())) {

                tertiaryWeapon = new GameplayWeapon(queryWeapon);
                tertiaryWeapon.register();
            }
        }
    }
    //endregion
}
