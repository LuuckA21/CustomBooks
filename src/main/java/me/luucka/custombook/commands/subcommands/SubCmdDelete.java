package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.CustomBook;
import me.luucka.custombook.utils.BookErrorException;
import me.luucka.custombook.utils.Perms;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SubCmdDelete extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete a book";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName() + " <bookname>";
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_DELETE;
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            player.sendMessage(Utils.msgConfig(player, getSyntax()));
            return;
        }
        BookManager bookManager = new BookManager(player, args[1]);
        try {
            bookManager.deleteBook();
            player.sendMessage(Utils.msgConfig(player, Utils.getString("book-deleted")));
        } catch (BookErrorException e) {
            player.sendMessage(Utils.msgConfig(player, e.getMessage()));
        }
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        if (args.length == 2) {
            List<String> suggestions = new ArrayList<>();
            List<String> arguments = new ArrayList<>(CustomBook.getInstance().dataManager.getConfig()
                    .getConfigurationSection("books").getKeys(false));
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                    suggestions.add(a);
                }
            }
            return suggestions;
        }
        return null;
    }
}
