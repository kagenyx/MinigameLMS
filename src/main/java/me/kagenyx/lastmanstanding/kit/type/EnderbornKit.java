package me.kagenyx.lastmanstanding.kit.type;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
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

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        System.out.println("Caralho");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) {
            return; // Not interacting with a player
        }

        Player player = event.getPlayer();

        // Check if the player used an ender pearl
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.ENDER_PEARL) {
            // Player threw an ender pearl
            // Now you can do something with the player
            player.sendMessage("You threw an ender pearl!");

            // Additional code here...
        }
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
                System.out.println("Item: " + damager.getInventory().getItemInMainHand().toString());
                if(damager.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
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

    private Location randomTeleport(int radius, Player p) {
        Random rnd = new Random();
        int x,z;

        x = rnd.nextInt(radius) + 3;
        z = rnd.nextInt(radius) + 3;

        if(rnd.nextBoolean()) {
            x = x*(-1);
        }

        if(rnd.nextBoolean()){
            z = z*(-1);
        }

        Location pLoc = p.getLocation();
        System.out.println("I will check for the coords: x:" + (pLoc.getX()+x) + ", z: " + (pLoc.getZ()+z));
        while (!(Bukkit.getWorld("world").getBlockAt((int) (pLoc.getX() + x), (int) pLoc.getY(), (int) (pLoc.getZ() + z)).getType() == Material.AIR &&
                Bukkit.getWorld("world").getBlockAt((int) (pLoc.getX() + x), (int) pLoc.getY() + 1, (int) (pLoc.getZ() + z)).getType() == Material.AIR)) {
            x = rnd.nextInt(radius) + 3;
            z = rnd.nextInt(radius) + 3;
            System.out.println("I will check for the coords: x:" + (pLoc.getX()+x) + ", z: " + (pLoc.getZ()+z));
            if(rnd.nextBoolean()) {
                x = x*(-1);
            }

            if(rnd.nextBoolean()){
                z = z*(-1);
            }
        }
        System.out.println("I will tp: x:" + (pLoc.getX()+x) + ", z: " + (pLoc.getZ()+z));
        return new Location(Bukkit.getWorld("world"),pLoc.getX() + x, pLoc.getY(),pLoc.getZ() + z);
    }
}
