package me.luucka.custombook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private final Player builderPlayer;
    private final String name;
    private final boolean isOpening;
    private final ItemStack book;
    private final BookMeta meta;

    public BookManager(Player player, String name, boolean isOpening) {
        this.builderPlayer = player;
        this.name = name;
        this.isOpening = isOpening;
        this.book = new ItemStack(this.isOpening ? Material.WRITTEN_BOOK : Material.WRITABLE_BOOK);
        this.meta = (BookMeta) this.book.getItemMeta();
        if (this.isOpening) setTitle(name);
    }

    public Player getBuilderPlayer() {
        return builderPlayer;
    }

    public String getName() {
        return name;
    }

    public ItemStack getBook() {
        return book;
    }

    public BookMeta getMeta() {
        return meta;
    }

    public void setTitle(String title) {
        if (title.length() > 32) {
            throw new IllegalArgumentException("The book title must be at most 32 characters");
        }
        meta.setTitle(title);
    }

    public String getTitle() {
        return meta.getTitle();
    }

    public void setAuthor(String author) {
        meta.setAuthor(author);
    }

    public String getAuthor() {
        return meta.getAuthor();
    }

    public void setPages(List<String> pages) {
        meta.setPages(pages);
    }

    public List<String> getPages() {
        return meta.getPages();
    }

    public boolean loadFromConfig() {

        if (!CustomBook.getInstance().dataManager.bookExists(this.name)) {
            return false;
        }

        if (this.isOpening) setAuthor(CustomBook.getInstance().dataManager.getConfig().getString("books." + this.name + ".author"));

        List<String> pages = new ArrayList<>();
        CustomBook.getInstance().dataManager.getConfig().getConfigurationSection("books." + this.name + ".pages")
                .getKeys(false).forEach(p -> {
            String page = CustomBook.getInstance().dataManager.getConfig().getString("books." + this.name + ".pages." + p);
            if (this.isOpening) {
                page = Utils.hexColor(page);
                if (CustomBook.isUsePlaceholderAPI()) {
                    page = PlaceholderAPI.setPlaceholders(this.builderPlayer, page);
                }
            }
            pages.add(Integer.parseInt(p), page);
        });
        setPages(pages);
        if (!this.isOpening) {
            this.meta.setDisplayName("Editing: " + this.name);
        }

        this.book.setItemMeta(this.meta);
        return true;
    }

    public void openBook() {
        builderPlayer.openBook(this.book);
    }
}
