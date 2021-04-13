package me.luucka.custombook.events;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EvtGiveBookOnJoin implements Listener {

    public void onJoin(PlayerJoinEvent event) {
        if (!Utils.getBool("give-on-join")) {
            return;
        }
        Player player = event.getPlayer();
        BookManager bookManager = new BookManager(player, Utils.getString("book-name"));
        try {
            bookManager.createWrittenBook();
            if (Utils.getBool("only-first-join")) {
                if (!player.hasPlayedBefore()) {
                    player.openBook(bookManager.getBookItem());
                    return;
                }
            }
            player.openBook(bookManager.getBookItem());
        } catch (BookErrorException e) {
            player.sendMessage(Utils.msgConfig(player, e.getMessage()));
        }
    }

}
