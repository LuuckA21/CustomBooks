package me.luucka.custombook.utility;

import me.luucka.custombook.CustomBook;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class VersionChecker {

    private static final int resourceID = 90931;

    public static String getValueFromWebsite(String website) throws IOException {
        URL url = new URL(website);
        Scanner sc = new Scanner(url.openStream());
        StringBuilder sb = new StringBuilder();
        while(sc.hasNext()) {
            sb.append(sc.next());
        }
        return sb.toString();
    }

    public static String getVersion() {
        try {
            return getValueFromWebsite("https://api.spigotmc.org/legacy/update.php?resource=" + resourceID);
        } catch (IOException e) {
            CustomBook.getInstance().getLogger().info("Cannot look for updates: " + e.getMessage());
        }
        return null;
    }
}
