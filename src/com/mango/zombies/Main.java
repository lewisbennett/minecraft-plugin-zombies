package com.mango.zombies;

import com.mango.zombies.commands.*;
import com.mango.zombies.entities.MapEntity;
import com.mango.zombies.entities.SignEntity;
import com.mango.zombies.helper.SignUtil;
import com.mango.zombies.listeners.*;
import com.mango.zombies.services.StockFilingService;
import com.mango.zombies.services.StockGameplayService;
import com.mango.zombies.services.StockMessagingService;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.TimerTask;

public class Main extends JavaPlugin {

    //region Static Fields
    private static Main instance;
    //endregion

    //region Static Getters/Setters
    /**
     * Gets the plugin instance.
     */
    public static Main getInstance() {
        return instance;
    }
    //endregion

    //region Lifecycle
    @Override
    public void onEnable() {

        instance = this;

        registerServices();

        registerCommands();

        registerEvents();

        PluginCore.getFilingService().setupFolders(getDataFolder());

        PluginCore.getFilingService().importEverything();

        resetAllMapSigns();

        enableAllMaps();

        long delay = Time.fromMinutes(PluginCore.getConfig().getAutoSaveTimerInterval()).totalMilliseconds();

        PluginCore.getFilingService().getAutoSaveTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PluginCore.autoSave();
            }
        }, delay, delay);
    }

    @Override
    public void onDisable() {
        PluginCore.getFilingService().saveEverything();
    }
    //endregion

    //region Public Methods
    /**
     * Registers all plugin commands with corresponding executors.
     */
    public void registerCommands() {

        this.getCommand("info").setExecutor(new InfoCommandExecutor());
        this.getCommand("createsession").setExecutor(new CreateSessionCommandExecutor());
        this.getCommand("joinsession").setExecutor(new JoinSessionCommandExecutor());
        this.getCommand("leavesession").setExecutor(new LeaveSessionCommandExecutor());
        this.getCommand("listsessions").setExecutor(new ListSessionsCommandExecutor());
        this.getCommand("createenemy").setExecutor(new CreateEnemyCommandExecutor());
        this.getCommand("createmap").setExecutor(new CreateMapCommandExecutor());
        this.getCommand("deletemap").setExecutor(new DeleteMapCommandExecutor());
        this.getCommand("addgamemode").setExecutor(new AddGamemodeCommandExecutor());
        this.getCommand("enablemap").setExecutor(new EnableMapCommandExecutor());
        this.getCommand("disablemap").setExecutor(new DisableMapCommandExecutor());
        this.getCommand("mapinfo").setExecutor(new MapInfoCommandExecutor());
        this.getCommand("listmaps").setExecutor(new ListMapsCommandExecutor());
        this.getCommand("createperk").setExecutor(new CreatePerkCommandExecutor());
        this.getCommand("createweapon").setExecutor(new CreateWeaponCommandExecutor());
        this.getCommand("getweapon").setExecutor(new GetWeaponCommandExecutor());
        this.getCommand("getpositiontool").setExecutor(new GetPositionToolCommandExecutor());
        this.getCommand("getspawningtool").setExecutor(new GetSpawningToolCommandExecutor());
    }

    /**
     * Registers all plugin event handlers.
     */
    public void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryPickupItemListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEggThrowListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupArrowListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignChangedListener(), this);
    }

    /**
     * Registers all plugin services.
     */
    public void registerServices() {

        PluginCore.setFilingService(new StockFilingService());
        PluginCore.setGameplayService(new StockGameplayService());
        PluginCore.setMessagingService(new StockMessagingService());
    }
    //endregion

    //region Private Methods
    private void enableAllMaps() {

        int enabledCount = 0;
        int disableCount = 0;

        for (MapEntity queryMap : PluginCore.getMaps()) {

            try {

                queryMap.enableMap();

            } catch (Exception ignored) { }

            if (queryMap.isEnabled())
                enabledCount++;
            else
                disableCount++;
        }

        String message = enabledCount + " " + (enabledCount == 1 ? "map has" : "maps have") + " been enabled.";

        if (disableCount > 0)
            message += " " + disableCount + " " + (disableCount == 1 ? "map is" : "maps are") + " currently disabled.";

        Log.information(message);
    }

    private void resetAllMapSigns() {

        Log.information("Validating signs...");

        int removals = 0;

        for (MapEntity mapEntity : PluginCore.getMaps()) {

            World world = getServer().getWorld(mapEntity.getWorldName());

            if (world == null)
                continue;

            for (SignEntity signEntity : mapEntity.getSigns()) {

                Block block = world.getBlockAt(signEntity.getLocation().getX(), signEntity.getLocation().getY(), signEntity.getLocation().getZ());

                if (!(block.getState() instanceof Sign)) {

                    mapEntity.removeSign(signEntity);
                    removals++;

                    continue;
                }

                Sign sign = (Sign)block.getState();

                String[] lines;

                try{

                    lines = SignUtil.getLinesForSign(mapEntity, signEntity);

                } catch (Exception ex) {

                    Log.information("Exception: " + ex.getMessage());

                    mapEntity.removeSign(signEntity);
                    removals++;

                    sign.setLine(0, "");
                    sign.setLine(1, "INVALID");
                    sign.setLine(2, "SIGN");
                    sign.setLine(3, "");

                    continue;
                }

                for (int i = 0; i < lines.length; i++)
                    sign.setLine(i, lines[i]);

                sign.update();
            }
        }

        if (removals == 1)
            Log.information("Sign validation complete. 1 error was detected and the sign was removed.");

        else if (removals > 1)
            Log.information("Sign validation complete. " + removals + " errors were detected and the signs were removed.");

        else
            Log.information("Sign validation complete.");
    }
    //endregion
}