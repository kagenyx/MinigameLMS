package me.kagenyx.lastmanstanding.kit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;

public enum KitType {
    ENDERBORN(NamedTextColor.DARK_PURPLE + "Enderborn",Material.ENDER_EYE,NamedTextColor.GRAY + "The End is coming…"),
    MAGE(NamedTextColor.LIGHT_PURPLE + "Mage",Material.ENCHANTED_BOOK,NamedTextColor.GRAY + "In the Arcane lies true power for those who seek it!"),
    BEASTMASTER(NamedTextColor.GREEN + "Beastmaster",Material.BONE,NamedTextColor.GRAY + "The spirit of the hunt lives through me!"),
    SAMURAI(Component.text("Samurai", TextColor.fromHexString("#e890c3")).toString(),Material.IRON_SWORD,NamedTextColor.GRAY + "May honor preserve us."),
    VIKING(NamedTextColor.DARK_GRAY+"Viking",Material.CHAINMAIL_HELMET,NamedTextColor.GRAY + "The gods shall protect us."),
    AGENT47(NamedTextColor.DARK_RED + "Agent 47",Material.POTION,NamedTextColor.GRAY+"Good job 47."),
    DREADWALKER(NamedTextColor.DARK_AQUA + "Dread Walker",Material.TRIDENT,NamedTextColor.GRAY + "They do not fear the depths of the waters; no, they fear us!"),
    NOOB(NamedTextColor.WHITE + "N00b",Material.STICK,NamedTextColor.GRAY + "How do I craft a Sponge?");

    private String display, desc;
    private Material material;

    KitType(String display, Material material, String desc) {
        this.display = display;
        this.material = material;
        this.desc = desc;
    }

    public String getDisplay() {
        return display;
    }

    public String getDesc() {
        return desc;
    }

    public Material getMaterial() {
        return material;
    }
}
