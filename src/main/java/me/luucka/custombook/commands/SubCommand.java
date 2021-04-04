package me.luucka.custombook.commands;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    private List<SubCommand> subCommands = new ArrayList<>();

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract String getPermission();

    public abstract void perform(Player player, String[] args);

    public abstract List<String> getSubcommandArgs(Player player, String[] args);

}
