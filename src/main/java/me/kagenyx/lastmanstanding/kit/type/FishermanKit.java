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
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;

public class FishermanKit extends Kit {


    private  LinkedList<Material> ll = new LinkedList<>();
    private Material[] m = {Material.COD,Material.COOKED_COD,Material.SALMON,Material.COOKED_SALMON};
    public FishermanKit(LastManStanding lms, UUID uuid) {

        super(lms, KitType.FISHERMAN, uuid);
        ll.addAll(Arrays.asList(m));
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

            Random r = new Random();
            int a = r.nextInt(60);
            Item item = (Item) event.getCaught();
            ItemStack itemStack = item.getItemStack();

            if(a == 45) {
                int property = r.nextInt(3);
                switch (property) {
                    case 0:
                        itemStack.setType(Material.DIAMOND_LEGGINGS);
                        break;
                    case 1:
                        itemStack.setType(Material.DIAMOND_CHESTPLATE);
                        break;
                    case 2:
                        itemStack.setType(Material.DIAMOND_HELMET);
                        break;
                    default:
                        itemStack.setType(Material.DIAMOND_BOOTS);
                }
            }

            if(a == 35) {
                int property = r.nextInt(3);
                switch (property) {
                    case 0:
                        itemStack.setType(Material.NETHERITE_SWORD);
                        break;
                    case 1:
                        itemStack.setType(Material.NETHERITE_PICKAXE);
                        break;
                    case 2:
                        itemStack.setType(Material.ELYTRA);
                        break;
                    default:
                        itemStack.setType(Material.WOLF_SPAWN_EGG);
                        itemStack.setAmount(5);
                }
            }

            if(a == 25) {
                itemStack.setType(Material.IRON_SWORD);
                ItemMeta im = itemStack.getItemMeta();
                im.addEnchant(Enchantment.DAMAGE_ALL,3,true);
                itemStack.setItemMeta(im);
                itemStack.setAmount(2);
            }

            if(a == 55) {
                itemStack.setType(Material.BOW);
                ItemMeta im = itemStack.getItemMeta();
                im.addEnchant(Enchantment.ARROW_DAMAGE,3,true);
                im.addEnchant(Enchantment.ARROW_FIRE,1,true);
                itemStack.setItemMeta(im);
                itemStack.setAmount(2);
            }

            if(a == 5) {
                itemStack.setType(Material.TRIDENT);
                ItemMeta im = itemStack.getItemMeta();
                im.addEnchant(Enchantment.IMPALING,5,true);
                im.addEnchant(Enchantment.LOYALTY,3,true);
                itemStack.setItemMeta(im);
                itemStack.setAmount(2);
            }
        }
    }

    @EventHandler
    public void onFishEat(PlayerItemConsumeEvent e) {
        if(e.getPlayer().getUniqueId().equals(uuid)){
            if(ll.contains(e.getItem())){
                int cooked = ll.indexOf(e.getItem());
                if(cooked % 2 == 0) {
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,300,0));
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,800,0));
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,260,0));
                } else {
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,400,1));
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,900,1));
                    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,180,1));
                }
            }
        }
    }
}
