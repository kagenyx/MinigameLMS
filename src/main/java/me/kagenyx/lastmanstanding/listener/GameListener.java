package me.kagenyx.lastmanstanding.listener;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.instances.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class GameListener implements Listener {
    private LastManStanding lms;

    public GameListener(LastManStanding lms) {
        this.lms = lms;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Arena arena = this.lms.getArenaManager().getArena(e.getPlayer());
        if (arena != null) {
            if(arena.getState().equals(GameState.LIVE)){
                arena.getGame().addPoint(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void killPlayer(EntityDeathEvent e){
        Arena arena = this.lms.getArenaManager().getArena(e.getEntity().getKiller());

    }
}
