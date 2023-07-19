package me.kagenyx.lastmanstanding.instances;


import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private int id;
    private Location spawn;
    private int WorldBorder;

    private GameState state;
    private List<UUID> players;

    public Arena(int id, Location spawn){
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player p) {
        players.add(p.getUniqueId());
        p.teleport(this.spawn);
    }

    public void removePlayer(Player p) {
        players.remove(p.getUniqueId());
        p.teleport(ConfigManager.getLobby());
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }
}
