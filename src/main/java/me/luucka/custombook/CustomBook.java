package me.luucka.custombook;

import me.luucka.custombook.commands.CmdCBook;
import me.luucka.custombook.events.EvtMenuBookListOnClick;
import me.luucka.custombook.files.DataManager;
import me.luucka.custombook.utility.Utils;
import me.luucka.custombook.utility.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CustomBook extends JavaPlugin {

    private static CustomBook instance;

    public DataManager dataManager;

    //public BookList menuBookList;

    private static boolean usePlaceholderAPI;

    @Override
    public void onEnable() {
        instance = this;
        this.dataManager = new DataManager();
        usePlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        saveDefaultConfig();
        getCommand("cbook").setExecutor(new CmdCBook());
        getServer().getPluginManager().registerEvents(new EvtMenuBookListOnClick(), this);
        printOnEnable();
        checkUpdates();
    }

    private void checkUpdates() {
        if (getConfig().getBoolean("check-updates")) {
            if (!getDescription().getVersion().equalsIgnoreCase(VersionChecker.getVersion())) {
                getLogger().log(Level.INFO, "CustomBooks is out of date!");
            }
        }
    }

    private void printOnEnable() {
        System.out.println();
        System.out.println(Utils.hexColor("    &#c4c4c4[&#2ce897Custom&#2c68e8Books&#c4c4c4]"));
        System.out.println("      v" + getDescription().getVersion());
        System.out.println();
    }

    public static CustomBook getInstance() {
        return instance;
    }

    public static boolean isUsePlaceholderAPI() {
        return usePlaceholderAPI;
    }
}
