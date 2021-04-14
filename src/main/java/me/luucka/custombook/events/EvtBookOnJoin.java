package me.luucka.custombook.events;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EvtBookOnJoin implements Listener {

    @EventHandler
    public void onJoinOpen(PlayerJoinEvent event) {
        if (!Utils.getBool("open.on-join")) {
            return;
        }
        Player player = event.getPlayer();
        BookManager bookManager = new BookManager(player, Utils.getString("open.book-name"));
        try {
            bookManager.createWrittenBook();
            if (Utils.getBool("open.first-join-only")) {
                if (!player.hasPlayedBefore()) {
                    player.openBook(bookManager.getBookItem());
                }
                return;
            }
            player.openBook(bookManager.getBookItem());
        } catch (BookErrorException e) {
            player.sendMessage(Utils.msgConfig(player, e.getMessage()));
        }
    }

    @EventHandler
    public void onJoinGive(PlayerJoinEvent event) {
        if (!Utils.getBool("give.on-join")) {
            return;
        }
        Player player = event.getPlayer();
        BookManager bookManager = new BookManager(player, Utils.getString("give.book-name"));
        try {
            bookManager.createWrittenBook();
            if (Utils.getBool("give.first-join-only")) {
                if (!player.hasPlayedBefore()) {
                    player.getInventory().addItem(bookManager.getBookItem());
                }
                return;
            }
            player.getInventory().addItem(bookManager.getBookItem());
        } catch (BookErrorException e) {
            player.sendMessage(Utils.msgConfig(player, e.getMessage()));
        }
    }

}
