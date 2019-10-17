package com.mango.zombies.schema;

public class WeaponTypes
{
	public static final String SINGLE_SHOT = "single_shot";
	public static final String BUCK_SHOT = "buck_shot";
	public static final String MELEE = "melee";
	
	public static String[] toArray()
	{
		return new String[]
		{
			SINGLE_SHOT,
			BUCK_SHOT,
			MELEE
		};
	}
}