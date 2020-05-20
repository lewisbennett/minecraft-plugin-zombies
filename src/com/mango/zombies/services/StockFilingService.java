package com.mango.zombies.services;

import com.google.gson.GsonBuilder;
import com.mango.zombies.Log;
import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.*;
import com.mango.zombies.schema.FileName;
import com.mango.zombies.services.base.FilingService;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class StockFilingService implements FilingService {

	//region Fields
	private File rootFolder;
	private File debugFolder;
	private File enemiesFolder;
	private File importFolder;
	private File mapsFolder;
	private File perksFolder;
	private File weaponsFolder;

	private Timer autoSaveTimer = new Timer();
	//endregion

	//region Getters/Setters
	/**
	 * Gets the auto save timer.
	 */
	public Timer getAutoSaveTimer() {
		return autoSaveTimer;
	}

	/**
	 * Gets the root folder.
	 */
	public File getRootFolder() {
		return rootFolder;
	}

	/**
	 * Gets the debug folder.
	 */
	public File getDebugFolder() {
		return debugFolder;
	}

	/**
	 * Gets the enemies folder.
	 */
	public File getEnemiesFolder() {
		return enemiesFolder;
	}

	/**
	 * Gets the import folder.
	 */
	public File getImportFolder() {
		return importFolder;
	}

	/**
	 * Gets the perks folder.
	 */
	public File getPerksFolder() {
		return perksFolder;
	}

	/**
	 * Gets the maps folder.
	 */
	public File getMapsFolder() {
		return mapsFolder;
	}

	/**
	 * Gets the weapons folder.
	 */
	public File getWeaponsFolder() {
		return weaponsFolder;
	}
	//endregion

	//region Public Methods
	/**
	 * Deletes a file from a directory.
	 * @param directory The directory where the file is located.
	 * @param name The name of the file to delete, not including extension.
	 */
	public boolean deleteFile(File directory, String name) {
		return new File(directory + "/" + name + ".json").delete();
	}

	/**
	 * Imports all available plugin data.
	 */
	public void importEverything() {

		importConfigs();

		importEnemies();
		importMaps();
		importPerks();
		importWeapons();
	}

	/**
	 * Reads the contents of a file as deserializes it to an object.
	 * @param filePath The path to the file to read.
	 * @param entity The class of the object.
	 */
	public <TReq> TReq readContents(String filePath, Class<TReq> entity) {

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

			StringBuilder contentBuilder = new StringBuilder();
			String currentLine;

			while ((currentLine = reader.readLine()) != null)
				contentBuilder.append(currentLine).append("\n");

			return new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.create()
					.fromJson(contentBuilder.toString(), entity);

		} catch (Exception e) {

			String error = "Failed to open file: " + filePath;

			if (PluginCore.getConfig().isDebuggingEnabled()) {

				String stackTraceFileName = writeStackTraceFile(error, ExceptionUtils.getStackTrace(e));

				if (stackTraceFileName != null)
					error += ". View the stacktrace: " + stackTraceFileName;
			}

			Log.information(error);
		}

		return null;
	}

	/**
	 * Saves all plugin data.
	 */
	public void saveEverything() {

		saveConfig();
		saveEnemyConfig();
		saveMapConfig();
		savePerkConfig();
		saveWeaponConfig();

		saveEnemies();
		saveMaps();
		savePerks();
		saveWeapons();
	}

	/**
	 * Sets up the folders using the root directory.
	 * @param rootFolder The plugin's root directory.
	 */
	public void setupFolders(File rootFolder) {

		this.rootFolder = rootFolder;

		debugFolder = new File(this.rootFolder + "/Debug/");
		enemiesFolder = new File(this.rootFolder + "/Enemies/");
		mapsFolder = new File(this.rootFolder + "/Maps/");
		importFolder = new File(this.rootFolder + "/Import/");
		weaponsFolder = new File(this.rootFolder + "/Weapons/");
		perksFolder = new File(this.rootFolder + "/Perks/");

		createDirectory(this.rootFolder);
		createDirectory(debugFolder);
		createDirectory(enemiesFolder);
		createDirectory(importFolder);
		createDirectory(mapsFolder);
		createDirectory(perksFolder);
		createDirectory(weaponsFolder);
	}

	/**
	 * Writes a file to a directory in JSON format.
	 * @param directory The directory for where the file should be created.
	 * @param name The name of the file, not including extension.
	 * @param contents The contents of the file to be written.
	 */
	public boolean writeFile(File directory, String name, Object contents) {

		String filePath = directory + "/" + name + ".json";

		try (FileWriter writer = new FileWriter(filePath)) {

			new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.setPrettyPrinting()
					.create()
					.toJson(contents, writer);

			return true;

		} catch (Exception e) {

			String error = "Failed to write file: " + filePath;

			if (PluginCore.getConfig().isDebuggingEnabled()) {

				String stackTraceFileName = writeStackTraceFile(error, ExceptionUtils.getStackTrace(e));

				if (stackTraceFileName != null)
					error += ". View the stacktrace: " + stackTraceFileName;
			}

			Log.information(error);

			return false;
		}
	}

	/**
	 * Writes a stack trace file.
	 * @param error The error message.
	 * @param stackTrace The error stack trace.
	 */
	public String writeStackTraceFile(String error, String stackTrace) {

		String stackTraceFileName = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date(System.currentTimeMillis())) + ".txt";

		try (PrintWriter writer = new PrintWriter(debugFolder + "/" + stackTraceFileName, "UTF-8")) {

			writer.println(error);
			writer.println("");
			writer.println(stackTrace);

			writer.close();

			return stackTraceFileName;

		} catch (Exception e) {
			return null;
		}
	}
	//endregion

	//region Private Methods
	private void createDirectory(File file) {

		if (!file.exists() && !file.mkdir())
			Log.information("Could not create plugin directory: " + file.toString());
	}

	private void importConfigs() {

		Log.information("Importing configuration files...");

		for (File file : rootFolder.listFiles()) {

			switch (file.getName()) {

				case FileName.CONFIG_FILE + ".json":
					PluginCore.setConfig(readContents(file.toString(), ConfigEntity.class));
					continue;

				case FileName.ENEMY_CONFIG_FILE + ".json":
					PluginCore.setEnemyConfig(readContents(file.toString(), EnemyConfigEntity.class));
					continue;

				case FileName.MAP_CONFIG_FILE + ".json":
					PluginCore.setMapConfig(readContents(file.toString(), MapConfigEntity.class));
					continue;

				case FileName.PERK_CONFIG_FILE + ".json":
					PluginCore.setPerkConfig(readContents(file.toString(), PerkConfigEntity.class));
					continue;

				case FileName.WEAPON_CONFIG_FILE + ".json":
					PluginCore.setWeaponConfig(readContents(file.toString(), WeaponConfigEntity.class));
			}
		}

		if (PluginCore.getConfig() == null)
			PluginCore.setConfig(new ConfigEntity());

		if (PluginCore.getEnemyConfig() == null)
			PluginCore.setEnemyConfig(new EnemyConfigEntity());

		if (PluginCore.getMapConfig() == null)
			PluginCore.setMapConfig(new MapConfigEntity());

		if (PluginCore.getPerkConfig() == null)
			PluginCore.setPerkConfig(new PerkConfigEntity());

		if (PluginCore.getWeaponConfig() == null)
			PluginCore.setWeaponConfig(new WeaponConfigEntity());

		Log.information("Configuration files imported.");
	}

	private void importEnemies() {

		Log.information("Importing enemies...");

		for (File file : enemiesFolder.listFiles())
			PluginCore.addEnemy(readContents(file.toString(), EnemyEntity.class));

		int enemyCount = PluginCore.getEnemies().size();

		Log.information(String.format(enemyCount == 1 ? "%d enemy imported." : "%d enemies imported.", enemyCount));
	}

	private void importMaps() {

		Log.information("Importing maps...");

		for (File file : mapsFolder.listFiles())
			PluginCore.addMap(readContents(file.toString(), MapEntity.class));

		int mapCount = PluginCore.getMaps().size();

		Log.information(String.format(mapCount == 1 ? "%d map imported." : "%d maps imported.", mapCount));
	}

	private void importPerks() {

		Log.information("Importing perks...");

		for (File file : perksFolder.listFiles())
			PluginCore.addPerk(readContents(file.toString(), PerkEntity.class));

		int perkCount = PluginCore.getPerks().size();

		Log.information(String.format(perkCount == 1 ? "%d perk imported." : "%d perks imported.", perkCount));
	}

	private void importWeapons() {

		Log.information("Importing weapons...");

		for (File file : weaponsFolder.listFiles())
			PluginCore.addWeapon(readContents(file.toString(), WeaponEntity.class));

		int weaponCount = PluginCore.getWeapons().size();

		Log.information(String.format(weaponCount == 1 ? "%d weapon imported." : "%d weapons imported.", weaponCount));
	}

	private void saveConfig() {

		Log.information("Saving configuration file...");

		boolean result = writeFile(rootFolder, FileName.CONFIG_FILE, PluginCore.getConfig());

		Log.information(result ? "Configuration file saved." : "Failed to save configuration file.");
	}

	private void saveEnemies() {

		Log.information("Saving enemies...");

		int successes = 0;
		int failures = 0;

		for (EnemyEntity enemy : PluginCore.getEnemies()) {

			boolean result = writeFile(enemiesFolder, enemy.getId(), enemy);

			if (result)
				successes++;
			else
				failures++;
		}

		String successMessage = String.format(successes == 1 ? "Saved %d enemy." : "Saved %d enemies.", successes);
		String failMessage = String.format(failures == 1 ? "Failed to save %d enemy." : "Failed to save %d enemies.", failures);

		Log.information(successMessage + (failures > 0 ? " " + failMessage : ""));
	}

	private void saveEnemyConfig() {

		Log.information("Saving enemy configuration file...");

		boolean result = writeFile(rootFolder, FileName.ENEMY_CONFIG_FILE, PluginCore.getEnemyConfig());

		Log.information(result ? "Enemy configuration file saved." : "Failed to save enemy configuration file.");
	}

	private void saveMapConfig() {

		Log.information("Saving map configuration file...");

		boolean result = writeFile(rootFolder, FileName.MAP_CONFIG_FILE, PluginCore.getMapConfig());

		Log.information(result ? "Map configuration file saved." : "Failed to save map configuration file.");
	}

	private void saveMaps() {

		Log.information("Saving maps...");

		int successes = 0;
		int failures = 0;

		for (MapEntity map : PluginCore.getMaps()) {

			boolean result = writeFile(mapsFolder, map.getId(), map);

			if (result)
				successes++;
			else
				failures++;
		}

		String successMessage = String.format(successes == 1 ? "Saved %d map." : "Saved %d maps.", successes);
		String failMessage = String.format(failures == 1 ? "Failed to save %d map." : "Failed to save %d maps.", failures);

		Log.information(successMessage + (failures > 0 ? " " + failMessage : ""));
	}

	private void savePerkConfig() {

		Log.information("Saving perk configuration file...");

		boolean result = writeFile(rootFolder, FileName.PERK_CONFIG_FILE, PluginCore.getPerkConfig());

		Log.information(result ? "Perk configuration file saved." : "Failed to save perk configuration file.");
	}

	private void savePerks() {

		Log.information("Saving perks...");

		int successes = 0;
		int failures = 0;

		for (PerkEntity perk : PluginCore.getPerks()) {

			boolean result = writeFile(perksFolder, perk.getId(), perk);

			if (result)
				successes++;
			else
				failures++;
		}

		String successMessage = String.format(successes == 1 ? "Saved %d perk." : "Saved %d perks.", successes);
		String failMessage = String.format(failures == 1 ? "Failed to save %d perk." : "Failed to save %d perks.", failures);

		Log.information(successMessage + (failures > 0 ? " " + failMessage : ""));
	}

	private void saveWeaponConfig() {

		Log.information("Saving weapon configuration file...");

		boolean result = writeFile(rootFolder, FileName.WEAPON_CONFIG_FILE, PluginCore.getWeaponConfig());

		Log.information(result ? "Weapon configuration file saved." : "Failed to save weapon configuration file.");
	}

	private void saveWeapons() {

		Log.information("Saving weapons...");

		int successes = 0;
		int failures = 0;

		for (WeaponEntity weapon : PluginCore.getWeapons()) {

			boolean result = writeFile(weaponsFolder, weapon.getId(), weapon);

			if (result)
				successes++;
			else
				failures++;
		}

		String successMessage = String.format(successes == 1 ? "Saved %d weapon." : "Saved %d weapons.", successes);
		String failMessage = String.format(failures == 1 ? "Failed to save %d weapon." : "Failed to save %d weapons.", failures);

		Log.information(successMessage + (failures > 0 ? " " + failMessage : ""));
	}
	//endregion
}