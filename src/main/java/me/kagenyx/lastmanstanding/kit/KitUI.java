package me.kagenyx.lastmanstanding.kit;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class KitUI {

    public KitUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 18, NamedTextColor.BLUE + "Olá");

        for (KitType type : KitType.values()) {
            ItemStack is = new ItemStack(type.getMaterial());
            ItemMeta isMeta = is.getItemMeta();
            isMeta.displayName(type.getDisplay());
            isMeta.lore(Arrays.asList(type.getDesc()));
            isMeta.setLocalizedName(type.name());
            is.setItemMeta(isMeta);

            gui.addItem(is);
        }

        player.openInventory(gui);

    }
}
