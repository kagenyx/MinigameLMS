package me.kagenyx.lastmanstanding.kit.type;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

public class EnderbornKit extends Kit {
    private String texture = "c09f1de6135f4bea781c5a8e0d61095f833ee2685d8154ecea814ee6d328a5c6";

    public EnderbornKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.VIKING, uuid);
    }

    @Override
    public void onStart(Player player) {
        //ENDERMAN HEAD
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        PlayerProfile profile = Bukkit.getServer().createProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();
        try {
            textures.setSkin(new URL("http://textures.minecraft.net/texture/" + texture));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        profile.setTextures(textures);
        meta.setOwnerProfile(profile);
        head.setItemMeta(meta);

        //head.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL,4);

        player.getInventory().addItem(head);

        //ENDER EYE
        ItemStack eye = new ItemStack(Material.ENDER_EYE);
        ItemMeta eyemeta = eye.getItemMeta();
        eyemeta.displayName(Component.text("The End", TextColor.fromCSSHexString("#b27fb2")));
        eyemeta.setLocalizedName("OLACRL");
        eye.setItemMeta(eyemeta);
        player.getInventory().addItem(eye);

        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
        player.getInventory().addItem(new ItemStack(Material.CHORUS_FRUIT,16));

        //Chestplate
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplate_meta = (LeatherArmorMeta) chestplate.getItemMeta();
        chestplate_meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,5,true);
        chestplate_meta.addEnchant(Enchantment.DURABILITY,10,true);
        chestplate_meta.setColor(Color.BLACK);
        chestplate.setItemMeta(chestplate_meta);

        player.getInventory().addItem(chestplate);
    }

    @EventHandler
    public void onDebugEnderPearl(PlayerTeleportEvent e) {

        if(uuid.equals(e.getPlayer().getUniqueId())){
            if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
                e.setCancelled(true);
                if(!e.getPlayer().getInventory().contains(Material.ENDER_PEARL)) {
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL));

                }
                e.getPlayer().teleport(e.getTo());
            }
        }

    }

    @EventHandler
    public void hitWithEnderEye(EntityDamageByEntityEvent e) {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player damaged = (Player) e.getEntity();

            if(uuid.equals(damager.getUniqueId())) {
                ItemStack eye = new ItemStack(Material.ENDER_EYE);
                ItemMeta eyemeta = eye.getItemMeta();
                eyemeta.displayName(Component.text("The End", TextColor.fromCSSHexString("#b27fb2")));
                eyemeta.setLocalizedName("OLACRL");
                eye.setItemMeta(eyemeta);
                if(damager.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
                    Particle.DustOptions dustOp = new Particle.DustOptions(Color.fromRGB(255,102,255),20.0F);
                    Bukkit.getWorld("world").spawnParticle(Particle.REDSTONE,damaged.getLocation(),50,1, 1, 1,dustOp);
                    damaged.teleport(randomTeleport(4,damaged));
                }
            }
        }
    }

    @EventHandler
    public void enderbornOnWater (PlayerMoveEvent e) {
        if(uuid.equals(e.getPlayer().getUniqueId()) && Bukkit.getWorld("world").getBlockAt(e.getPlayer().getLocation()).isLiquid()){
            e.getPlayer().damage(1.0);
        }
    }

    @EventHandler
    public void chorusFruit (PlayerItemConsumeEvent e) {
        if(uuid.equals(e.getPlayer().getUniqueId()) && e.getItem().getType() == Material.CHORUS_FRUIT) {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,200,2));
        }
    }

    private Location randomTeleport(int radius, Player p) {
        Random rnd = new Random();
        int x,z;

        x = rnd.nextInt(radius) + 2;
        z = rnd.nextInt(radius) + 2;

        if(rnd.nextBoolean()) {
            x = x*(-1);
        }

        if(rnd.nextBoolean()){
            z = z*(-1);
        }

        Location pLoc = p.getLocation();
        int player_x = (int) pLoc.getX();
        int player_y = (int) pLoc.getY();
        int player_z = (int) pLoc.getZ();

        while (!(Bukkit.getWorld("world").getBlockAt( (player_x + x), player_y, (player_z + z)).getType() == Material.AIR &&
                Bukkit.getWorld("world").getBlockAt( (player_x + x), player_y + 1,  (player_z + z)).getType() == Material.AIR)) {
            player_y++;
        }
        System.out.println("pX" + player_x + ". x:" + x);
        System.out.println("pZ" + player_z + ". z:" + z);
        System.out.println("I will tp: x:" + (player_x+x) + ", y:" + player_y + ", z: " + (player_z+z));
        return new Location(Bukkit.getWorld("world"),player_x + x, player_y,player_z + z);
    }
}