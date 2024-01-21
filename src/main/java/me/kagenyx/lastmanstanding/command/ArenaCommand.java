package me.kagenyx.lastmanstanding.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length == 1 && args[0].equalsIgnoreCase("list")){

            } else if(args.length == 1 && args[0].equalsIgnoreCase("leave")){

            } else if(args.length == 2 && args[0].equalsIgnoreCase("join")){

            } else {

            }


        }

        return false;
    }
}
