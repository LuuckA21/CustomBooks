package me.luucka.custombook.inventories;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.CustomBook;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.utility.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private final Player performerPlayer;
    private List<ItemStack> books = new ArrayList<>();
    private List<Inventory> invs = new ArrayList<>();

    public BookList(Player performerPlayer) {
        this.performerPlayer = performerPlayer;
        setBooks();
    }

    public Inventory getInv(int i) {
        return invs.get(i);
    }

    public void setBooks() {
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

    public void createInventories() {
        if (books.size() <= 54) {
            invs.add(Bukkit.createInventory(null, 54, "title"));
            for (int i = 0; i < books.size(); i++) {
                invs.get(0).setItem(i, books.get(i));
            }
        } else {
            int inventoriesToCreate = (books.size() / 45) + 1;
            for (int i = 0; i < inventoriesToCreate; i++) {
                invs.add(Bukkit.createInventory(null, 54, "page " + i));
                for (int j = 0; j < 45; j++) {
                    if (j + (45 * i) < books.size()) {
                        invs.get(i).setItem(j, books.get(j + (45 * i)));
                    }
                }
            }
        }
    }
}
