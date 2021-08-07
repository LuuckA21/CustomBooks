package me.luucka.custombook.events;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.menus.BookList;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EvtMenuBookListOnClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains(Utils.getString("book-list-inventory-title"))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Next page")) {
            int currentPage = CustomBook.get_menuUtilityMap((Player) event.getWhoClicked()).getCurrentPage();
            CustomBook.get_menuUtilityMap((Player) event.getWhoClicked()).setCurrentPage(currentPage + 1);
            player.openInventory(BookList.getInv(currentPage + 1));
            return;
        }
        player.openBook(event.getCurrentItem());
    }

}
