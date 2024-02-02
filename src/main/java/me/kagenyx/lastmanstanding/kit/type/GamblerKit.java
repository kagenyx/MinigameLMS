package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import sun.jvm.hotspot.opto.Block;

import java.util.Random;
import java.util.UUID;

public class GamblerKit extends Kit {

    private ItemStack[] legendary = {new ItemStack(Material.DIAMOND_CHESTPLATE,1), new ItemStack(Material.NETHERITE_SWORD,1),
            new ItemStack(Material.ELYTRA,1), new ItemStack(Material.DIAMOND_LEGGINGS,1), new ItemStack(Material.NETHERITE_HELMET,1)};

    private ItemStack[] regular = {new ItemStack(Material.DIAMOND_SWORD,1),
            new ItemStack(Material.CROSSBOW,1),new ItemStack(Material.IRON_AXE,1),new ItemStack(Material.IRON_SWORD,1),
            new ItemStack(Material.WOLF_SPAWN_EGG,5),
            new ItemStack(Material.POTION,1),new ItemStack(Material.BOW,1),new ItemStack(Material.VILLAGER_SPAWN_EGG,2),
            new ItemStack(Material.EMERALD,64),new ItemStack(Material.EXPERIENCE_BOTTLE,64),
            new ItemStack(Material.ENCHANTING_TABLE,1)};

    private ItemStack[] low = {new ItemStack(Material.STICK,64),new ItemStack(Material.BEEF,32),
            new ItemStack(Material.COOKED_BEEF,16),
            new ItemStack(Material.OAK_WOOD,128),
            new ItemStack(Material.OAK_DOOR,6),
            new ItemStack(Material.BOOK,100),
            new ItemStack(Material.BOOKSHELF,15),
            new ItemStack(Material.TALL_GRASS,200),
            new ItemStack(Material.STRIDER_SPAWN_EGG,2),
            new ItemStack(Material.LEATHER_BOOTS,5),
            new ItemStack(Material.DRIED_KELP,64)};

    public GamblerKit(LastManStanding lms, KitType type, UUID uuid) {
        super(lms, KitType.GAMBLER, uuid);
    }

    @Override
    public void onStart(Player player) {
        randomize(player);
    }

    private void randomize(Player player) {
        Random r = new Random();

        for(ItemStack is : legendary) {
            if(r.nextInt(100) < 15) {
                player.getInventory().addItem(is);
            }
        }

        for(ItemStack is : regular) {
            if(r.nextInt(100) < 30) {
                if(is.getType() == Material.POTION) {
                    ItemStack potion = new ItemStack(Material.POTION,2);
                    PotionMeta pm = (PotionMeta) potion.getItemMeta();
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 2400, 1), true);
                    potion.setItemMeta(pm);
                } else {
                    player.getInventory().addItem(is);
                }
            }
        }

        for(ItemStack is : low) {
            if(r.nextInt(100) < 55) {
                player.getInventory().addItem(is);
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Random r = new Random();
        if(uuid.equals(e.getPlayer().getUniqueId())){
            if(e.getBlock().getType() == Material.DIAMOND_ORE) {
                if(r.nextInt(100) < 50) {
                    Bukkit.getWorld("world").dropItem(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND,1));
                }
                if(r.nextInt(100) < 10) {
                    Bukkit.getWorld("world").spawnEntity(e.getBlock().getLocation(), EntityType.CREEPER);
                }
            }
            if(e.getBlock().getType() == Material.IRON_ORE) {
                if(r.nextInt(100) < 50) {
                    Bukkit.getWorld("world").dropItem(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT,1));
                }
                if(r.nextInt(100) < 6) {
                    Bukkit.getWorld("world").spawnEntity(e.getBlock().getLocation(), EntityType.CREEPER);
                }
            }
            if(e.getBlock().getType() == Material.GOLD_ORE){
                if(r.nextInt(100) < 50) {
                    Bukkit.getWorld("world").dropItem(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT,1));
                }
                if(r.nextInt(100) < 15) {
                    Bukkit.getWorld("world").spawnEntity(e.getBlock().getLocation(), EntityType.CREEPER);
                }
            }
        }
    }


}
