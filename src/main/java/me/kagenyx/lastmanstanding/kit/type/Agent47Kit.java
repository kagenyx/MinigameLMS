package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Agent47Kit extends Kit {


    public Agent47Kit(LastManStanding lms, UUID uuid) {
        super(lms,KitType.AGENT47, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        ItemStack pot = createPotInv();
        player.getInventory().addItem(pot,pot,pot);
        player.getInventory().addItem(new ItemStack(Material.TNT,32));
        player.getInventory().addItem(createSword());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        System.out.println("Caralho");
    }

    private ItemStack createPotInv(){
        ItemStack invPot = new ItemStack(Material.POTION);
        PotionMeta pm = (PotionMeta) invPot.getItemMeta();
        pm.addCustomEffect(new PotionEffect(PotionEffectType.INVISIBILITY,300,1),true);
        invPot.setItemMeta(pm);
        return invPot;
    }

    private ItemStack createSword(){
        ItemStack bow = new ItemStack(Material.STONE_SWORD);
        ItemMeta bm = bow.getItemMeta();
        bm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier("generic.attack_speed",2, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        bow.setItemMeta(bm);
        bow.addEnchantment(Enchantment.FIRE_ASPECT,2);
        bm.displayName(Component.text("47's Dagger", NamedTextColor.DARK_RED));
        return bow;
    }
}