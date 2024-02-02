package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.UUID;

public class BeastmasterKit extends Kit {

    public BeastmasterKit(LastManStanding lms, KitType type, UUID uuid) {
        super(lms, KitType.BEASTMASTER, uuid);
    }

    @Override
    public void onStart(Player player) {
        addItemToPlayer(createChestplate(),player);
        addItemToPlayer(new ItemStack(Material.WOLF_SPAWN_EGG,2),player);
        addItemToPlayer(new ItemStack(Material.BONE,32),player);

    }

    private void addItemToPlayer(ItemStack item, Player player) {
        player.getInventory().addItem(item);
    }

    private ItemStack createChestplate() {
        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta meta = chestplate.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY,7,true);
        meta.addEnchant(Enchantment.PROTECTION_PROJECTILE,6,true);
        chestplate.setItemMeta(meta);

        return chestplate;
    }

    @EventHandler
    public void onPetDeath() {

    }
}
