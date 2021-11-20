package me.perplexed.delivery;

import me.perplexed.delivery.mission.Game;
import me.perplexed.delivery.utils.TxtUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MegaCommand implements TabExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player pl)) {
            sender.sendMessage(TxtUtils.red("Only players can run this command!"));
            return true;
        }

        if (!pl.hasPermission("delivery.start")) {
            pl.sendMessage("Sorry, you don't have permission to run this command!");
            return true;
        }

        if (args.length == 0) {
            pl.sendMessage(TxtUtils.red("Usage: /deliveryman <solo/team/versus>"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "solo" -> {
                Game game = new Game(pl.getUniqueId());
            }

            case "team" -> {
                if (args.length > 2) {
                    pl.sendMessage(TxtUtils.red("Who are you teaming with?"));
                    return false;
                }

                List<UUID> teamsies = new ArrayList<>();

                for (int i = 1;i < args.length; ++i) {
                    Player team = Bukkit.getPlayer(args[1]);
                    if (team == null) {
                        pl.sendMessage("\"" +args[1] + "\" is not a real player or is offline!");
                        return true;
                    }
                    teamsies.add(team.getUniqueId());
                }

                UUID[] teamArr = new UUID[teamsies.size()];

                teamsies.toArray(teamArr);
                Game game = new Game(Game.GameType.TEAM,teamArr);
            }

            case "versus","vs" -> {
                if (args.length > 2) {
                    pl.sendMessage(TxtUtils.red("Who are you battling with?"));
                    return false;
                }

                List<UUID> teamsies = new ArrayList<>();

                for (int i = 1;i < args.length; ++i) {
                    Player team = Bukkit.getPlayer(args[1]);
                    if (team == null) {
                        pl.sendMessage("\"" +args[1] + "\" is not a real player or is offline!");
                        return true;
                    }
                    teamsies.add(team.getUniqueId());
                }

                UUID[] teamArr = new UUID[teamsies.size()];

                teamsies.toArray(teamArr);
                Game game = new Game(Game.GameType.VERSUS, teamArr);
            }

        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> tabs = new ArrayList<>();
        if (!sender.hasPermission("delivery.start")) {
            return tabs;
        }

        tabs.add("solo"); tabs.add("team"); tabs.add("versus"); tabs.add("vs");
        if (args.length == 1) {
            List<String> otherReturn = new ArrayList<>();
            for (String s : tabs) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    otherReturn.add(s);
                }
            }
            return otherReturn;
        }

        if (args.length == 2) {
            return null;
        }

        return tabs;
    }
}
