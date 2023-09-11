package me.kagenyx.lastmanstanding.listener;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {

    LastManStanding lms;

    public ConnectListener(LastManStanding lms) {
        this.lms = lms;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(ConfigManager.getLobby());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Arena arena = this.lms.getArenaManager().getArena(e.getPlayer());
        if (arena != null) {
            arena.removePlayer(e.getPlayer());
        }
    }
}
