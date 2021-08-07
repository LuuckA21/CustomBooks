package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.menus.BookGetter;
import me.luucka.custombook.menus.BookMenu;
import me.luucka.custombook.menus.MenuUtility;
import me.luucka.custombook.permissions.PlayerPermission;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SubCmdList extends SubCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Open inventory and show list of books";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName();
    }

    @Override
    public String getPermission() {
        return PlayerPermission.CBOOK_LIST.getPermission();
    }

    @Override
    public void perform(Player player, String[] args) {
        //BookList bookList = new BookList(player);
        //player.openInventory(bookList.getInv(0));
        List<ItemStack> books = BookGetter.getBooksName();
        int inventoriesToCreate = books.size() <= 45 ? 1 : (books.size() / 45) + 1;
        for (int i = 0; i < inventoriesToCreate; i++) {
            BookMenu m = new BookMenu(
                    new MenuUtility(player), Utils.getString("book-list-inventory-title") + (i+1), i);
            m.setItems();
        }
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
