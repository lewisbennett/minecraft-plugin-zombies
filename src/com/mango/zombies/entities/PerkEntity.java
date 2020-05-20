package com.mango.zombies.entities;

import com.mango.zombies.PluginCore;
import com.mango.zombies.serializers.PerkEntityJsonSerializer;
import org.bukkit.Material;

public class PerkEntity {

	//region Constant Values
	public static final PerkEntityJsonSerializer SERIALIZER = new PerkEntityJsonSerializer();

	public static final String COST_JSON_TAG = "cost";
	public static final String DOES_REQUIRE_POWER_JSON_TAG = "doesRequirePower";
	public static final String ID_JSON_TAG = "id";
	public static final String ITEM_JSON_TAG = "item";
	public static final String NAME_JSON_TAG = "name";
	//endregion

	//region Fields
	private boolean doesRequirePower;

	private int  cost;

	private Material item;

	private String id;
	private String name;
	//endregion

	//region Getters/Setters
	/**
	 * Gets the perk's cost.
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the perk's cost.
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Gets whether the perk requires power.
	 */
	public boolean doesRequirePower() {
		return doesRequirePower;
	}

	/**
	 * Sets whether the perk requires power.
	 */
	public void setDoesRequirePower(boolean doesRequirePower) {
		this.doesRequirePower = doesRequirePower;
	}

	/**
	 * Gets the perk's ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the perk's ID.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the perk's item.
	 */
	public Material getItem() {
		return item;
	}

	/**
	 * Sets the perk's item.
	 */
	public void setItem(Material item) {
		this.item = item;
	}

	/**
	 * Gets the perk's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the perk's name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	//endregion

	//region Constructors
	public PerkEntity() { }

	public PerkEntity(String id, String name, int cost) {

		this.id = id;
		this.name = name;
		this.cost = cost;

		PerkConfigEntity config = PluginCore.getPerkConfig();

		doesRequirePower = config.getDefaultDoesRequirePower();
		item = config.getDefaultMaterial();
	}
	//endregion
}