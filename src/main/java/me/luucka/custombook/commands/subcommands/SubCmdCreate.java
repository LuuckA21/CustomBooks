package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.Perms;
import me.luucka.custombook.Utils;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

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
        return "/cbook " + getName();
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_CREATE;
    }

    @Override
    public void perform(Player player, String[] args) {
        ItemStack newBook = player.getInventory().getItemInMainHand();
        if (!newBook.getType().equals(Material.WRITTEN_BOOK)) {
            player.sendMessage(Utils.elaborateMessageConfig(player, Utils.getString("no-book")));
            return;
        }

        BookMeta meta = (BookMeta) newBook.getItemMeta();
        boolean created = CustomBook.getInstance().dataManager.addNewBook(meta.getTitle(), meta.getAuthor(), meta.getPages());
        if (created) {
            player.sendMessage(Utils.elaborateMessageConfig(player, Utils.getString("book-created")));
        }
        player.getInventory().setItemInMainHand(null);
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
