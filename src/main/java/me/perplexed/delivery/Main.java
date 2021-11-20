package me.perplexed.delivery;

import me.perplexed.delivery.mission.Game;
import me.perplexed.delivery.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private static Main inst;
    public final List<Game> gameList = new ArrayList<>();
    // temp
    private int taskId;
    
    @Override
    public void onEnable() {
        inst = this;
        getCommand("delivery").setExecutor(new MegaCommand());
        getCommand("delivery").setTabCompleter(new MegaCommand());
        getCommand("testparticel").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player pl)) return true;
            try {
                taskId = Utils.spawnBeam(pl.getLocation(),Long.parseLong(args[0]));
            } catch (NumberFormatException e) {
                Bukkit.getScheduler().cancelTask(taskId);
            }
            return true;
        });
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return inst;
    }
}
