package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.utils.Perms;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SubCmdGive extends SubCommand {
    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "Give you a book and quil";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName();
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_GIVE;
    }

    @Override
    public void perform(Player player, String[] args) {
        player.getInventory().addItem(new ItemStack(Material.WRITABLE_BOOK));
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
