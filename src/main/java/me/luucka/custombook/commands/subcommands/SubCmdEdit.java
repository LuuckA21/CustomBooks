package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.*;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SubCmdEdit extends SubCommand {
    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getDescription() {
        return "Edit a book";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName() + " <bookname>";
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_EDIT;
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length == 1) {
            player.sendMessage(Utils.elaborateMessageConfig(player, "&#ff5747 Usage: " + getSyntax()));
            return;
        }

        BookManager book = new BookManager(player, args[1], false);
        if (book.loadFromConfig()) {
            player.getInventory().addItem(book.getBook());
            return;
        }
        player.sendMessage("Book '" + args[1] + "' does not exists");
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
