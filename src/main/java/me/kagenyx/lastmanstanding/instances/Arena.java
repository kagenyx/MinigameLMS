package me.kagenyx.lastmanstanding.instances;


import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private int id;
    private Location spawn;
    private int WorldBorder;

    private GameState state;
    private List<UUID> players;
    private Countdown countdown;

    public void setId(int id) {
        this.id = id;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public void setWorldBorder(int worldBorder) {
        WorldBorder = worldBorder;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setPlayers(List<UUID> players) {
        this.players = players;
    }

    public Arena(LastManStanding lms, int id, Location spawn){
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.countdown = new Countdown(lms, this);
    }

    public void sendMessage(Component msg){
        for(UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendMessage(msg);
        }
    }

    public void sendTitle(String title, String subtitle){
        for(UUID uuid : players) {
            Bukkit.getPlayer(uuid).sendTitle(title,subtitle);
        }
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
