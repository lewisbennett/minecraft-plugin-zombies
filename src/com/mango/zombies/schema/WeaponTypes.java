package com.mango.zombies.schema;

public class WeaponTypes
{
	public static final String singleShot = "single_shot";
	public static final String buckShot = "buck_shot";
	public static final String melee = "melee";
	
	public static String[] toArray()
	{
		return new String[]
		{
			singleShot,
			buckShot,
			melee
		};
	}
}