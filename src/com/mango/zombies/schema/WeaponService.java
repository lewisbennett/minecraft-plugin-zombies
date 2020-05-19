package com.mango.zombies.schema;

public class WeaponService {

	//region Constant Values
	public static final String GUNSHOT = "gunshot";
	public static final String MELEE = "melee";
	//endregion

	//region Public Static Methods
	/**
	 * Gets the available weapon service types as an array.
	 */
	public static String[] toArray() {

		return new String[] {
			GUNSHOT,
			MELEE
		};
	}
	//endregion
}