package com.mango.zombies.entities;

import com.mango.zombies.serializers.PerkEntityJsonSerializer;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class PerkEntity {

	//region Constant Values
	public static final String COST_JSON_TAG = "cost";
	public static final String DOES_REQUIRE_POWER_JSON_TAG = "does_require_power";
	public static final String ID_JSON_TAG = "id";
	public static final String ITEM_JSON_TAG = "item";
	public static final String NAME_JSON_TAG = "name";
	public static final PerkEntityJsonSerializer SERIALIZER = new PerkEntityJsonSerializer();
	public static final String SERVICES_JSON_TAG = "services";
	//endregion

	//region Fields
	private int  cost;
	private boolean doesRequirePower;
	private String id;
	private String item;
	private String name;
	private List<PerkServiceEntity> services = new ArrayList<PerkServiceEntity>();
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
	public String getItem() {
		return item;
	}

	/**
	 * Sets the perk's item.
	 */
	public void setItem(String item) {
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

	/**
	 * Gets the perk's services.
	 */
	public List<PerkServiceEntity> getServices() {
		return services;
	}
	//endregion

	//region Constructors
	public PerkEntity() {
	}

	public PerkEntity(String id, String name, int cost) {

		this.id = id;
		this.name = name;
		this.cost = cost;
		item = Material.DIAMOND.name();
		doesRequirePower = true;
	}
	//endregion
}