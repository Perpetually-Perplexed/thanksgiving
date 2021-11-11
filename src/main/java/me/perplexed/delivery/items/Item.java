package me.perplexed.delivery.items;

import me.perplexed.delivery.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public abstract class Item {

    private ItemStack customItem;
    protected NamespacedKey key = new NamespacedKey(Main.getInstance(),"recipe");
    private final String id;

    protected Item(ItemStack item,String id) {
        this.customItem = item;
        this.id = id;
    }

    protected Item(String id) {
        this.customItem = new ItemStack(Material.COOKED_CHICKEN);
        this.id = id;
    }

    public ItemStack getCustomItem() {
        return new ItemStack(customItem);
    }

    public String getId() {
        return id;
    }

    public void setCustomItem(ItemStack customItem) {
        this.customItem = customItem;
    }
}
