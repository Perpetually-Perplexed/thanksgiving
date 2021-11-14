package me.perplexed.delivery.mission;

import me.perplexed.delivery.Main;
import me.perplexed.delivery.items.Items;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.inventory.ItemStack;

public enum Missions {
    IGLOO(1,300,StructureType.IGLOO,new ItemStack(Material.COOKED_CHICKEN,5)),
    WOODLAND_MANSION(5,600,StructureType.WOODLAND_MANSION,new ItemStack(Material.SWEET_BERRIES,30)
            ,new ItemStack(Material.COOKED_CHICKEN,16), Items.getInstance().DISCOUNT_TURKEY.getCustomItem()),
    VILLAGE(5,600,StructureType.VILLAGE,new ItemStack(Material.SWEET_BERRIES,30)
            ,new ItemStack(Material.COOKED_CHICKEN,16), Items.getInstance().DISCOUNT_TURKEY.getCustomItem());

    private final int difficulty; // 1 is the lowest 5 is the highest
    private final long time; // time is in ticks
    private final ItemStack[] resourcesRequired;
    private final StructureType type;

    Missions(int difficulty,long time, StructureType type, ItemStack... itemsRequired) {
        this.time = time;
        this.type = type;
        this.difficulty = difficulty;
        this.resourcesRequired = itemsRequired.length == 0 ? new ItemStack[]{new ItemStack(Material.COOKED_CHICKEN)} : itemsRequired;
    }
}
