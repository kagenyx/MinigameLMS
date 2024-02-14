package me.kagenyx.lastmanstanding.kit;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;

import javax.naming.Name;

public enum KitType {
    ENDERBORN(Component.text( "Enderborn").color(TextColor.fromHexString("#2f1163")),Material.ENDER_EYE,Component.text("The End is Coming").color(NamedTextColor.GRAY)),
    MAGE( Component.text("Mage").color(NamedTextColor.LIGHT_PURPLE),Material.ENCHANTED_BOOK,Component.text("In the Arcane lies true power for those who seek it!").color(NamedTextColor.GRAY)),
    BEASTMASTER(Component.text("Beastmaster").color(NamedTextColor.GREEN),Material.BONE,Component.text("The spirit of the hunt lives through me!").color(NamedTextColor.GRAY)),
    SAMURAI(Component.text("Samurai").color(TextColor.fromHexString("#e890c3")),Material.IRON_SWORD,Component.text("May honor preserve us.").color(NamedTextColor.GRAY)),
    VIKING(Component.text("Viking").color(NamedTextColor.DARK_GRAY),Material.CHAINMAIL_HELMET,Component.text("The gods shall protect us.").color(NamedTextColor.GRAY)),
    AGENT47(Component.text("Agent 47").color(NamedTextColor.DARK_RED),Material.POTION,Component.text("Good job 47.").color(NamedTextColor.GRAY)),
    FISHERMAN(Component.text("Dreadwalker").color(NamedTextColor.DARK_AQUA),Material.TRIDENT,Component.text("They do not fear the depths of the waters; no, they fear us!").color(NamedTextColor.GRAY)),
    NOOB(Component.text("N00b").color(NamedTextColor.WHITE),Material.STICK,Component.text("How do I craft a Sponge?").color(NamedTextColor.GRAY)),
    GAMBLER(Component.text("Gambler"),Material.EMERALD,Component.text("A Betano está a oferecer rodadas grátis").color(NamedTextColor.GRAY));

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
