package com.mango.zombies;

import com.mango.zombies.commands.*;
import com.mango.zombies.listeners.ProjectileHitListener;
import com.mango.zombies.listeners.SignChangedListener;
import com.mango.zombies.listeners.WeaponInteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimerTask;

public class Main extends JavaPlugin {

    //region Static Fields
    private static Main instance;

    private static InfoCommandExecutor infoCommandExecutor = new InfoCommandExecutor();
    private static MapInfoCommandExecutor mapInfoCommandExecutor = new MapInfoCommandExecutor();
    private static CreateMapCommandExecutor createMapCommandExecutor = new CreateMapCommandExecutor();
    private static CreateWeaponClassCommandExecutor createWeaponClassCommandExecutor = new CreateWeaponClassCommandExecutor();
    private static CreateWeaponCommandExecutor createWeaponCommandExecutor = new CreateWeaponCommandExecutor();
    private static CreatePerkCommandExecutor createPerkCommandExecutor = new CreatePerkCommandExecutor();
    private static GetWeaponCommandExecutor getWeaponCommandExecutor = new GetWeaponCommandExecutor();
    private static GetPositionToolCommandExecutor getPositionToolCommandExecutor = new GetPositionToolCommandExecutor();
    private static DeleteMapCommandExecutor deleteMapCommandExecutor = new DeleteMapCommandExecutor();
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

        PluginCore.setDescriptionFile(getDescription());
        PluginCore.setupFolders(getDataFolder());

        PluginCore.importConfig();
        PluginCore.importMaps();
        PluginCore.importPerks();
        PluginCore.importWeaponClasses();
        PluginCore.importWeapons();

        PluginCore.enableMaps();

        registerCommands();
        registerEvents();

        int delay = Time.fromMinutes(PluginCore.getConfig().getAutoSaveTimerInterval()).totalMilliseconds();

        PluginCore.getAutoSaveTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PluginCore.autoSave();
            }
        }, delay, delay);

        instance = this;
    }

    @Override
    public void onDisable() {

        PluginCore.saveConfig();
        PluginCore.saveMaps();
        PluginCore.savePerks();
        PluginCore.saveWeaponClasses();
        PluginCore.saveWeapons();
    }
    //endregion

    //region Public Methods
    /**
     * Registers all plugin commands with corresponding executors.
     */
    public void registerCommands() {

        this.getCommand("info").setExecutor(infoCommandExecutor);
        this.getCommand("mapinfo").setExecutor(mapInfoCommandExecutor);
        this.getCommand("createmap").setExecutor(createMapCommandExecutor);
        this.getCommand("createweaponclass").setExecutor(createWeaponClassCommandExecutor);
        this.getCommand("createweapon").setExecutor(createWeaponCommandExecutor);
        this.getCommand("createperk").setExecutor(createPerkCommandExecutor);
        this.getCommand("getweapon").setExecutor(getWeaponCommandExecutor);
        this.getCommand("getpositiontool").setExecutor(getPositionToolCommandExecutor);
        this.getCommand("deletemap").setExecutor(deleteMapCommandExecutor);
    }

    /**
     * Registers all plugin event handlers.
     */
    public void registerEvents() {

        Bukkit.getPluginManager().registerEvents(getPositionToolCommandExecutor, this);

        Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignChangedListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeaponInteractListener(), this);
    }
    //endregion
}