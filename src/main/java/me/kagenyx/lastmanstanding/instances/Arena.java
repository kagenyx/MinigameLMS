package me.kagenyx.lastmanstanding.instances;


import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import me.kagenyx.lastmanstanding.kit.type.Agent47Kit;
import me.kagenyx.lastmanstanding.kit.type.EnderbornKit;
import me.kagenyx.lastmanstanding.kit.type.VikingKit;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private LastManStanding lms;

    private int id;
    private Location spawn;
    private int WorldBorder;

    private GameState state;
    private List<UUID> players;
    private HashMap<UUID,Kit> kits;
    private Countdown countdown;
    private Game game;

    public Arena(LastManStanding lms, int id, Location spawn){
        this.lms = lms;
        this.id = id;
        this.spawn = spawn;

        this.state = GameState.RECRUITING;
        this.players = new ArrayList<>();
        this.kits = new HashMap<UUID, Kit>();
        this.countdown = new Countdown(lms, this);
        this.game = new Game(this);
    }

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
        if(state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getReqPlayers()) {
            countdown.start();
        }
    }

    public void removePlayer(Player p) {
        players.remove(p.getUniqueId());
        p.teleport(ConfigManager.getLobby());

        removeKit(p.getUniqueId());
        if(this.state == GameState.COUNTDOWN && players.size() < ConfigManager.getReqPlayers()){
            sendMessage(Component.text("Acabou viado"));
            reset(false);
            return;
        }

        if(this.state == GameState.LIVE && players.size() < ConfigManager.getReqPlayers()){
            sendMessage(Component.text("Acabou viado"));
            reset(true);
        }
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

    public HashMap<UUID, Kit> getKits() {
        return kits;
    }

    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers) {
        if(kickPlayers) {
            Location loc = ConfigManager.getLobby();
            for (UUID uuid : players){
                //dumb?? but its in tutorial ig lmao
                Bukkit.getPlayer(uuid).teleport(loc);
                removeKit(Bukkit.getPlayer(uuid).getUniqueId());
                Bukkit.getPlayer(uuid).clearActivePotionEffects();
                Bukkit.getPlayer(uuid).getInventory().clear();
            }
            players.clear();
        }
        kits.clear();
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(lms,this);
        game = new Game(this);
    }

    public Game getGame() {return this.game;}

    public void removeKit(UUID uuid) {
        if (kits.containsKey(uuid)) {
            kits.get(uuid).remove();
            kits.remove(uuid);
        }
    }

    public void setKit(UUID uuid, KitType type) {
        removeKit(uuid);
        if (type == KitType.ENDERBORN) {
            kits.put(uuid, new EnderbornKit(lms,uuid));
        } else if (type == KitType.VIKING) {
            kits.put(uuid, new VikingKit(lms,uuid));
        } else if (type == KitType.AGENT47) {
            kits.put(uuid, new Agent47Kit(lms,uuid));
        }
    }

    public KitType getKitType (Player player) {
        return kits.containsKey(player.getUniqueId()) ? kits.get(player.getUniqueId()).getType() : null;
    }
}
