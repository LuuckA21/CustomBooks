package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.permissions.PlayerPermission;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SubCmdCreate extends SubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create a new book";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName() + " <bookname>";
    }

    @Override
    public String getPermission() {
        return PlayerPermission.CBOOK_CREATE.getPermission();
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            player.sendMessage(Utils.msgConfig(player, "&#ff5747Usage: " + getSyntax()));
            return;
        }
        ItemStack newBook = player.getInventory().getItemInMainHand();
        BookManager bookManager = new BookManager(player, args[1]);
        try {
            bookManager.addBook(newBook);
            player.getInventory().setItemInMainHand(null);
            player.sendMessage(Utils.msgConfig(player, Utils.getString("book-created")));
        } catch (BookErrorException e) {
            player.sendMessage(Utils.msgConfig(player, e.getMessage()));
        }
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
