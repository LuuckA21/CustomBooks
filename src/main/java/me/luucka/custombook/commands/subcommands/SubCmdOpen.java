package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.*;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.exceptions.BookErrorException;
import me.luucka.custombook.permissions.PlayerPermission;
import me.luucka.custombook.utility.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SubCmdOpen extends SubCommand {


    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String getDescription() {
        return "Open selected book";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName() + " <bookname>";
    }

    @Override
    public String getPermission() {
        return PlayerPermission.CBOOK_OPEN.getPermission();
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
            player.openBook(bookManager.getBookItem());
            CustomBook.get_menuUtilityMap(player).setCurrentPage(0);
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
