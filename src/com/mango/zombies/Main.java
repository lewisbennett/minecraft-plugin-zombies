package com.mango.zombies;

import com.mango.zombies.commands.*;
import com.mango.zombies.listeners.*;
import com.mango.zombies.services.StockFilingService;
import com.mango.zombies.services.StockGameplayService;
import com.mango.zombies.services.StockMessagingService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimerTask;

public class Main extends JavaPlugin {

    //region Static Fields
    private static Main instance;
    //endregion

    //region Static Getters/Setters
    /**
     * Gets the plugin instance.
     */
    public static Main getInstance() {
        return instance;
    }
    //endregion

    //region Lifecycle
    @Override
    public void onEnable() {

        instance = this;

        registerServices();

        registerCommands();

        registerEvents();

        PluginCore.getFilingService().setupFolders(getDataFolder());

        PluginCore.getFilingService().importEverything();

        long delay = Time.fromMinutes(PluginCore.getConfig().getAutoSaveTimerInterval()).totalMilliseconds();

        PluginCore.getFilingService().getAutoSaveTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PluginCore.autoSave();
            }
        }, delay, delay);
    }

    @Override
    public void onDisable() {
        PluginCore.getFilingService().saveEverything();
    }
    //endregion

    //region Public Methods
    /**
     * Registers all plugin commands with corresponding executors.
     */
    public void registerCommands() {

        this.getCommand("info").setExecutor(new InfoCommandExecutor());
        this.getCommand("mapinfo").setExecutor(new MapInfoCommandExecutor());
        this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
        this.getCommand("createenemy").setExecutor(new CreateEnemyCommandExecutor());
        this.getCommand("createweapon").setExecutor(new CreateWeaponCommandExecutor());
        this.getCommand("createperk").setExecutor(new CreatePerkCommandExecutor());
        this.getCommand("getweapon").setExecutor(new GetWeaponCommandExecutor());
        this.getCommand("getpositiontool").setExecutor(new GetPositionToolCommandExecutor());
        this.getCommand("getspawningtool").setExecutor(new GetSpawningToolCommandExecutor());
        this.getCommand("deletemap").setExecutor(new DeleteMapCommandExecutor());
    }

    /**
     * Registers all plugin event handlers.
     */
    public void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryPickupItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEggThrowListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupArrowListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignChangedListener(), this);
    }

    /**
     * Registers all plugin services.
     */
    public void registerServices() {

        PluginCore.setFilingService(new StockFilingService());
        PluginCore.setGameplayService(new StockGameplayService());
        PluginCore.setMessagingService(new StockMessagingService());
    }
    //endregion
}