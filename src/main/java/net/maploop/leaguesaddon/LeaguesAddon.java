package net.maploop.leaguesaddon;

import net.maploop.leaguesaddon.commands.PerksCommand;
import net.maploop.leaguesaddon.commands.ResetperksCommand;
import net.maploop.leaguesaddon.listeners.InventoryClick;
import net.maploop.leaguesaddon.listeners.PlayerDeath;
import net.maploop.leaguesaddon.listeners.PlayerInteract;
import net.maploop.leaguesaddon.listeners.PlayerJoin;
import net.maploop.leaguesaddon.menus.PlayerMenuUtility;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public final class LeaguesAddon extends JavaPlugin {
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");
    private static LeaguesAddon INSTANCE;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        INSTANCE = this;
        createDataFile();
        loadCommands();
        loadListeners();

        log.severe(String.format("[%s] - Enabled plugin!", getDescription().getName()));
    }

    @Override
    public void onDisable() {
        getLogger().info("disabled!");
    }

    private void loadCommands() {
        this.getCommand("perks").setExecutor(new PerksCommand());
        this.getCommand("resetperks").setExecutor(new ResetperksCommand());
    }

    private void loadListeners() {
        this.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    private File customConfigFile;
    private FileConfiguration customConfig;

    public FileConfiguration getData() {
        return this.customConfig;
    }

    public void saveData() {
        try {
            this.customConfig.save(customConfigFile);
        } catch (IOException e) {
            System.out.println("An error occurred while saving data! [WARN] [CRITICAL]");
        }
    }

    private void createDataFile() {
        customConfigFile = new File(getDataFolder(), "data.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static LeaguesAddon getInstance() {
        return INSTANCE;
    }
}
