package me.perplexed.delivery.mission;

import me.perplexed.delivery.Main;
import me.perplexed.delivery.utils.TxtUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    List<UUID> plyrs;
    Map<UUID,Mission> missions = new HashMap<>();
    private final GameType type;

    public Game(UUID... plyrs) {
        if (plyrs.length != 1) {
            if (plyrs.length == 2) {
                type = GameType.VERSUS;
            } else {
                type = GameType.TEAM;
            }
        } else {
            type = GameType.SOLO;
        }

        this.plyrs = Arrays.asList(plyrs);
        Main.getInstance().gameList.add(this);
        this.plyrs.forEach(p -> rollNewTask(Bukkit.getPlayer(p),1));
    }

    public Game(GameType type, UUID... plyrs) {
        this.plyrs = Arrays.asList(plyrs);
        this.type = type;
        Main.getInstance().gameList.add(this);
        this.plyrs.forEach(p -> rollNewTask(Bukkit.getPlayer(p),1));
    }

    public void rollNewTask(Player doer, int difficulty) {
        if (!isInGame(doer.getUniqueId())) return;


        if (difficulty > 5) {
            end(doer);
            return;
        }

        missions.remove(doer.getUniqueId());

        Missions mis = Missions.difficultyOf(difficulty);

        Location doerLoc = doer.getLocation();

        Location structure = doerLoc.getWorld().locateNearestStructure(doerLoc,mis.getType(),Integer.MAX_VALUE,false);

        Mission newMission = new Mission(mis,doer,structure);
        doer.sendMessage(Missions.formatMissionText(mis,structure));
        missions.put(doer.getUniqueId(),newMission);
    }



    public void end(Player ender) {
        // todo do end
    }

     public Mission missionOf(UUID of) {
        return missions.get(of);
     }

    public boolean isInGame(UUID who) {
        return plyrs.contains(who);
    }


    public enum GameType {
        SOLO,
        VERSUS,
        TEAM;
    }

}
