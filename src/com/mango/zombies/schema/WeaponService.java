package com.mango.zombies.schema;

import java.util.ArrayList;
import java.util.List;

public class WeaponService {

	//region Constant Values
	public static final String GUNSHOT = "gunshot";
	public static final String MELEE = "melee";
	//endregion

	//region Public Static Methods
	/**
	 * Gets all weapon service types as a list.
	 */
	public static List<String> toList() {

		List<String> types = new ArrayList<String>();

		types.add(GUNSHOT);
		types.add(MELEE);

		return types;
	}
	//endregion
}