package me.luucka.custombook.menus;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.CustomBook;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private final Player performerPlayer;
    private final List<ItemStack> books = new ArrayList<>();
    private static final List<Inventory> invs = new ArrayList<>();

    public BookList(Player performerPlayer) {
        this.performerPlayer = performerPlayer;
        setBooks();
        createInventories();
    }

    public static Inventory getInv(int i) {
        return invs.get(i);
    }

    private void setBooks() {
        CustomBook.getInstance().dataManager.getConfig().getConfigurationSection("books").getKeys(false).forEach(b -> {
            BookManager bm = new BookManager(performerPlayer, b);
            try {
                bm.createWrittenBook();
                books.add(bm.getBookItem());
            } catch (BookErrorException e) {
                performerPlayer.sendMessage(Utils.msgConfig(performerPlayer, "todo")); //Error during inv creation
            }
        });
    }

    private void createInventories() {
        if (books.size() <= 54) {
            invs.add(Bukkit.createInventory(null, 54, Utils.getString("book-list-inventory-title")));
            for (int i = 0; i < books.size(); i++) {
                invs.get(0).setItem(i, books.get(i));
            }
        } else {
            int inventoriesToCreate = (books.size() / 45) + 1;
            for (int i = 0; i < inventoriesToCreate; i++) {
                invs.add(Bukkit.createInventory(
                        null, 54, Utils.getString("book-list-inventory-title") + " - page " + (i + 1)));
                for (int j = 0; j < 45; j++) {
                    if (j + (45 * i) < books.size()) {
                        invs.get(i).setItem(j, books.get(j + (45 * i)));
                    }
                }

                if (i == 0) {
                    invs.get(i).setItem(50, changePageItem("&a&lNext page"));
                } else if (i == inventoriesToCreate - 1) {
                    invs.get(i).setItem(48, changePageItem("&c&lPrevious page"));
                } else {
                    invs.get(i).setItem(48, changePageItem("&c&lPrevious page"));
                    invs.get(i).setItem(50, changePageItem("&a&lNext page"));
                }

            }
        }
    }

    private ItemStack changePageItem(String name) {
        ItemStack pageItem = new ItemStack(Material.ARROW);
        ItemMeta meta = pageItem.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        pageItem.setItemMeta(meta);
        return pageItem;
    }
}
