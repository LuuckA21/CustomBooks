package me.luucka.custombook.menus;

import org.bukkit.entity.Player;

public class MenuUtility {

    private final Player player;

    private int currentPage;

    public MenuUtility(Player player) {
        this.player = player;
        this.currentPage = -1;
    }

    public Player getPlayer() {
        return player;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
