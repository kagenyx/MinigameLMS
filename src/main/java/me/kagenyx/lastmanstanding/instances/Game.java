package me.kagenyx.lastmanstanding.instances;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.team.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    private Arena arena;
    private HashMap<UUID,Integer> points;
    private HashMap<UUID, Team> teams;
    private HashMap<UUID, Team> dead;
    public Game(Arena arena){
        this.arena = arena;
        this.points = new HashMap<>();
        this.teams = arena.getTeams();
        this.dead = new HashMap<>();
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

        //player.sendMessage(Component.text("Ganhaste um ponto"));
        points.replace(player.getUniqueId(),playerPoints);
    }

    public void playerDied(Player p) {
        this.dead.put(p.getUniqueId(),teams.get(p));
        p.setGameMode(GameMode.SPECTATOR);
        checkTeams(teams.get(p));
        checkAlive();
    }

    private void checkTeams(Team t) {
        if (dead.values().size() == 2 && dead.values().contains(t)) {
            arena.sendMessage(Component.text("Oh shit! Team "
                    + t.getDisplay().content() + " just got eliminated!").color(NamedTextColor.RED));
        }
    }

    private void checkAlive() {
        if (teams.size() - 2 == dead.size()) {
            //Caso a equipa seja a mesma!
            if(getDifferenceValues(teams,dead).toArray()[0].equals(getDifferenceValues(teams,dead).toArray()[0])) {
                arena.sendMessage(Component.text("Oh shit! Team "
                        + ((Team)(getDifferenceValues(teams,dead).toArray()[0])).getDisplay().content() + " just won!!").color(NamedTextColor.GREEN));
                arena.reset(true);
            } else {
                arena.sendMessage(Component.text("Holy schnitzel! Only 2 players left!").color(NamedTextColor.RED));
            }

        }
    }

    private <K, V> Set<V> getDifferenceValues(Map<K, V> map1, Map<K, V> map2) {
        Set<K> differenceKeys = new HashSet<>(map1.keySet());
        differenceKeys.removeAll(map2.keySet());

        Set<V> differenceValues = new HashSet<>();
        for (K key : differenceKeys) {
            differenceValues.add(map1.get(key));
        }

        return differenceValues;
    }
}
