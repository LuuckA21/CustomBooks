package me.luucka.custombook;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookStocaz {

    private final Player performerPlayer;
    private final String bookName;
    private final ItemStack bookItem;
    private final BookMeta bookMeta;

    public BookStocaz(Player player, String bookName, Material material) {
        this.performerPlayer = player;
        this.bookName = bookName;
        this.bookItem = new ItemStack(material);
        this.bookMeta = (BookMeta) this.bookItem.getItemMeta();
    }
}
