package me.luucka.custombook.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BookMenu extends PaginatedMenu {

    public BookMenu(MenuUtility menuUtility, String name, int page) {
        super(menuUtility, name, page);
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }

    @Override
    public void setItems() {
        List<ItemStack> books = BookGetter.getBooksName();
        for (int i = 0; i < super.slots; i++) {
            super.inventory.addItem(books.get(i + (45 * super.page)));
        }
        if (super.page == 0) {
            super.inventory.setItem(50, changePageItem("&a&lNext page"));
        } else if (super.page == - 1) {
            super.inventory.setItem(48, changePageItem("&c&lPrevious page"));
        } else {
            super.inventory.setItem(48, changePageItem("&c&lPrevious page"));
            super.inventory.setItem(50, changePageItem("&a&lNext page"));
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
