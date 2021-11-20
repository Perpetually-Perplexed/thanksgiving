package me.perplexed.delivery.mission;

import me.perplexed.delivery.Main;
import me.perplexed.delivery.items.Items;
import me.perplexed.delivery.utils.TxtUtils;
import me.perplexed.delivery.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Missions {
    public static final Missions IGLOO= new Missions(1,300,StructureType.IGLOO,new ItemStack(Material.COOKED_CHICKEN,5));
    public static final Missions WITCH_HUT= new Missions(1,250,StructureType.SWAMP_HUT, Items.getInstance().DISCOUNT_TURKEY.getCustomItem());
    public static final Missions DESERT_TEMPLE = new Missions(2,300,StructureType.DESERT_PYRAMID,Items.getInstance().DISCOUNT_TURKEY.getCustomItem(),new ItemStack(Material.HONEY_BOTTLE));
    public static final Missions JUNGLE_PYRIMAD = new Missions(2,300,StructureType.JUNGLE_PYRAMID,Items.getInstance().CRANBERRY_JUICE.getCustomItem(), Items.getInstance().CRANBERRY.getCustomItem(20),Items.getInstance().CRANBERRY_JUICE.getCustomItem());
    public static final Missions WOODLAND_MANSION = new Missions(3,600,StructureType.WOODLAND_MANSION,new ItemStack(Material.SWEET_BERRIES,30)
            ,new ItemStack(Material.COOKED_CHICKEN,16), Items.getInstance().DISCOUNT_TURKEY.getCustomItem());
    public static final Missions VILLAGE = new Missions(3,600,StructureType.VILLAGE,new ItemStack(Material.SWEET_BERRIES,30)
            ,new ItemStack(Material.COOKED_CHICKEN,16), Items.getInstance().DISCOUNT_TURKEY.getCustomItem());

    private final int difficulty; // 1 is the lowest 5 is the highest
    private final long time; // time is in ticks
    private final ItemStack[] resourcesRequired;
    private final StructureType type;

    private Missions(int difficulty,long time, StructureType type, ItemStack... itemsRequired) {
        this.time = time;
        this.type = type;
        this.difficulty = difficulty;
        this.resourcesRequired = itemsRequired.length == 0 ? new ItemStack[]{new ItemStack(Material.COOKED_CHICKEN)} : itemsRequired;
    }

    public StructureType getType() {
        return type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public ItemStack[] getResourcesRequired() {
        return resourcesRequired;
    }

    public long getTime() {
        return time;
    }

    public static Missions difficultyOf(int difficulty) {
        int which1 = new Random().nextInt(2);
        switch (difficulty) {
            case 1 -> {
                return which1 == 1 ? IGLOO : WITCH_HUT;
            }
            case 2 -> {
                return which1 == 1 ? DESERT_TEMPLE : JUNGLE_PYRIMAD;
            }
            // defaults to 3
            default -> {
                return which1 == 1 ? VILLAGE : WOODLAND_MANSION;
            }

        }

    }

    public static Component formatMissionText(Missions type, Location l) {
        String var = "You need to send %s to a " + ChatColor.YELLOW + type.getType().toString().toLowerCase().replaceAll("_"," ")  + "at " +
                Math.round(l.getX()) + " " + Math.round(l.getY()) + " " + Math.round(l.getZ());
        StringBuilder appendr = new StringBuilder();
        for (ItemStack stack : type.getResourcesRequired()) {
            int amt = stack.getAmount();
            appendr.append("" + ChatColor.YELLOW).append(amt);
            appendr.append(" ");
            if (stack.getItemMeta() == null) appendr.append(ChatColor.YELLOW + stack.getType().toString().toLowerCase().replaceAll("_"," "));
            else appendr.append(ChatColor.YELLOW + Component.empty().append((Component) Utils.ifNull(stack.getItemMeta().displayName(), // idk how adventure works so i did Comp.empty to make sure its a TextComp bcuz basic comps dont have content for some reason
                    TxtUtils.white("air"))).content());

            if (Arrays.stream(type.getResourcesRequired()).toList().lastIndexOf(stack) != type.getResourcesRequired().length - 1) {
                appendr.append(", ");
            }
        }

        var = String.format(var, appendr);

        return Component.text(var);
    }

    // idk ok
    public static enum MissionsNumerated {
        IGLOO(Missions.IGLOO),
        WITCH_HUT(Missions.WITCH_HUT),
        DESERT_TEMPLE(Missions.DESERT_TEMPLE),
        WOODLAND_MANSION(Missions.WOODLAND_MANSION),
        VILLAGE(Missions.VILLAGE);
        private final Missions mission;

        MissionsNumerated(Missions missions) {
            this.mission = missions;
        }

        public Missions getMission() {
            return mission;
        }

        public static MissionsNumerated convert(Missions m) {
            return Arrays.stream(values()).filter(e -> m.equals(e.getMission())).toList().get(0);
        }
    }
}
