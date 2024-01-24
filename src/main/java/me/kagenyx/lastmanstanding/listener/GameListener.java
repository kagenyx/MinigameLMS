package me.kagenyx.lastmanstanding.listener;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GameListener implements Listener {
    private LastManStanding lms;

    public GameListener(LastManStanding lms) {
        this.lms = lms;
    }

    @EventHandler
    public void onClick (InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Kit Selection") && e.getInventory() != null && e.getCurrentItem() != null) {
            KitType type = KitType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());

        }
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
