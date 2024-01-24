package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class EnderbornKit extends Kit {


    public EnderbornKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.VIKING, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        System.out.println("Caralho");
    }
}
