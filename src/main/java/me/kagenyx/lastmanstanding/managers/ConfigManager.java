package me.kagenyx.lastmanstanding.managers;

import me.kagenyx.lastmanstanding.LastManStanding;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    private static FileConfiguration config;

    public static void setupConfig(LastManStanding lms) {
        ConfigManager.config = lms.getConfig();
        lms.saveDefaultConfig();
    }

    public static int getReqPlayers(){
        return config.getInt("required-players");
    }

    public static int getCdSeconds(){
        return config.getInt("cooldown-seconds");
    }

    public static Location getLobby() {
        return new Location(Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"),
                config.getDouble("lobby-spawn.z"),
                (float) config.getDouble("lobby-spawn.yaw"),
                (float) config.getDouble("lobby-spawn.pitch"));
    }
}
