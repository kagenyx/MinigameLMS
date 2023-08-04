package me.kagenyx.lastmanstanding.managers;

import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.LastManStanding;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new LinkedList<>();

    public ArenaManager(LastManStanding lms){
        FileConfiguration config = lms.getConfig();
        for(String str : config.getConfigurationSection("arenas.").getKeys(false)){
            arenas.add(new Arena(lms,Integer.parseInt(str), new Location(Bukkit.getWorld(config.getString("arenas." + str + "world")),
                    config.getDouble("arenas." + str + "x"),
                    config.getDouble("arenas." + str + "y"),
                    config.getDouble("arenas." + str + "z"),
                    (float) config.getDouble("arenas." + str + "yaw"),
                    (float) config.getDouble("arenas." + str + "pitch"))
                    ));
        }
    }

    public List<Arena> getArenas() { return this.arenas; }

    public Arena getArena(Player p) {
        for(Arena arena : arenas) {
            if(arena.getPlayers().contains(p.getUniqueId())) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(int id) {
        for(Arena arena : arenas) {
            if(arena.getId() == id) {
                return arena;
            }
        }
        return null;
    }

}
