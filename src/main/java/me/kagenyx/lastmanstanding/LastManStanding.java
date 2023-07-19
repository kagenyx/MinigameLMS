package me.kagenyx.lastmanstanding;

import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.managers.ArenaManager;
import me.kagenyx.lastmanstanding.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LastManStanding extends JavaPlugin {

    private ArenaManager am;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);

        am = new ArenaManager(this);
    }
    public ArenaManager getArenaManager() {
        return am;
    }
}
