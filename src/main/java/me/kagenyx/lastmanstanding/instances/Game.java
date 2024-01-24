package me.kagenyx.lastmanstanding.instances;

import me.kagenyx.lastmanstanding.GameState;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID,Integer> points;
    public Game(Arena arena){

    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(Component.text("GAME HAS STARTED!", NamedTextColor.GREEN));

        for (UUID uuid : arena.getKits().keySet()) {
            arena.getKits().get(uuid).onStart(Bukkit.getPlayer(uuid));
        }

        for (UUID uuid : arena.getPlayers()){
            points.put(uuid,0);
        }
    }

    public void addPoint(Player player) {
        int playerPoints = points.get(player.getUniqueId()) + 1;
        if (playerPoints == 20) {
            arena.sendMessage(Component.text("Ganhou"));
            arena.reset(true);
            return;
        }

        player.sendMessage(Component.text("Ganhaste um ponto"));
        points.replace(player.getUniqueId(),playerPoints);
    }
}
