package me.kagenyx.lastmanstanding.listener;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.instances.Game;
import me.kagenyx.lastmanstanding.kit.KitType;
import me.kagenyx.lastmanstanding.team.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
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
            e.setCancelled(true);
            KitType type = KitType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());

            Arena arena = this.lms.getArenaManager().getArena(p);
            if (arena != null) {
                KitType act = arena.getKitType(p);
                if (act != null && act == type) {
                    //n é possível caguei no send message tbh :(
                } else {
                    p.sendMessage(Component.text("You've chosen " + type.getDisplay()));
                    arena.setKit(p.getUniqueId(),type);
                }
                p.closeInventory();
            }
        }

        if (e.getView().getTitle().contains("Team Selection") && e.getInventory() != null && e.getCurrentItem() != null) {
            e.setCancelled(true);
            Team team = Team.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());

            Arena arena = this.lms.getArenaManager().getArena(p);
            if (arena != null) {
                KitType act = arena.getKitType(p);
                if (act != null && arena.getTeam(p) == team) {
                    //n é possível caguei no send message tbh :(
                } else {
                    if(e.getCurrentItem().getType() == Material.RED_WOOL) {
                        p.sendMessage(Component.text("That team is full!", NamedTextColor.RED));

                    } else {
                        p.sendMessage(Component.text("You've chosen " + team.getDisplay()));
                        arena.setTeam(p,team);
                    }
                }
                p.closeInventory();
            }
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
        if(e.getEntity().getKiller() instanceof Player) {
            Arena arena = this.lms.getArenaManager().getArena(e.getEntity().getKiller());
            if (arena != null) {
                Game game = arena.getGame();
                game.playerDied(e.getEntity().getKiller());
            }
        }
    }
}
