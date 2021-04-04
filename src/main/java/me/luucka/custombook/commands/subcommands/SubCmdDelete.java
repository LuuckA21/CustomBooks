package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.Perms;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.entity.Player;

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
        CustomBook.getInstance().dataManager.removeBook(args[1]);
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
