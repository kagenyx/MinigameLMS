package me.kagenyx.lastmanstanding.kit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;

import javax.naming.Name;

public enum KitType {
    ENDERBORN(Component.text( "Enderborn").color(NamedTextColor.DARK_PURPLE),Material.ENDER_EYE,Component.text("The End is Coming").color(NamedTextColor.GRAY)),
    //MAGE(NamedTextColor.LIGHT_PURPLE + "Mage",Material.ENCHANTED_BOOK,NamedTextColor.GRAY + "In the Arcane lies true power for those who seek it!"),
    //BEASTMASTER(NamedTextColor.GREEN + "Beastmaster",Material.BONE,NamedTextColor.GRAY + "The spirit of the hunt lives through me!"),
    //SAMURAI(Component.text("Samurai", TextColor.fromHexString("#e890c3")).toString(),Material.IRON_SWORD,NamedTextColor.GRAY + "May honor preserve us."),
    VIKING(Component.text("Viking").color(NamedTextColor.DARK_GRAY),Material.CHAINMAIL_HELMET,Component.text("The gods shall protect us.").color(NamedTextColor.GRAY));
    //AGENT47(NamedTextColor.DARK_RED + "Agent 47",Material.POTION,NamedTextColor.GRAY+"Good job 47."),
    //DREADWALKER(NamedTextColor.DARK_AQUA + "Dread Walker",Material.TRIDENT,NamedTextColor.GRAY + "They do not fear the depths of the waters; no, they fear us!"),
    //NOOB(NamedTextColor.WHITE + "N00b",Material.STICK,NamedTextColor.GRAY + "How do I craft a Sponge?");

    private Component text, desc;
    private Material material;

    KitType(Component text, Material material, Component desc) {
        this.text = text;
        this.material = material;
        this.desc = desc;
    }

    public Component getDisplay() {
        return text;
    }

    public Component getDesc() {
        return desc;
    }

    public Material getMaterial() {
        return material;
    }
}
