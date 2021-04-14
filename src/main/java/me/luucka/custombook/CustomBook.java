package me.luucka.custombook;

import me.luucka.custombook.commands.CmdCBook;
import me.luucka.custombook.events.EvtBookOnJoin;
import me.luucka.custombook.events.EvtMenuBookListOnClick;
import me.luucka.custombook.files.DataManager;
import me.luucka.custombook.utility.VersionChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CustomBook extends JavaPlugin {

    private static CustomBook _instance;

    public DataManager dataManager;

    private static boolean _usePlaceholderAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        _loadVariables();
        _loadCommands();
        _loadEvents();
        _printOnEnable();
        _checkUpdates();
        Metrics metrics = new Metrics(this, 11016);
    }

    private void _loadVariables() {
        _instance = this;
        dataManager = new DataManager();
        _usePlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
    }

    private void _loadCommands() {
        getCommand("cbook").setExecutor(new CmdCBook());
    }

    private void _loadEvents() {
        getServer().getPluginManager().registerEvents(new EvtMenuBookListOnClick(), this);
        getServer().getPluginManager().registerEvents(new EvtBookOnJoin(), this);
    }

    private void _checkUpdates() {
        if (getConfig().getBoolean("check-updates")) {
            if (!getDescription().getVersion().equalsIgnoreCase(VersionChecker.getVersion())) {
                getLogger().log(Level.INFO, ChatColor.translateAlternateColorCodes('&', "&cCustomBooks is out of date!"));
            }
        }
    }

    private void _printOnEnable() {
        System.out.println();
        System.out.println(ChatColor.translateAlternateColorCodes('&', "    &7[&bCustom&3Books&7]"));
        System.out.println("      v" + getDescription().getVersion());
        System.out.println();
    }

    public static CustomBook getInstance() {
        return _instance;
    }

    public static boolean isUsePlaceholderAPI() {
        return _usePlaceholderAPI;
    }
}
