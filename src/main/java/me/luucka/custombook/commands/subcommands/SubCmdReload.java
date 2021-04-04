package me.luucka.custombook.commands.subcommands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.utils.Perms;
import me.luucka.custombook.commands.SubCommand;
import me.luucka.custombook.utils.Utils;
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
        player.sendMessage(Utils.msgConfig(player, Utils.getString("reload")));
    }

    @Override
    public List<String> getSubcommandArgs(Player player, String[] args) {
        return null;
    }
}
