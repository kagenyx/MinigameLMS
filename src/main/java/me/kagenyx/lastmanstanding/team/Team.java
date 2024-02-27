package me.kagenyx.lastmanstanding.team;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

public enum Team {

    ONE(Component.text("One")),
    TWO(Component.text("Two")),
    THREE(Component.text("Three")),
    PI(Component.text("3.14159")),
    FOUR(Component.text("Four")),
    FIVE(Component.text("Five")),
    SIX(Component.text("Six")),
    SEVEN(Component.text("Seven")),
    EIGHT(Component.text("Eight")),
    NINE(Component.text("Nine")),
    TEN(Component.text("Ten")),
    ELEVEN(Component.text("Eleven")),
    TWELVE(Component.text("Twelve")),
    THIRTEEN(Component.text("Thirteen"));

    private Component display;

    Team(Component display) {
        this.display = display;
    }

    public Component getDisplay() {
        return display;
    }


}
