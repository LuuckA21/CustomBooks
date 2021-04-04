package me.luucka.custombook.commands;

import me.luucka.custombook.CustomBook;
import me.luucka.custombook.commands.subcommands.*;
import me.luucka.custombook.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdCBook implements TabExecutor {

    private List<SubCommand> subCommands = new ArrayList<>();

    public CmdCBook() {
        subCommands.add(new SubCmdOpen());
        subCommands.add(new SubCmdCreate());
        subCommands.add(new SubCmdReload());
        subCommands.add(new SubCmdDelete());
        subCommands.add(new SubCmdGive());
        subCommands.add(new SubCmdEdit());
        subCommands.add(new SubCmdUpdate());
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            CustomBook.getInstance().reloadConfig();
            CustomBook.getInstance().dataManager.reloadConfig();
            sender.sendMessage("CustomBooks reload!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    if (!player.hasPermission(getSubCommands().get(i).getPermission())) {
                        player.sendMessage(Utils.msgConfig(player, Utils.getString("no-perm")));
                        return true;
                    }
                    getSubCommands().get(i).perform(player, args);
                }
            }
        } else {
            if (!player.hasPermission(Perms.CBOOK_HELP)) {
                player.sendMessage(Utils.msgConfig(player, Utils.getString("no-perm")));
                return true;
            }
            player.sendMessage("----------------------------");
            player.sendMessage("CustomBooks commands:");
            getSubCommands().forEach(s -> {
                if (player.hasPermission(s.getPermission())) {
                    player.sendMessage(s.getSyntax() + " - " + s.getDescription());
                }
            });
            player.sendMessage("----------------------------");
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1) {
            getSubCommands().forEach(s -> {
                if (sender.hasPermission(s.getPermission())) {
                    suggestions.add(s.getName());
                }
            });
            return suggestions;
        } else if (args.length == 2) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    if (sender.hasPermission(getSubCommands().get(i).getPermission())) {
                        return getSubCommands().get(i).getSubcommandArgs((Player) sender, args);
                    }
                }
            }
        }
        return null;
    }
}
