package me.luucka.custombook;

import me.clip.placeholderapi.PlaceholderAPI;
import me.luucka.custombook.utils.BookErrorException;
import me.luucka.custombook.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private final Player performerPlayer;
    private final String bookName;
    private ItemStack bookItem;
    private BookMeta bookMeta;

    public BookManager(Player performerPlayer, String bookName) {
        this.performerPlayer = performerPlayer;
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public ItemStack getBookItem() {
        this.bookItem.setItemMeta(this.bookMeta);
        return bookItem;
    }

    //------------------------------------------------------------------------------------------------------------------

    /*
        This getter and setter work directly with books.yml file
     */

    /**
     *
     * @return title from books.yml of the book with bookName
     */
    public String getTitle() {
        return CustomBook.getInstance().dataManager.getConfig().getString("books." + bookName + ".title");
    }

    /**
     * Set title of a book
     */
    public void setTitle() {
        CustomBook.getInstance().dataManager.getConfig().set("books." + bookName + ".title", this.bookMeta.getTitle());
        saveBook();
    }

    /**
     *
     * @return author from books.yml of the book with bookName
     */
    public String getAuthor() {
        return CustomBook.getInstance().dataManager.getConfig().getString("books." + bookName + ".author");
    }

    /**
     * Set author of a book
     */
    public void setAuthor() {
        CustomBook.getInstance().dataManager.getConfig().set("books." + bookName + ".author", this.bookMeta.getAuthor());
        saveBook();
    }

    /**
     *
     * @return pages from books.yml of the book with bookName
     */
    public List<String> getPages(boolean useColors, boolean usePlaceholderAPI) {
        List<String> pages = new ArrayList<>();
        CustomBook.getInstance().dataManager.getConfig().getConfigurationSection("books." + bookName + ".pages")
                .getKeys(false).forEach(p -> {
                    String page = CustomBook.getInstance().dataManager.getConfig().getString("books." + bookName + ".pages." + p);
                    if (useColors) page = Utils.hexColor(page);
                    if (CustomBook.isUsePlaceholderAPI()) {
                        if (usePlaceholderAPI) page = PlaceholderAPI.setPlaceholders(this.performerPlayer, page);
                    }
            pages.add(Integer.parseInt(p), page);
        });
        return pages;
    }

    /**
     * Set pages of a book
     */
    public void setPages() {
        for (int i = 0; i < this.bookMeta.getPages().size(); i++) {
            CustomBook.getInstance().dataManager.getConfig().set("books." + bookName + ".pages." + i, this.bookMeta.getPages().get(i));
        }

        saveBook();
    }

    /*
        End method working with books.yml
     */

    //------------------------------------------------------------------------------------------------------------------

    /*
        CUD methods for books
     */

    /**
     * Save books.yml
     */
    public void saveBook() {
        CustomBook.getInstance().dataManager.saveConfig();
        CustomBook.getInstance().dataManager.reloadConfig();
    }

    /**
     *
     * @return TRUE if book exists, FALSE otherwise
     */
    public boolean bookExists() {
        return CustomBook.getInstance().dataManager.getConfig().contains("books." + bookName);
    }


    public void addBook(ItemStack newBook) throws BookErrorException {
        // Some checks
        if (!newBook.getType().equals(Material.WRITTEN_BOOK)) {
            throw new BookErrorException(Utils.getString("no-written-book"));
        }
        if (bookExists()) {
            throw new BookErrorException(Utils.getString("book-exists"));
        }

        // Assign newBook to bookItem and bookMeta
        this.bookItem = newBook;
        this.bookMeta = (BookMeta) this.bookItem.getItemMeta();

        // Set Title, Author and Pages into books.yml
        setTitle();
        setAuthor();
        setPages();

        // Save books.yml
        saveBook();
    }

    public void updateBook(ItemStack editBook) throws BookErrorException {
        if (!editBook.getType().equals(Material.WRITTEN_BOOK)
                && !editBook.getType().equals(Material.WRITABLE_BOOK)) {
            throw new BookErrorException(Utils.getString("no-written-writable-book"));
        }
        if (!bookExists()) {
            throw new BookErrorException(Utils.getString("book-not-exists"));
        }

        // Assign newBook to bookItem and bookMeta
        this.bookItem = editBook;
        this.bookMeta = (BookMeta) this.bookItem.getItemMeta();

        // Set Title, Author and Pages into books.yml
        if (editBook.getType().equals(Material.WRITTEN_BOOK)) {
            setTitle();
            setAuthor();
        }
        setPages();

        // Save books.yml
        saveBook();
    }

    public void deleteBook() throws BookErrorException {
        if (!bookExists()) {
            throw new BookErrorException(Utils.getString("book-not-exists"));
        }
        CustomBook.getInstance().dataManager.getConfig().set("books." + bookName, null);
        saveBook();
    }

    /*
        End CUD methods for books
     */

    //------------------------------------------------------------------------------------------------------------------

    public void createWrittenBook() throws BookErrorException {
        if (!bookExists()) {
            throw new BookErrorException(Utils.getString("book-not-exists"));
        }
        this.bookItem = new ItemStack(Material.WRITTEN_BOOK);
        this.bookMeta = (BookMeta) this.bookItem.getItemMeta();

        this.bookMeta.setTitle(getTitle());
        this.bookMeta.setAuthor(getAuthor());
        this.bookMeta.setPages(getPages(true, true));
    }

    public void createWritableBook() throws BookErrorException {
        if (!bookExists()) {
            throw new BookErrorException(Utils.getString("book-not-exists"));
        }
        this.bookItem = new ItemStack(Material.WRITABLE_BOOK);
        this.bookMeta = (BookMeta) this.bookItem.getItemMeta();

        this.bookMeta.setPages(getPages(false, false));
        this.bookMeta.setDisplayName("Editing: " + this.bookName);
    }
}