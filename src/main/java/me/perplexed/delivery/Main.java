package me.perplexed.delivery;

import me.perplexed.delivery.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;
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
                taskId = Utils.spawnBeam(pl.getLocation(),Long.parseLong(args[0]),pl.getUniqueId());
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
