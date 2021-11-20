package me.perplexed.delivery.utils;

import com.google.common.base.Preconditions;
import me.perplexed.delivery.Main;
import me.perplexed.delivery.mission.Game;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Utils {

    public static int spawnBeam(World world, double x, double z, long ticks) {
        final Location[] spawnAt = {new Location(world, x, 0, z)};

        return new BukkitRunnable() {
            @Override
            public void run() {
                int max = ticks <= 0 ? -1 : (int) (ticks / 100);
                for (int i = 0; i < 256; ++i) {
                    spawnAt[0] = new Location(world,x,i,z);
                    if (spawnAt[0].getBlock().getType() != Material.AIR) {
                        continue;
                    }

                    // todo change particle
                    world.spawnParticle(Particle.LIGHT, spawnAt[0],1);

                    if (max == 0) {
                        cancel();
                    } else {
                        --max;
                    }
                }

            }
        }.runTaskTimer(Main.getInstance(),0L,4*20).getTaskId();
    }

    @NotNull
    public static Object ifNull(@Nullable Object obj,@NotNull Object placeholder) {
        Preconditions.checkNotNull(placeholder,"Placeholder null!");
        if (obj == null) {
            return placeholder;
        }

        return obj;
    }

    public static boolean inGame(UUID player) {
        Player pogger = Bukkit.getPlayer(player); // i made this code late at night AND THERE IS NOTHING YOU CAN DO ABOUT IT
        Preconditions.checkArgument(pogger == null,"who passed a fucking non existant player?"); //sadly this will never run unless the player somehow leaves before the previous line is run
        return Main.getInstance().gameList.stream().filter(g -> g.isInGame(player)).toList().size() == 0;
    }


    public static Game gameOf(UUID player) {
        Player pogger = Bukkit.getPlayer(player); // i made this code late at night AND THERE IS NOTHING YOU CAN DO ABOUT IT
        if (!inGame(player)) return null;
        Preconditions.checkArgument(pogger == null,"who passed a fucking non existant player?"); //sadly this will never run unless the player somehow leaves before the previous line is run
        return Main.getInstance().gameList.stream().filter(g -> g.isInGame(player)).toList().get(0);
    }


    public static int spawnBeam(Location loc, long ticks) {
       return spawnBeam(loc.getWorld(), loc.getX(), loc.getZ(), ticks);
    }
}
