package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.BookManager;
import me.luucka.custombook.CustomBook;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.permissions.PlayerPermission;
import me.luucka.custombook.utility.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SubCmdGive extends SubCommand {
    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "Give you selected book";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName() + "<bookname>";
    }

    @Override
    public String getPermission() {
        return PlayerPermission.CBOOK_GIVE.getPermssion();
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            player.sendMessage(Utils.msgConfig(player, "&#ff5747Usage: " + getSyntax()));
            return;
        }
        BookManager bookManager = new BookManager(player, args[1]);
        try {
            bookManager.createWrittenBook();
            player.getInventory().addItem(bookManager.getBookItem());
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
