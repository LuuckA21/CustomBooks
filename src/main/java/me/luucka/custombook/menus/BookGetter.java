package me.luucka.custombook.menus;

import me.luucka.custombook.CustomBook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BookGetter {

    public static List<ItemStack> getBooksName() {
        List<ItemStack> books = new ArrayList<>();
        CustomBook.getInstance().dataManager.getConfig().getConfigurationSection("books").getKeys(false).forEach(b -> {
            ItemStack newBook = new ItemStack(Material.WRITTEN_BOOK);
            ItemMeta meta = newBook.getItemMeta();
            meta.setDisplayName(b);
            newBook.setItemMeta(meta);
            books.add(newBook);
        });
        return books;
    }

}
