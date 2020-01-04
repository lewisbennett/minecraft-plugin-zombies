package com.mango.zombies;

import com.mango.zombies.entities.*;
import com.mango.zombies.schema.FileName;
import com.mango.zombies.services.FilingService;
import net.minecraft.server.v1_14_R1.Tuple;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class PluginCore {

    //region Fields
    private static Timer autoSaveTimer = new Timer();
    private static ConfigEntity config;
    private static File dataFolder;
    private static File debugFolder;
    private static List<EnemyEntity> enemies = new ArrayList<EnemyEntity>();
    private static File enemiesFolder;
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
    //endregion

    //region Getters/Setters
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
     * Gets the debug folder.
     */
    public static File getDebugFolder() {
        return debugFolder;
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
     * Gets the enemies.
     */
    public static List<EnemyEntity> getEnemies() {
        return enemies;
    }

    /**
     * Gets the enemies folder.
     */
    public static File getEnemiesFolder() {
        return enemiesFolder;
    }

    /**
     * Sets the enemies folder.
     */
    public static void setEnemiesFolder(File enemiesFolder) {
        PluginCore.enemiesFolder = enemiesFolder;
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
    //endregion

    //region Public Static Methods
    /**
     * Saves everything.
     */
    public static void autoSave() {

        log("Auto save started.");

        saveConfig();
        saveMaps();
        savePerks();
        saveEnemies();
        saveWeaponClasses();
        saveWeapons();

        log("Auto save completed.");
    }

    /**
     * Enables all available maps.
     */
    public static void enableMaps() {

        for (MapEntity map : maps) {

            if (!map.isEnabled())
                continue;

            Tuple<Boolean, String> enabled = map.enableMap();

            if (!enabled.a())
                log(enabled.b());
        }
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
     * Imports all available enemies.
     */
    public static void importEnemies() {

        log("Importing enemies...");

        for (File file : enemiesFolder.listFiles())
            enemies.add(FilingService.readContents(file.toString(), EnemyEntity.SERIALIZER, EnemyEntity.class));

        log(String.format(enemies.size() == 1 ? "%d enemy imported." : "%d enemies imported.", enemies.size()));
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
     * Formats then logs a message to the console.
     */
    public static void log(String message) {
        System.out.println("[Zombies] " + message);
    }

    /**
     * Saves the config file.
     */
    public static void saveConfig() {

        PluginCore.log("Saving config file...");

        boolean result = FilingService.writeFile(dataFolder, "config", config, ConfigEntity.SERIALIZER);

        PluginCore.log(result ? "Config file saved." : "Failed to save config file.");
    }

    /**
     * Saves all available enemies.
     */
    public static void saveEnemies() {

        PluginCore.log("Saving enemies...");

        int successes = 0;
        int failures = 0;

        for (EnemyEntity enemy : enemies) {

            boolean result = FilingService.writeFile(enemiesFolder, enemy.getId(), enemy, EnemyEntity.SERIALIZER);

            if (result)
                successes++;
            else
                failures++;
        }

        String successMessage = String.format(successes == 1 ? "Saved %d enemy." : "Saved %d enemies.", successes);
        String failMessage = String.format(failures == 1 ? "Failed to save %d enemy." : "Failed to save %d enemies.", failures);

        PluginCore.log(successMessage + (failures > 0 ? " " + failMessage : ""));
    }

    /**
     * Saves all available maps.
     */
    public static void saveMaps() {

        PluginCore.log("Saving maps...");

        int successes = 0;
        int failures = 0;

        for (MapEntity map : maps) {

            boolean result = FilingService.writeFile(mapsFolder, map.getId(), map, MapEntity.SERIALIZER);

            if (result)
                successes++;
            else
                failures++;
        }

        String successMessage = String.format(successes == 1 ? "Saved %d map." : "Saved %d maps.", successes);
        String failMessage = String.format(failures == 1 ? "Failed to save %d map." : "Failed to save %d maps.", failures);

        PluginCore.log(successMessage + (failures > 0 ? " " + failMessage : ""));
    }

    /**
     * Saves all available perks.
     */
    public static void savePerks() {

        PluginCore.log("Saving perks...");

        int successes = 0;
        int failures = 0;

        for (PerkEntity perk : perks) {

            boolean result = FilingService.writeFile(perksFolder, perk.getId(), perk, PerkEntity.SERIALIZER);

            if (result)
                successes++;
            else
                failures++;
        }

        String successMessage = String.format(successes == 1 ? "Saved %d perk." : "Saved %d perks.", successes);
        String failMessage = String.format(failures == 1 ? "Failed to save %d perk." : "Failed to save %d perks.", failures);

        PluginCore.log(successMessage + (failures > 0 ? " " + failMessage : ""));
    }

    /**
     * Saves all available weapon classes.
     */
    public static void saveWeaponClasses() {

        PluginCore.log("Saving weapon classes...");

        int successes = 0;
        int failures = 0;

        for (WeaponClassEntity weaponClass : weaponsClasses) {

            boolean result = FilingService.writeFile(weaponClassesFolder, weaponClass.getId(), weaponClass, WeaponClassEntity.SERIALIZER);

            if (result)
                successes++;
            else
                failures++;
        }

        String successMessage = String.format(successes == 1 ? "Saved %d weapon class." : "Saved %d weapon classes.", successes);
        String failMessage = String.format(failures == 1 ? "Failed to save %d weapon class." : "Failed to save %d weapon classes.", failures);

        PluginCore.log(successMessage + (failures > 0 ? " " + failMessage : ""));
    }

    /**
     * Saves all available weapons.
     */
    public static void saveWeapons() {

        PluginCore.log("Saving weapons...");

        int successes = 0;
        int failures = 0;

        for (WeaponEntity weapon : weapons) {

            boolean result = FilingService.writeFile(weaponsFolder, weapon.getId(), weapon, WeaponEntity.SERIALIZER);

            if (result)
                successes++;
            else
                failures++;
        }

        String successMessage = String.format(successes == 1 ? "Saved %d weapon." : "Saved %d weapons.", successes);
        String failMessage = String.format(failures == 1 ? "Failed to save %d weapon." : "Failed to save %d weapons.", failures);

        PluginCore.log(successMessage + (failures > 0 ? " " + failMessage : ""));
    }

    /**
     * Creates the folders needed for the plugin.
     */
    public static void setupFolders(File pluginRoot) {

        dataFolder = pluginRoot;
        debugFolder = new File(dataFolder + "/Debug/");
        enemiesFolder = new File(dataFolder + "/Enemies/");
        mapsFolder = new File(dataFolder + "/Maps/");
        importFolder = new File(dataFolder + "/Import/");
        weaponsFolder = new File(dataFolder + "/Weapons/");
        weaponClassesFolder = new File(dataFolder + "/Weapon Classes/");
        perksFolder = new File(dataFolder + "/Perks/");

        createDirectory(dataFolder);
        createDirectory(debugFolder);
        createDirectory(enemiesFolder);
        createDirectory(importFolder);
        createDirectory(mapsFolder);
        createDirectory(perksFolder);
        createDirectory(weaponsFolder);
        createDirectory(weaponClassesFolder);
    }
    //endregion

    //region Private Methods
    private static void createDirectory(File file) {

        if (!file.exists() && !file.mkdir())
            System.out.println("[Zombies] Could not create plugin directory: " + file.toString());
    }
    //endregion
}