package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.Perms;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class SubCmdUpdate extends SubCommand {
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Update and save book";
    }

    @Override
    public String getSyntax() {
        return "/cbook "+ getName() + " <bookname>";
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_UPDATE;
    }

    @Override
    public void perform(Player player, String[] args) {
        ItemStack newBook = player.getInventory().getItemInMainHand();
        if (!newBook.getType().equals(Material.WRITTEN_BOOK)) {
            player.sendMessage("Non é un libro");
            return;
        }

        BookMeta meta = (BookMeta) newBook.getItemMeta();
        CustomBook.getInstance().dataManager.editBook(args[1], meta.getAuthor(), meta.getPages());
        player.getInventory().setItemInMainHand(null);
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
