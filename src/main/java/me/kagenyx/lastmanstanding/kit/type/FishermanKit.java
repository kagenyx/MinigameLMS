package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class FishermanKit extends Kit {

    public FishermanKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.FISHERMAN, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,50000000,2));
        player.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
    }

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        if(uuid.equals(event.getPlayer().getUniqueId())){
            if (!(event.getCaught() instanceof Item)) {
                return;
            }

            Item item = (Item) event.getCaught();

            ItemStack itemStack = item.getItemStack();
            if (!itemStack.hasItemMeta()) {
                return;
            }
            itemStack.setType(Material.COBWEB);
            ItemMeta itemMeta = itemStack.getItemMeta();

        }
    }
}
