package me.perplexed.delivery;

import me.perplexed.delivery.utils.TxtUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MegaCommand implements TabExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player pl)) {
            sender.sendMessage(TxtUtils.red("Only players can run this command!"));
            return true;
        }

        if (args.length == 0) {
            pl.sendMessage(TxtUtils.red("Usage: /deliveryman <solo/team/versus>"));
            return true;
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        tabs.add("solo"); tabs.add("team"); tabs.add("versus");


        return tabs;
    }
}
