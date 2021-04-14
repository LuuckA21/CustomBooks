package me.luucka.custombook.files;

import me.luucka.custombook.CustomBook;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public DataManager() {
        saveDefaultConfig();
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

}
