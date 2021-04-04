package me.luucka.custombook.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.luucka.custombook.CustomBook;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    public static List<String> hexColor(List<String> list) {
        list.forEach(Utils::hexColor);
        return list;
    }

    public static String hexColor(String content) {
        if (content.contains("&#")) {
            Matcher match = pattern.matcher(content);
            while (match.find()) {
                String color = content.substring(match.start(), match.end());
                content = content.replace(color, net.md_5.bungee.api.ChatColor.of(color.replaceAll("&", "")) + "");
                match = pattern.matcher(content);
            }
            return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', content);
        }
        return content;
    }

    public static String getString(String path) {
        return CustomBook.getInstance().getConfig().getString(path);
    }

    public static List<String> getStringList(String path) {
        return CustomBook.getInstance().getConfig().getStringList(path);
    }

    public static int getInt(String path) {
        return CustomBook.getInstance().getConfig().getInt(path);
    }

    public static String msgConfig(Player player, String s) {
        s = hexColor((getString("prefix") + s));
        if (CustomBook.isUsePlaceholderAPI()) {
            s = PlaceholderAPI.setPlaceholders(player, s);
        }
        return s;
    }

}

