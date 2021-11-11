package me.perplexed.delivery.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class TxtUtils {
    public static @NotNull Component red(String msg) {
        return Component.text(msg).color(NamedTextColor.RED);
    }

    public static @NotNull Component green(String msg) {
        return Component.text(msg).color(NamedTextColor.GREEN);
    }

    public static @NotNull Component yellow(String msg) {
        return Component.text(msg).color(NamedTextColor.YELLOW);
    }

    public static @NotNull Component custom(String msg, TextColor clr) {
        return Component.text(msg).color(clr);
    }

}
