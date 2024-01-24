package me.kagenyx.lastmanstanding.command;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.kit.KitUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.naming.Name;

public class ArenaCommand implements CommandExecutor {

    private LastManStanding lms;

    public ArenaCommand(LastManStanding lms) {
        this.lms = lms;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                p.sendMessage(Component.text("These are the available arenas:"));
                for(Arena arena : lms.getArenaManager().getArenas()){
                    p.sendMessage(Component.text("- "+ arena.getId() + "(" + arena.getState().name() + ")", NamedTextColor.GREEN));
                }
            } else if(args.length == 1 && args[0].equalsIgnoreCase("leave")){
                Arena arena = lms.getArenaManager().getArena(p);
                if(arena != null) {
                    p.sendMessage(Component.text("You left the arena!",NamedTextColor.RED));
                    arena.removePlayer(p);
                } else {
                    p.sendMessage(Component.text("You are not in an arena",NamedTextColor.RED));
                }
            } else if(args.length == 2 && args[0].equalsIgnoreCase("join")){
                if(lms.getArenaManager().getArena(p) != null) {
                    p.sendMessage(Component.text("You are in an arena!",NamedTextColor.RED));
                    return false;
                }

                int id;
                try {
                    id = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    p.sendMessage(Component.text("Invalid arena.",NamedTextColor.RED));
                    return false;
                }

                if (id >= 0 && id < lms.getArenaManager().getArenas().size()){
                    Arena arena = lms.getArenaManager().getArena(id);
                    if (arena.getState() == GameState.LIVE) {
                        p.sendMessage(Component.text("Invalid arena.",NamedTextColor.RED));
                    } else {
                        p.sendMessage(Component.text("Great success!",NamedTextColor.GREEN));
                        arena.addPlayer(p);
                    }
                } else {
                    p.sendMessage(Component.text("Invalid arena.",NamedTextColor.RED));
                }
            } else if (args.length == 1  && args[0].equalsIgnoreCase("kit")){
                Arena arena = lms.getArenaManager().getArena(p);
                if(arena != null) {
                    if (arena.getState() != GameState.LIVE) {
                        new KitUI(p);
                    } else {
                        //ta a jogar logo n pode selecionar o kit :D
                    }
                } else {
                    //do nothing
                }
            }
            else {
                p.sendMessage(Component.text("Invalid usage!", NamedTextColor.RED));
            }


        }

        return false;
    }
}
