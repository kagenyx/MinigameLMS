package me.kagenyx.lastmanstanding.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArenaTab implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        LinkedList<String> ll = new LinkedList<>();

        if(args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("join","leave","kit","team"), new LinkedList<>());
        } else if (args.length == 2) {
            if (args[0] == "join") {
                ll.add("0");
            }
        }

        return ll;
    }
}
