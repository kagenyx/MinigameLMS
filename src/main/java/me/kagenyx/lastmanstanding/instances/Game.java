package me.kagenyx.lastmanstanding.instances;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.team.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID,Integer> points;
    private HashMap<UUID, Team> teams;
    private LinkedList<UUID> dead;
    public Game(Arena arena){
        this.arena = arena;
        this.points = new HashMap<>();
        this.teams = arena.getTeams();
        this.dead = new LinkedList<>();
    }

    public void start() {
        arena.setState(GameState.LIVE);
        arena.sendMessage(Component.text("GAME HAS STARTED!", NamedTextColor.GREEN));

        for (UUID uuid : arena.getKits().keySet()) {
            arena.getKits().get(uuid).onStart(Bukkit.getPlayer(uuid));
        }

        for (UUID uuid : arena.getPlayers()){
            points.put(uuid,0);
            Bukkit.getPlayer(uuid).closeInventory();
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

    public void playerDied(Player p) {
        this.dead.add(p.getUniqueId());
        p.setGameMode(GameMode.SPECTATOR);
        checkTeams(p);
    }

    private void checkTeams(Player p) {

    }
}
