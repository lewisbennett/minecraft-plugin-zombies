package com.mango.zombies.listeners;

import com.mango.zombies.Log;
import com.mango.zombies.PluginCore;
import com.mango.zombies.entities.LocationEntity;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.SignEntity;
import com.mango.zombies.helper.SignUtil;
import com.mango.zombies.schema.Sign;
import com.mango.zombies.schema.SignResource;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangedListener implements Listener {

	//region Constant Values
	public static final String INVALID_MAP_ERROR = "Sign not created. %s is not a valid map.";
	public static final String INVALID_MAP_ERROR_GENERIC = "Sign not created. Invalid map.";
	public static final String INVALID_SIGN_POSITION_ERROR = "Sign not created. The sign must be within the bounds of the map.";
	public static final String INVALID_TYPE_ERROR_GENERIC = "Sign not created. Invalid sign type.";
	//endregion

	//region Event Handlers
	@EventHandler
	public void onSignChange(SignChangeEvent event) {

		String[] lines = event.getLines();

		String header = lines[0] == null ? "" : lines[0];
		String type = lines[1] == null ? "" : lines[1];
		String paramOne = lines[2] == null ? "" : lines[2];
		String paramTwo = lines[3] == null ? "" : lines[3];

		if (!header.equalsIgnoreCase(Sign.HEADER))
			return;

		if (type.isEmpty()) {
			PluginCore.getMessagingService().error(event.getPlayer(), INVALID_TYPE_ERROR_GENERIC);
			return;
		}

		MapEntity mapEntity = null;

		switch (type) {

			case Sign.JOIN:
			case Sign.SPECTATE:

				for (MapEntity queryMap : PluginCore.getMaps()) {

					if (queryMap.getId().equals(paramOne)) {
						mapEntity = queryMap;
						break;
					}
				}

				if (mapEntity == null) {
					PluginCore.getMessagingService().error(event.getPlayer(), paramOne.isEmpty() ? INVALID_MAP_ERROR_GENERIC : String.format(INVALID_MAP_ERROR, paramOne));
					return;
				}

				break;

			default:

				for (MapEntity queryMap : PluginCore.getMaps()) {

					if (queryMap.isWithinMapBounds(event.getBlock().getLocation())) {
						mapEntity = queryMap;
						break;
					}
				}

				if (mapEntity == null) {
					PluginCore.getMessagingService().error(event.getPlayer(), INVALID_SIGN_POSITION_ERROR);
					return;
				}
		}

		SignEntity signEntity = new SignEntity();

		signEntity.setLocation(new LocationEntity(event.getBlock().getLocation()));
		signEntity.setType(type);

		switch (signEntity.getType()) {

			case Sign.PERK:
				signEntity.setResourceType(SignResource.PERK_ID);
				signEntity.setResourceValue(paramOne);
				break;

			case Sign.WEAPON:
				signEntity.setResourceType(SignResource.WEAPON_ID);
				signEntity.setResourceValue(paramOne);
				break;

			default:
				signEntity.setResourceType(SignResource.MAP_ID);
				signEntity.setResourceValue(mapEntity.getId());
		}

		String[] formattedLines;

		try {

			formattedLines = SignUtil.getLinesForSign(mapEntity, signEntity);

		} catch (IllegalArgumentException ex) {

			PluginCore.getMessagingService().error(event.getPlayer(), ex.getMessage());
			return;

		} catch (Exception ex) {

			String error = "An unknown error occurred.";

			if (PluginCore.getConfig().isDebuggingEnabled()) {

				String stackTraceFileName = PluginCore.getFilingService().writeStackTraceFile(error, ExceptionUtils.getStackTrace(ex));

				if (stackTraceFileName != null)
					error += ". View the stacktrace: " + stackTraceFileName;
			}

			Log.information(error);

			return;
		}

		for (int i = 0; i < formattedLines.length; i++)
			event.setLine(i, formattedLines[i]);

		for (SignEntity querySign : mapEntity.getSigns()) {

			if (!mapEntity.getWorldName().equals(event.getBlock().getWorld().getName()))
				continue;

			if (querySign.getLocation().getX() != event.getBlock().getLocation().getBlockX())
				continue;

			if (querySign.getLocation().getY() != event.getBlock().getLocation().getBlockY())
				continue;

			if (querySign.getLocation().getZ() != event.getBlock().getLocation().getBlockZ())
				continue;

			mapEntity.removeSign(querySign);
		}

		mapEntity.addSign(signEntity);
	}
	//endregion
}