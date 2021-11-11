package me.perplexed.delivery.items;

import me.perplexed.delivery.utils.TxtUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class DiscountTurkey extends Item {

    public DiscountTurkey() {
        super("discount_turkey");
        ItemStack discount  = new ItemStack(Material.COOKED_BEEF);
        ItemMeta meta = discount.getItemMeta();
        meta.displayName(TxtUtils.custom("Discount Turkey", NamedTextColor.WHITE).decoration(TextDecoration.ITALIC,false));
        discount.setItemMeta(meta);
        setCustomItem(discount);

        ShapedRecipe recipe = new ShapedRecipe(key,discount);
        recipe.shape("HCH","CCC","HCH");
        recipe.setIngredient('H',Material.HONEY_BOTTLE);
        recipe.setIngredient('C',new ItemStack(Material.COOKED_CHICKEN,2));

        Bukkit.getServer().addRecipe(recipe);
    }
}
