package com.mango.zombies.gameplay;

import java.util.ArrayList;
import java.util.List;

import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.WeaponClassEntity;
import com.mango.zombies.entities.WeaponEntity;

public class Gameplay
{
	public List<MapEntity> maps = new ArrayList<MapEntity>();
	public List<WeaponClassEntity> weaponClasses = new ArrayList<WeaponClassEntity>();
	public List<WeaponEntity> weapons = new ArrayList<WeaponEntity>();
}