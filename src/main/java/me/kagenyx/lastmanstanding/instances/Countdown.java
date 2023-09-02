package me.kagenyx.lastmanstanding.instances;

import me.kagenyx.lastmanstanding.GameState;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private LastManStanding lms;
    private Arena arena;
    private int cdsecs;

    public Countdown(LastManStanding lms, Arena arena) {
        this.lms = lms;
        this.arena = arena;
        this.cdsecs = ConfigManager.getCdSeconds();
    }

    public void start(){
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(lms,0,20);
    }

    @Override
    public void run() {
        if(this.cdsecs == 0) {
            cancel();
            arena.start();
            return;
        }

        if((cdsecs <= 10 && cdsecs % 2 == 0) || cdsecs <= 3 || cdsecs % 15 == 0) {
            arena.sendMessage(Component.text("Game will start in " + cdsecs + "second" + (cdsecs == 1 ? "." : "s."), NamedTextColor.GREEN));
        }
        this.cdsecs--;
    }
}
