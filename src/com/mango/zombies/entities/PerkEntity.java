package com.mango.zombies.entities;

import org.bukkit.Material;

public class PerkEntity
{
	public String id;
	public String name;
	public int  cost;
	public String item;
	public boolean doesRequirePower;
	public PerkServiceEntity[] services;
	
	public PerkEntity(String id, String name, int cost)
	{
		this.id = id;
		this.name = name;
		this.cost = cost;
		item = Material.DIAMOND.name();
		doesRequirePower = true;
		services = new PerkServiceEntity[0];
	}
}