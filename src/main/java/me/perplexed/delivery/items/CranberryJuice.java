package me.perplexed.delivery.items;

import me.perplexed.delivery.utils.TxtUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class CranberryJuice extends Item {

    public CranberryJuice() {
        super("cranberry_juice");
        setCustomItem(item(TxtUtils.white("Cranberry Juice"), Material.HONEY_BOTTLE,1));
        ShapedRecipe recipe = new ShapedRecipe(key,getCustomItem());
        recipe.shape("AAA","CCC","ABA");
        recipe.setIngredient('A',Material.AIR);
        recipe.setIngredient('B',Material.GLASS_BOTTLE);
        recipe.setIngredient('C',Items.getInstance().CRANBERRY.getCustomItem(2));

        Bukkit.getServer().addRecipe(recipe);
    }
}
