package me.kagenyx.lastmanstanding.kit;

import me.kagenyx.lastmanstanding.LastManStanding;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class Kit implements Listener {

    protected KitType type;
    protected UUID uuid;

    public Kit(LastManStanding lms, KitType type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;

        Bukkit.getPluginManager().registerEvents(this,lms);
    }

    public KitType getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public abstract void onStart(Player player);

    public void remove() {
        HandlerList.unregisterAll(this);
    }
}
