package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NoobKit extends Kit {
    public NoobKit(LastManStanding lms, KitType type, UUID uuid) {
        super(lms, KitType.NOOB, uuid);
    }

    @Override
    public void onStart(Player player) {

    }
}
