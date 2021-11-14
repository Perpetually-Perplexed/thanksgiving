package me.perplexed.delivery.items;

import me.perplexed.delivery.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;

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

    public ItemStack getCustomItem(int amt) {
        ItemStack item = getCustomItem();
        item.setAmount(amt);
        return item;
    }

    protected ItemStack item(Component name, Material mat, int amt) {
        ItemStack stack = new ItemStack(mat,amt);
        stack.editMeta(m -> m.displayName(name.decoration(TextDecoration.ITALIC,false)));
        return stack;
    }

    public String getId() {
        return id;
    }

    public void setCustomItem(ItemStack customItem) {
        this.customItem = customItem;
    }
}
