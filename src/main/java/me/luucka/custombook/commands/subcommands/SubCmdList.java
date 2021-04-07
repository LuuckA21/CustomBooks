package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.inventories.BookList;
import me.luucka.custombook.permissions.PlayerPermission;
import org.bukkit.entity.Player;

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
        return PlayerPermission.CBOOK_LIST.getPermssion();
    }

    @Override
    public void perform(Player player, String[] args) {
        BookList bookList = new BookList(player);
        player.openInventory(bookList.getInv(0));
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
