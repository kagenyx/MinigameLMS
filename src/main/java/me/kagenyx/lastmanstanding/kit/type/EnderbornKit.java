package me.kagenyx.lastmanstanding.kit.type;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class EnderbornKit extends Kit {
    private String texture = "c09f1de6135f4bea781c5a8e0d61095f833ee2685d8154ecea814ee6d328a5c6";

    public EnderbornKit(LastManStanding lms, UUID uuid) {
        super(lms, KitType.VIKING, uuid);
    }

    @Override
    public void onStart(Player player) {
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
}
