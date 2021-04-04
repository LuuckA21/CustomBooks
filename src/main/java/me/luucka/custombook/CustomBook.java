package me.luucka.custombook;

import me.luucka.custombook.commands.CmdCBook;
import me.luucka.custombook.files.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomBook extends JavaPlugin {

    private static CustomBook instance;

    public DataManager dataManager;

    private static boolean usePlaceholderAPI;

    @Override
    public void onEnable() {
        instance = this;
        this.dataManager = new DataManager();
        usePlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        saveDefaultConfig();
        getCommand("cbook").setExecutor(new CmdCBook());
    }

    public static CustomBook getInstance() {
        return instance;
    }

    public static boolean isUsePlaceholderAPI() {
        return usePlaceholderAPI;
    }
}
