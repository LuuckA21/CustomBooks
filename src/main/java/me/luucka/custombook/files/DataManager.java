package me.luucka.custombook.files;

import me.luucka.custombook.CustomBook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

public class DataManager {

    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public DataManager() {
        saveDefaultConfig();
        //initConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(CustomBook.getInstance().getDataFolder(), "books.yml");
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStrem = CustomBook.getInstance().getResource("books.yml");
        if (defaultStrem != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStrem));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null) return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            CustomBook.getInstance().getLogger().log(Level.SEVERE, "Could not save config to "+ this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configFile == null) {
            this.configFile = new File(CustomBook.getInstance().getDataFolder(), "books.yml");
        }

        if (!this.configFile.exists()) {
            CustomBook.getInstance().saveResource("books.yml", false);
        }
    }

    public void initConfig() {

        //if (this.configFile.length() > 0) return;

        //List<String> books = new ArrayList<>(getConfig().getConfigurationSection("books").getKeys(false));


        getConfig().set("books.book1.author", "author");
        getConfig().set("books.book1.pages.0", "row1\nrow2\nrow3");
        saveConfig();
    }

    public boolean bookExists(String name) {

        return getConfig().contains("books." + name);

        /*List<String> books = new ArrayList<>();
        getConfig().getConfigurationSection("books").getKeys(false).forEach(k -> books.add(k.toLowerCase()));
        return books.contains(name.toLowerCase());*/

    }

    public boolean addNewBook(String title, String author, List<String> pages) {
        if (bookExists(title)) return false;
        getConfig().set("books." + title + ".author", author);
        for (int i = 0; i < pages.size(); i++) {
            getConfig().set("books." + title + ".pages." + i, pages.get(i));
        }
        saveConfig();
        reloadConfig();
        return true;
    }

    public boolean removeBook(String name) {
        if (!bookExists(name)) return false;
        getConfig().set("books." + name, null);
        saveConfig();
        reloadConfig();
        return true;
    }

    public boolean editBook(String title, String author, List<String> pages) {
        if (!bookExists(title)) return false;

        getConfig().set("books." + title + ".author", author);
        for (int i = 0; i < pages.size(); i++) {
            getConfig().set("books." + title + ".pages." + i, pages.get(i));
        }
        saveConfig();
        reloadConfig();
        return true;
    }

}
