package me.kagenyx.lastmanstanding.kit.type;

import me.kagenyx.lastmanstanding.LastManStanding;
import me.kagenyx.lastmanstanding.kit.Kit;
import me.kagenyx.lastmanstanding.kit.KitType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class SamuraiKit extends Kit {
    public SamuraiKit(LastManStanding lms, KitType type, UUID uuid) {
        super(lms, KitType.SAMURAI, uuid);
    }

    @Override
    public void onStart(Player player) {
        addItemToPlayer(createKatana(),player);
        addItemToPlayer(createArrow(),player);
        addItemToPlayer(createHelmet(),player);
        addItemToPlayer(createChestplate(),player);
        addItemToPlayer(createLeggings(),player);
        addItemToPlayer(createBoots(),player);

    }

    private void addItemToPlayer(ItemStack item, Player player) {
        player.getInventory().addItem(item);
    }

    private ItemStack createKatana() {
        ItemStack katana = new ItemStack(Material.IRON_SWORD);
        ItemMeta katana_meta = katana.getItemMeta();
        katana_meta.setCustomModelData(99);
        katana_meta.addEnchant(Enchantment.DAMAGE_ALL,2,true);
        katana_meta.addEnchant(Enchantment.DURABILITY,10,true);
        katana_meta.displayName(Component.text("Ikutachi").color(TextColor.fromHexString("#66000")));
        katana_meta.lore(Arrays.asList(Component.text("The true power of Ikutachi can only be harnessed alongside Ikuyumiya.")));
        katana.setItemMeta(katana_meta);

        return katana;
    }

    private ItemStack createArrow() {
        ItemStack arrow = new ItemStack(Material.BOW);
        ItemMeta arr_meta = arrow.getItemMeta();
        arr_meta.addEnchant(Enchantment.ARROW_DAMAGE,2,true);
        arr_meta.addEnchant(Enchantment.ARROW_INFINITE,1,true);
        arr_meta.addEnchant(Enchantment.DURABILITY,10,true);
        arr_meta.lore(Arrays.asList(Component.text("The true power of Ikuyumiya can only be harnessed alongside Ikutachi.")));
        arr_meta.displayName(Component.text("Ikuyumiya").color(TextColor.fromHexString("#66000")));
        arrow.setItemMeta(arr_meta);

        return arrow;
    }

    private ItemStack createHelmet() {
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta helmet_meta = helmet.getItemMeta();
        helmet_meta.addEnchant(Enchantment.THORNS,3,true);
        helmet_meta.addEnchant(Enchantment.DURABILITY,10,true);
        helmet_meta.displayName(Component.text("Kabuto").color(TextColor.fromHexString("#66000")));
        helmet.setItemMeta(helmet_meta);

        return helmet;
    }

    private ItemStack createChestplate() {
        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta chestplate_meta = chestplate.getItemMeta();
        chestplate_meta.addEnchant(Enchantment.THORNS,3,true);
        chestplate_meta.addEnchant(Enchantment.DURABILITY,10,true);
        chestplate_meta.displayName(Component.text("Kabuto").color(TextColor.fromHexString("#66000")));
        chestplate.setItemMeta(chestplate_meta);

        return chestplate;
    }

    private ItemStack createLeggings() {
        ItemStack leggings = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta leggings_meta = leggings.getItemMeta();
        leggings_meta.addEnchant(Enchantment.THORNS,3,true);
        leggings_meta.addEnchant(Enchantment.DURABILITY,10,true);
        leggings_meta.displayName(Component.text("Kabuto").color(TextColor.fromHexString("#66000")));
        leggings.setItemMeta(leggings_meta);

        return leggings;
    }

    private ItemStack createBoots() {
        ItemStack boots = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta boots_meta = boots.getItemMeta();
        boots_meta.addEnchant(Enchantment.THORNS,3,true);
        boots_meta.addEnchant(Enchantment.DURABILITY,10,true);
        boots_meta.displayName(Component.text("Kabuto").color(TextColor.fromHexString("#66000")));
        boots.setItemMeta(boots_meta);

        return boots;
    }
}
