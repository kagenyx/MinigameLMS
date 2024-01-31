package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DreadwalkerKit extends Kit {

    public DreadwalkerKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.DREADWALKER, uuid);
    }

    @Override
    public void onStart(Player player) {

    }
}
