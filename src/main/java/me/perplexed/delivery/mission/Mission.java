package me.perplexed.delivery.mission;

import me.perplexed.delivery.utils.TxtUtils;
import me.perplexed.delivery.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Mission {

    private final Missions mission;
    private final Player plyr;
    private final int beamTaskId;
    private final Location goal;
    private final LivingEntity reportTo;

    public Mission(Missions mission, Player plyr, Location goal) {
        this.mission = mission;
        this.plyr = plyr;
        this.goal = goal;
        this.beamTaskId = Utils.spawnBeam(goal,-1);
        EntityType type = switch (Missions.MissionsNumerated.convert(mission)) {
            case WITCH_HUT -> EntityType.WITCH;
            case WOODLAND_MANSION -> EntityType.ILLUSIONER;
            default -> EntityType.VILLAGER;
        };

        this.reportTo = (LivingEntity) goal.getWorld().spawnEntity(goal,type, CreatureSpawnEvent.SpawnReason.NATURAL);
        if (reportTo instanceof Villager) {
            Villager.Type set = switch (goal.getWorld().getBiome(goal)) {
                case DESERT,BADLANDS,BADLANDS_PLATEAU,MODIFIED_BADLANDS_PLATEAU,MODIFIED_WOODED_BADLANDS_PLATEAU,WOODED_BADLANDS_PLATEAU,ERODED_BADLANDS,DESERT_HILLS,DESERT_LAKES -> Villager.Type.DESERT;
                case TAIGA,TAIGA_HILLS,GIANT_SPRUCE_TAIGA,GIANT_SPRUCE_TAIGA_HILLS,GIANT_TREE_TAIGA,GIANT_TREE_TAIGA_HILLS -> Villager.Type.TAIGA;
                case MOUNTAINS,SNOWY_TAIGA,SNOWY_BEACH,SNOWY_MOUNTAINS,SNOWY_TAIGA_HILLS,SNOWY_TUNDRA,SNOWY_TAIGA_MOUNTAINS,MOUNTAIN_EDGE,TAIGA_MOUNTAINS,MODIFIED_GRAVELLY_MOUNTAINS,GRAVELLY_MOUNTAINS -> Villager.Type.SNOW;
                case SAVANNA,SAVANNA_PLATEAU,SHATTERED_SAVANNA_PLATEAU,SHATTERED_SAVANNA -> Villager.Type.SAVANNA;
                case SWAMP,SWAMP_HILLS -> Villager.Type.SWAMP;
                case JUNGLE, JUNGLE_EDGE,JUNGLE_HILLS,BAMBOO_JUNGLE_HILLS,BAMBOO_JUNGLE,MODIFIED_JUNGLE_EDGE,MODIFIED_JUNGLE-> Villager.Type.JUNGLE;
                default -> Villager.Type.PLAINS;
            };
            ((Villager) reportTo).setVillagerType(set);
        }
    }

    public Location getGoal() {
        return goal;
    }

    public LivingEntity getReportTo() {
        return reportTo;
    }

    public void end() {
        plyr.sendMessage(TxtUtils.green("Yay, you completed a mission!"));
        Utils.gameOf(plyr.getUniqueId()).rollNewTask(plyr,mission.getDifficulty() + 1);
        Bukkit.getScheduler().cancelTask(beamTaskId);
    }


}
