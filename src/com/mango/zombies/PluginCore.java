package com.mango.zombies;

import com.mango.zombies.entities.*;
import com.mango.zombies.services.FilingService;
import com.mango.zombies.schema.FileName;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class PluginCore {

    private static Timer autoSaveTimer = new Timer();
    private static ConfigEntity config;
    private static File dataFolder;
    private static PluginDescriptionFile descriptionFile;
    private static File importFolder;
    private static List<MapEntity> maps = new ArrayList<MapEntity>();
    private static File mapsFolder;
    private static List<PerkEntity> perks = new ArrayList<PerkEntity>();
    private static File perksFolder;
    private static List<WeaponClassEntity> weaponsClasses = new ArrayList<WeaponClassEntity>();
    private static File weaponClassesFolder;
    private static List<WeaponEntity> weapons = new ArrayList<WeaponEntity>();
    private static File weaponsFolder;

    /**
     * Gets the auto save timer.
     */
    public static Timer getAutoSaveTimer() {
        return autoSaveTimer;
    }

    /**
     * Gets the plugin's configuration.
     */
    public static ConfigEntity getConfig() {
        return config;
    }

    /**
     * Sets the plugin's configuration.
     */
    public static void setConfig(ConfigEntity config) {
        PluginCore.config = config;
    }

    /**
     * Gets the data folder.
     */
    public static File getDataFolder() {
        return dataFolder;
    }

    /**
     * Gets the plugin's description file.
     */
    public static PluginDescriptionFile getDescriptionFile() {
        return descriptionFile;
    }

    /**
     * Sets the plugin's description file.
     */
    public static void setDescriptionFile(PluginDescriptionFile descriptionFile) {
        PluginCore.descriptionFile = descriptionFile;
    }

    /**
     * Gets the import folder.
     */
    public static File getImportFolder() {
        return importFolder;
    }

    /**
     * Gets the perks.
     */
    public static List<PerkEntity> getPerks() {
        return perks;
    }

    /**
     * Gets the perks folder.
     */
    public static File getPerksFolder() {
        return perksFolder;
    }

    /**
     * Gets the maps.
     */
    public static List<MapEntity> getMaps() {
        return maps;
    }

    /**
     * Gets the maps folder.
     */
    public static File getMapsFolder() {
        return mapsFolder;
    }

    /**
     * Gets the weapon classes.
     */
    public static List<WeaponClassEntity> getWeaponsClasses() {
        return weaponsClasses;
    }

    /**
     * Gets the weapon classes folder.
     */
    public static File getWeaponClassesFolder() {
        return weaponClassesFolder;
    }

    /**
     * Gets the weapons.
     */
    public static List<WeaponEntity> getWeapons() {
        return weapons;
    }

    /**
     * Gets the weapons folder.
     */
    public static File getWeaponsFolder() {
        return weaponsFolder;
    }

    /**
     * Formats then logs a message to the console.
     */
    public static void log(String message) {
        System.out.println("[Zombies] " + message);
    }

    /**
     * Creates the folders needed for the plugin.
     */
    public static void setupFolders(File pluginRoot) {

        dataFolder = pluginRoot;
        mapsFolder = new File(dataFolder + "/Maps/");
        importFolder = new File(dataFolder + "/Import/");
        weaponsFolder = new File(dataFolder + "/Weapons/");
        weaponClassesFolder = new File(dataFolder + "/Weapon Classes/");
        perksFolder = new File(dataFolder + "/Perks/");

        createDirectory(dataFolder);
        createDirectory(importFolder);
        createDirectory(mapsFolder);
        createDirectory(perksFolder);
        createDirectory(weaponsFolder);
        createDirectory(weaponClassesFolder);
    }

    /**
     * Imports the config file.
     */
    public static void importConfig() {

        log("Importing config file...");

        for (File file : dataFolder.listFiles()) {

            if (!file.getName().equals(FileName.CONFIG_FILE))
                continue;

            setConfig(FilingService.readContents(file.toString(), ConfigEntity.SERIALIZER, ConfigEntity.class));

            break;
        }

        if (config == null)
            setConfig(new ConfigEntity());

        if (config.getWorldName() == null || config.getWorldName().isEmpty())
            config.setWorldName(Bukkit.getServer().getWorlds().get(0).getName());

        log("Config file imported.");
    }

    /**
     * Imports all available map files.
     */
    public static void importMaps() {

        log("Importing maps...");

        for (File file : mapsFolder.listFiles())
            maps.add(FilingService.readContents(file.toString(), MapEntity.SERIALIZER, MapEntity.class));

        log(String.format(maps.size() == 1 ? "%d map imported." : "%d maps imported.", maps.size()));
    }

    /**
     * Imports all available perk files.
     */
    public static void importPerks() {

        log("Importing perks...");

        for (File file : perksFolder.listFiles())
            perks.add(FilingService.readContents(file.toString(), PerkEntity.SERIALIZER, PerkEntity.class));

        log(String.format(perks.size() == 1 ? "%d perk imported." : "%d perks imported.", perks.size()));
    }

    /**
     * Imports all available weapon class files.
     */
    public static void importWeaponClasses() {

        log("Importing weapon classes...");

        for (File file : weaponClassesFolder.listFiles())
            weaponsClasses.add(FilingService.readContents(file.toString(), WeaponClassEntity.SERIALIZER, WeaponClassEntity.class));

        log(String.format(weaponsClasses.size() == 1 ? "%d weapon class imported." : "%d weapon classes imported.", weaponsClasses.size()));
    }

    /**
     * Imports all available weapon files.
     */
    public static void importWeapons() {

        log("Importing weapons...");

        for (File file : weaponsFolder.listFiles())
            weapons.add(FilingService.readContents(file.toString(), WeaponEntity.SERIALIZER, WeaponEntity.class));

        log(String.format(weapons.size() == 1 ? "%d weapon imported." : "%d weapons imported.", weapons.size()));
    }

    /**
     * Saves everything.
     */
    public static void autoSave() {

        log("Auto save started.");

        saveConfig();
        saveMaps();
        savePerks();
        saveWeaponClasses();
        saveWeapons();

        log("Auto save completed.");
    }

    /**
     * Saves the config file.
     */
    public static void saveConfig() {

        PluginCore.log("Saving config file...");

        FilingService.writeFile(dataFolder, "config", config, ConfigEntity.SERIALIZER);

        PluginCore.log("Config file saved.");
    }

    /**
     * Saves all available maps.
     */
    public static void saveMaps() {

        PluginCore.log("Saving maps...");

        for (MapEntity map : maps)
            FilingService.writeFile(mapsFolder, map.getId(), map, MapEntity.SERIALIZER);

        PluginCore.log(String.format(maps.size() == 1 ? "%d map saved." : "%d maps saved.", maps.size()));
    }

    /**
     * Saves all available perks.
     */
    public static void savePerks() {

        PluginCore.log("Saving perks...");

        for (PerkEntity perk : perks)
            FilingService.writeFile(perksFolder, perk.getId(), perk, PerkEntity.SERIALIZER);

        PluginCore.log(String.format(perks.size() == 1 ? "%d perk saved." : "%d perks saved.", perks.size()));
    }

    /**
     * Saves all available weapon classes.
     */
    public static void saveWeaponClasses() {

        PluginCore.log("Saving weapon classes...");

        for (WeaponClassEntity weaponClass : weaponsClasses)
            FilingService.writeFile(weaponClassesFolder, weaponClass.getId(), weaponClass, WeaponClassEntity.SERIALIZER);

        PluginCore.log(String.format(weaponsClasses.size() == 1 ? "%d weapon class saved." : "%d weapon classes saved.", weaponsClasses.size()));
    }

    /**
     * Saves all available weapons.
     */
    public static void saveWeapons() {

        PluginCore.log("Saving weapons...");

        for (WeaponEntity weapon : weapons)
            FilingService.writeFile(weaponsFolder, weapon.getId(), weapon, WeaponEntity.SERIALIZER);

        PluginCore.log(String.format(weapons.size() == 1 ? "%d weapon saved." : "%d weapons saved.", weapons.size()));
    }

    private static void createDirectory(File file) {
        if (!file.exists() && !file.mkdir())
            System.out.println("[Zombies] Could not create plugin directory: " + file.toString());
    }
}