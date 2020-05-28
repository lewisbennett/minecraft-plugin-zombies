package com.mango.zombies.entities;

import com.google.gson.annotations.Expose;

public class LoadoutEntity {

    //region Fields
    @Expose private String meleeWeaponId;
    @Expose private String perkFiveId;
    @Expose private String perkFourId;
    @Expose private String perkOneId;
    @Expose private String perkThreeId;
    @Expose private String perkTwoId;
    @Expose private String primaryWeaponId;
    @Expose private String secondaryWeaponId;
    @Expose private String tertiaryWeaponId;
    //endregion

    //region Getters/Setters
    /**
     * Gets the melee weapon ID.
     */
    public String getMeleeWeaponId() {
        return meleeWeaponId;
    }

    /**
     * Sets the melee weapon ID.
     */
    public void setMeleeWeaponId(String meleeWeaponId) {
        this.meleeWeaponId = meleeWeaponId;
    }

    /**
     * Gets the fifth perk ID.
     */
    public String getPerkFiveId() {
        return perkFiveId;
    }

    /**
     * Sets the fifth perk ID.
     */
    public void setPerkFiveId(String perkFiveId) {
        this.perkFiveId = perkFiveId;
    }

    /**
     * Gets the fourth perk ID.
     */
    public String getPerkFourId() {
        return perkFourId;
    }

    /**
     * Sets the fourth perk ID.
     */
    public void setPerkFourId(String perkFourId) {
        this.perkFourId = perkFourId;
    }

    /**
     * Gets the first perk ID.
     */
    public String getPerkOneId() {
        return perkOneId;
    }

    /**
     * Sets the first perk ID.
     */
    public void setPerkOneId(String perkOneId) {
        this.perkOneId = perkOneId;
    }

    /**
     * Gets the third perk ID.
     */
    public String getPerkThreeId() {
        return perkThreeId;
    }

    /**
     * Sets the third perk ID.
     */
    public void setPerkThreeId(String perkThreeId) {
        this.perkThreeId = perkThreeId;
    }

    /**
     * Gets the second perk ID.
     */
    public String getPerkTwoId() {
        return perkTwoId;
    }

    /**
     * Sets the second perk ID.
     */
    public void setPerkTwoId(String perkTwoId) {
        this.perkTwoId = perkTwoId;
    }

    /**
     * Gets the primary weapon ID.
     */
    public String getPrimaryWeaponId() {
        return primaryWeaponId;
    }

    /**
     * Sets the primary weapon ID.
     */
    public void setPrimaryWeaponId(String primaryWeaponId) {
        this.primaryWeaponId = primaryWeaponId;
    }

    /**
     * Gets the secondary weapon ID.
     */
    public String getSecondaryWeaponId() {
        return secondaryWeaponId;
    }

    /**
     * Sets the secondary weapon ID.
     */
    public void setSecondaryWeaponId(String secondaryWeaponId) {
        this.secondaryWeaponId = secondaryWeaponId;
    }

    /**
     * Gets the tertiary weapon ID.
     */
    public String getTertiaryWeaponId() {
        return tertiaryWeaponId;
    }

    /**
     * Sets the tertiary weapon ID.
     */
    public void setTertiaryWeaponId(String tertiaryWeaponId) {
        this.tertiaryWeaponId = tertiaryWeaponId;
    }
    //endregion

    //region Public Methods
    /**
     * Gets whether the values of this loadout have been configured to a minimum spec.
     */
    public boolean isEmpty() {

        boolean isMeleeEmpty = meleeWeaponId == null || meleeWeaponId.isEmpty();
        boolean isPrimaryWeaponEmpty = primaryWeaponId == null || primaryWeaponId.isEmpty();
        boolean isSecondaryWeaponEmpty = secondaryWeaponId == null || secondaryWeaponId.isEmpty();
        boolean isTertiaryWeaponEmpty = tertiaryWeaponId == null || tertiaryWeaponId.isEmpty();

        return isMeleeEmpty && isPrimaryWeaponEmpty && isSecondaryWeaponEmpty && isTertiaryWeaponEmpty;
    }
    //endregion
}
