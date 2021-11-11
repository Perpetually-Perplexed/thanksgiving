package me.perplexed.delivery;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main inst;
    
    @Override
    public void onEnable() {
        inst = this;
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return inst;
    }
}
