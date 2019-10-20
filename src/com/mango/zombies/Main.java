package com.mango.zombies;

import com.mango.zombies.commands.*;
import com.mango.zombies.listeners.BlockEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimerTask;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginCore.setDescriptionFile(getDescription());
        PluginCore.setupFolders(getDataFolder());

        PluginCore.importConfig();
        PluginCore.importMaps();
        PluginCore.importPerks();
        PluginCore.importWeaponClasses();
        PluginCore.importWeapons();

        registerCommands();
        registerEvents();

        int delay = PluginCore.getConfig().getAutoSaveTimerInterval() * 60 * 1000;

        PluginCore.getAutoSaveTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PluginCore.autoSave();
            }
        }, delay, delay);
    }

    @Override
    public void onDisable() {

        PluginCore.saveConfig();
        PluginCore.saveMaps();
        PluginCore.savePerks();
        PluginCore.saveWeaponClasses();
        PluginCore.saveWeapons();
    }

    /**
     * Registers all plugin commands with corresponding executors.
     */
    public void registerCommands() {

        this.getCommand("info").setExecutor(new InfoCommandExecutor());
        this.getCommand("mapinfo").setExecutor(new MapInfoCommandExecutor());
        this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
        this.getCommand("createweaponclass").setExecutor(new CreateWeaponClassCommandExecutor());
        this.getCommand("createweapon").setExecutor(new CreateWeaponCommandExecutor());
        this.getCommand("createperk").setExecutor(new CreatePerkCommandExecutor());
        this.getCommand("getweapon").setExecutor(new GetWeaponCommandExecutor());
    }

    /**
     * Registers all plugin event handlers.
     */
    public void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
    }
}