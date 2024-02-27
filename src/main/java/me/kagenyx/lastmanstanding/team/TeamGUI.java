package me.kagenyx.lastmanstanding.team;

import me.kagenyx.lastmanstanding.instances.Arena;
import me.kagenyx.lastmanstanding.kit.KitType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TeamGUI {

    public static final int TEAM_MAX_SIZE = 2;

    public TeamGUI(Player p, Arena arena) {
        Inventory gui = Bukkit.createInventory(null, 18, Component.text("Team Selection").color(TextColor.fromHexString("#1b5394")));

        for (Team team : Team.values()) {
            int count = arena.getTeamCount(team);
            switch(count){
                case '0':
                    ItemStack wool = designWool(new ItemStack(Material.GREEN_WOOL),count,team);
                    gui.addItem(wool);
                    break;
                case TEAM_MAX_SIZE:
                    ItemStack wool2 = designWool(new ItemStack(Material.RED_WOOL),count,team);
                    gui.addItem(wool2);
                    break;
                default:
                    ItemStack wool3 = designWool(new ItemStack(Material.YELLOW_WOOL),count,team);
                    gui.addItem(wool3);
            }
        }

        p.openInventory(gui);

    }

    private ItemStack designWool(ItemStack wool,int size, Team team){
        ItemMeta im = wool.getItemMeta();
        im.displayName(Component.text("Team " + team.getDisplay()
                +". (" + size + "/2)"));
        im.setLocalizedName(team.name());
        wool.setItemMeta(im);
        return wool;
    }
}
