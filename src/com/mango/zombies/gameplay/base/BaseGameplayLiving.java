package com.mango.zombies.gameplay.base;

import com.mango.zombies.Main;
import com.mango.zombies.Time;
import com.mango.zombies.gameplay.GameplaySession;
import com.mango.zombies.schema.DamagerType;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public abstract class BaseGameplayLiving extends BaseGameplayRegisterable {

    //region Fields
    private boolean hasBeenDamaged;
    private boolean hasBeenDowned;
    private boolean hasBeenNuked;

    private final GameplaySession gameplaySession;

    private int bleedOutTaskReference;
    private int currentHealth;
    private int initialHealth;

    private LivingEntity livingEntity;
    //endregion

    //region Getters/Setters
    /**
     * Gets whether this entity can bleed out
     */
    public abstract boolean canBleedOut();

    /**
     * Gets whether this entity can heal after being damaged.
     */
    public abstract boolean canHeal();

    /**
     * Gets the chance the entity has of bleeding out once the threshold has been met.
     */
    public abstract int getBleedOutChance();

    /**
     * Gets the health requirement before the entity can begin to bleed out.
     */
    public abstract int getBleedOutThreshold();

    /**
     * Gets the session that this entity is a part of, if any.
     */
    public GameplaySession getGameplaySession() {
        return gameplaySession;
    }

    /**
     * Gets whether this entity has been damaged.
     */
    public boolean hasBeenDamaged() {
        return hasBeenDamaged;
    }

    /**
     * Gets whether this entity has been downed.
     */
    public boolean hasBeenDowned() {
        return hasBeenDowned;
    }

    /**
     * Gets whether this entity has been nuked.
     */
    public boolean hasBeenNuked() {
        return hasBeenNuked;
    }

    /**
     * Gets the entity's initial health.
     */
    public int getInitialHealth() {
        return initialHealth;
    }

    /**
     * Sets the entity's initial health.
     */
    public void setInitialHealth(int initialHealth) {

        this.initialHealth = initialHealth;

        if (!hasBeenDamaged)
            currentHealth = this.initialHealth;
    }

    /**
     * Gets the living entity, if any.
     */
    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    /**
     * Sets the living entity.
     */
    public void setLivingEntity(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    /**
     * Gets the duration that the entity should be nuked for, in seconds.
     */
    public abstract int getNukeDuration();

    /**
     * Gets the UUID of this gameplay registerable.
     */
    public UUID getUUID() {
        return livingEntity.getUniqueId();
    }
    //endregion

    //region Public Methods
    /**
     * Begins the entity bleeding out, if possible and if ready.
     */
    public void bleedOut() {

        if (!canBleedOut() || hasBeenNuked)
            return;

        if (currentHealth < getBleedOutThreshold() && Math.random() * 100 <= getBleedOutChance()) {

            Main instance = Main.getInstance();

            long time = Time.fromSeconds(1).totalTicks();

            bleedOutTaskReference = instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, this::bleedOut_runnable, time, time);
        }
    }

    /**
     * Damages the entity.
     * @param damagerEntity The entity that dealt the damage.
     * @param damage The damage dealt.
     * @param damagerType The type of damage that was dealt.
     */
    public void damage(BaseGameplayLiving damagerEntity, int damage, DamagerType damagerType) {

        if (livingEntity == null)
            return;

        hasBeenDamaged = true;

        currentHealth -= damage;

        spawnBloodEffect(damage / 10);

        if (currentHealth < 0) {
            down(damagerEntity, damagerType);
            return;
        }

        bleedOut();

        if (getGameplaySession() != null)
            getGameplaySession().getGamemode().onEntityDamaged(this, damagerEntity);
    }

    /**
     * Downs the entity.
     * @param downerEntity The entity that downed this entity.
     * @param damagerType The type of damage that was dealt.
     */
    public void down(BaseGameplayLiving downerEntity, DamagerType damagerType) {

        hasBeenDowned = true;

        if (gameplaySession != null)
            gameplaySession.getGamemode().onEntityDowned(this, downerEntity, damagerType);
    }

    /**
     * Immobilises the entity.
     */
    public abstract void immobilise();

    /**
     * Mobilises the entity.
     */
    public abstract void mobilise();

    /**
     * Nukes this entity.
     */
    public void nuke() {

        if (livingEntity == null)
            return;

        hasBeenNuked = true;

        immobilise();

        int nukeDuration = getNukeDuration();

        livingEntity.setFireTicks((int)Time.fromSeconds(nukeDuration).totalTicks());

        Main instance = Main.getInstance();

        instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, this::nuke_runnable, nukeDuration);
    }
    //endregion

    //region Runnables
    private void bleedOut_runnable() {

        if (livingEntity == null || currentHealth < 1 || hasBeenNuked)
            return;

        spawnBloodEffect(20);

        currentHealth -= (initialHealth * 0.05);

        if (currentHealth > 0)
            livingEntity.damage(0);

        else {

            livingEntity.damage(100);
            Main.getInstance().getServer().getScheduler().cancelTask(bleedOutTaskReference);
        }
    }

    private void nuke_runnable() {
        down(null, DamagerType.NUKE);
    }
    //endregion

    //region Constructors
    protected BaseGameplayLiving(GameplaySession gameplaySession) {
        this.gameplaySession = gameplaySession;
    }
    //endregion

    //region Private Methods
    private void spawnBloodEffect(int count) {
        livingEntity.getWorld().spawnParticle(Particle.BLOCK_CRACK, Math.random() > 0.5 ? livingEntity.getEyeLocation() : livingEntity.getLocation(), count, Material.RED_WOOL.createBlockData());
    }
    //endregion
}
