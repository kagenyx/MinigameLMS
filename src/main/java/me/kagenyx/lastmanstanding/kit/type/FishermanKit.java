package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FishermanKit extends Kit {

    public FishermanKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.FISHERMAN, uuid);
    }

    @Override
    public void onStart(Player player) {

    }
}
