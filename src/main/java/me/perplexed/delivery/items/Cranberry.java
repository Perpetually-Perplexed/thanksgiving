package me.perplexed.delivery.items;


import me.perplexed.delivery.utils.TxtUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class Cranberry extends Item {

    public Cranberry() {
        super("cranberry");
        setCustomItem(item(TxtUtils.white("Cranberry"), Material.SWEET_BERRIES,1));
        ShapedRecipe recipe = new ShapedRecipe(key,getCustomItem(10));
        recipe.shape("AAA","BBB","AAA");
        recipe.setIngredient('A',Material.AIR);
        recipe.setIngredient('B',Material.SWEET_BERRIES);

        Bukkit.getServer().addRecipe(recipe);
    }
}
