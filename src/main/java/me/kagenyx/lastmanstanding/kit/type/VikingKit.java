package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.UUID;

public class VikingKit extends Kit {

    Material[] meat = {Material.COOKED_BEEF,Material.COOKED_PORKCHOP,Material.COOKED_COD,
            Material.COOKED_CHICKEN, Material.COOKED_RABBIT,Material.COOKED_SALMON,
            Material.BEEF,Material.CHICKEN,Material.SALMON,Material.PORKCHOP,Material.RABBIT,Material.COD,Material.MUTTON, Material.COOKED_MUTTON};
    private LinkedList<Material> meat_ll;

    public VikingKit(LastManStanding lms, UUID uuid) {

        super(lms,KitType.VIKING, uuid);
        LinkedList<Material> meat_ll = new LinkedList<>();
        this.meat_ll.addAll(Arrays.asList(meat));
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(axeMaker());
        player.getInventory().addItem(helmetMaker());
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF,32));

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        System.out.println("Caralho");
    }

    @EventHandler
    public void playerInteractAir(PlayerInteractEvent e) {
        if(uuid.equals(e.getPlayer().getUniqueId())){
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                EquipmentSlot hand = e.getHand();
                ItemStack handItem = null;
                if (hand == EquipmentSlot.HAND) {
                    handItem = e.getPlayer().getInventory().getItemInMainHand();
                } else if (hand == EquipmentSlot.OFF_HAND) {
                    handItem = e.getPlayer().getInventory().getItemInOffHand();
                }
                if (handItem.getType() == Material.SHIELD) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onArrowDamage(PlayerItemConsumeEvent e) {
        if (!(this.meat_ll.contains(e.getItem().getType()))){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onArrowDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager().getType() == EntityType.ARROW){
            if(e.getEntity() instanceof Player){
                if(uuid.equals(e.getEntity().getUniqueId())){
                    e.setDamage(e.getDamage()*1.3);
                }
            }
        }
    }

    private ItemStack axeMaker() {
        AttributeModifier am = new AttributeModifier(UUID.randomUUID(),"generic.attack_speed",0.35,AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemMeta axemeta = axe.getItemMeta();
        axemeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,am);
        axemeta.addEnchant(Enchantment.DURABILITY,10,true);
        axemeta.addEnchant(Enchantment.FIRE_ASPECT,1,true);
        axe.setItemMeta(axemeta);

        return axe;
    }

    private ItemStack helmetMaker() {
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta helmetmeta = helmet.getItemMeta();
        helmetmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,5,true);
        helmetmeta.addEnchant(Enchantment.DURABILITY,4,true);
        helmet.setItemMeta(helmetmeta);

        return helmet;
    }
}
