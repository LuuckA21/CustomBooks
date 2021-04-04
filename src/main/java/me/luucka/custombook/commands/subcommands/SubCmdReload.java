package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.Perms;
import me.luucka.custombook.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class SubCmdReload extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload plugin";
    }

    @Override
    public String getSyntax() {
        return "/cbook " + getName();
    }

    @Override
    public String getPermission() {
        return Perms.CBOOK_RELOAD;
    }

    @Override
    public void perform(Player player, String[] args) {
        CustomBook.getInstance().reloadConfig();
        CustomBook.getInstance().dataManager.reloadConfig();
        player.sendMessage("CustomBooks reload!");
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
