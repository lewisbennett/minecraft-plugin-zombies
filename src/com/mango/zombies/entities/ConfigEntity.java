package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.serializers.ConfigEntityJsonSerializer;

import java.util.TimerTask;

public class ConfigEntity {

	//region Constant Values
	public static final String AUTO_GENERATE_DEFAULT_PERKS_JSON_TAG = "auto_generate_default_perks";
	public static final String AUTO_GENERATE_DEFAULT_WEAPON_CLASSES_JSON_TAG = "auto_generate_default_weapon_classes";
	public static final String AUTO_GENERATE_DEFAULT_WEAPONS_JSON_TAG = "auto_generate_default_weapons";
	public static final String AUTO_SAVE_TIMER_INTERVAL_JSON_TAG = "auto_save_timer_interval";
	public static final int DEFAULT_AUTO_SAVE_TIMER_INTERVAL = 10;
	public static final int DEFAULT_DEFAULT_MYSTERY_BOX_COST = 950;
	public static final boolean DEFAULT_ENABLE_DEBUGGING = false;
	public static final String DEFAULT_MYSTERY_BOX_COST_JSON_TAG = "default_mystery_box_cost";
	public static final String ENABLE_DEBUGGING_JSON_TAG = "enable_debugging";
	public static final ConfigEntityJsonSerializer SERIALIZER = new ConfigEntityJsonSerializer();
	public static final String WORLD_NAME_JSON_TAG = "world_name";
	//endregion

	//region Fields
	private boolean autoGenerateDefaultPerks = true, autoGenerateDefaultWeaponClasses = true, autoGenerateDefaultWeapons = true, enableDebugging = DEFAULT_ENABLE_DEBUGGING;
	private int autoSaveTimerInterval = DEFAULT_AUTO_SAVE_TIMER_INTERVAL, defaultMysteryBoxCost = DEFAULT_DEFAULT_MYSTERY_BOX_COST;
	private String worldName;
	//endregion

	//region Getters/Setters
	/**
	 * Gets whether default perks should be generated automatically.
	 */
	public boolean doesAutoGenerateDefaultPerks() {
		return autoGenerateDefaultPerks;
	}

	/**
	 * Sets whether default perks should be generated automatically.
	 */
	public void setAutoGenerateDefaultPerks(boolean autoGenerateDefaultPerks) {
		this.autoGenerateDefaultPerks = autoGenerateDefaultPerks;
	}

	/**
	 * Gets whether default weapon classes should be generated automatically.
	 */
	public boolean doesAutoGenerateDefaultWeaponClasses() {
		return autoGenerateDefaultWeaponClasses;
	}

	/**
	 * Sets whether default weapon classes should be generated automatically.
	 */
	public void setAutoGenerateDefaultWeaponClasses(boolean autoGenerateDefaultWeaponClasses) {
		this.autoGenerateDefaultWeaponClasses = autoGenerateDefaultWeaponClasses;
	}

	/**
	 * Gets whether default weapons should be generated automatically.
	 */
	public boolean doesAutoGenerateDefaultWeapons() {
		return autoGenerateDefaultWeapons;
	}

	/**
	 * Sets whether default weapons should be generated automatically.
	 */
	public void setAutoGenerateDefaultWeapons(boolean autoGenerateDefaultWeapons) {
		this.autoGenerateDefaultWeapons = autoGenerateDefaultWeapons;
	}

	/**
	 * Gets the interval between auto-saves (minutes).
	 */
	public int getAutoSaveTimerInterval() {
		return autoSaveTimerInterval;
	}

	/**
	 * Sets the interval between auto-saves (minutes).
	 */
	public void setAutoSaveTimerInterval(int autoSaveTimerInterval) {
		this.autoSaveTimerInterval = autoSaveTimerInterval < 1 ? DEFAULT_AUTO_SAVE_TIMER_INTERVAL : autoSaveTimerInterval;

		int delay = autoSaveTimerInterval * 60 * 1000;

		PluginCore.getAutoSaveTimer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				PluginCore.autoSave();
			}
		}, delay, delay);
	}

	/**
	 * Gets the default mystery box cost.
	 */
	public int getDefaultMysteryBoxCost() {
		return defaultMysteryBoxCost;
	}

	/**
	 * Sets the default mystery box cost.
	 */
	public void setDefaultMysteryBoxCost(int defaultMysteryBoxCost) {
		this.defaultMysteryBoxCost = defaultMysteryBoxCost;
	}

	/**
	 * Gets whether debugging is currently enabled.
	 */
	public boolean isDebuggingEnabled() {
		return enableDebugging;
	}

	/**
	 * Sets whether debugging is enabled.
	 */
	public void setEnableDebugging(boolean enableDebugging) {
		this.enableDebugging = enableDebugging;
	}

	/**
	 * Gets the world name.
	 */
	public String getWorldName() {
		return worldName;
	}

	/**
	 * Sets the world name.
	 */
	public void setWorldName(String worldName) {
		this.worldName = worldName;
	}
	//endregion
}