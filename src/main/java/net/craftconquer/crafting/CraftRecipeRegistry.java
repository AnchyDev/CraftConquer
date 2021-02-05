package net.craftconquer.crafting;

import lombok.Getter;
import lombok.Setter;
import net.craftconquer.itembuilder.ItemBuilder;
import net.craftconquer.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.RecipeChoice;

import java.util.HashMap;
import java.util.logging.Level;

public class CraftRecipeRegistry
{
    @Getter
    @Setter
    private HashMap<String, CraftRecipe> recipes;

    public CraftRecipeRegistry()
    {
        recipes = new HashMap<>();
    }

    public void registerRecipe(String id, CraftRecipe recipe)
    {
        recipes.put(id, recipe);
    }
    public void registerFurnaceRecipe(String key, FurnaceCraftRecipe recipe)
    {
        /*
        var input = itemRegistry.getItem("cc:blackenedore");
        var inputItem = new ItemBuilder(input).build();
        var output = itemRegistry.getItem("cc:blacksteel");
        var outputItem = new ItemBuilder(output).build();
        var namespacedKey = new NamespacedKey(this, "furnace_recipe_blacksteel");

        FurnaceRecipe recipe = new FurnaceRecipe(namespacedKey, outputItem, new RecipeChoice.ExactChoice(inputItem), 2, 100);
        Bukkit.addRecipe(recipe);

         */
        var inputItem = new ItemBuilder(recipe.getInputItem()).build();
        var outputItem = new ItemBuilder(recipe.getOutputItem()).build();

        Main.getInstance().getLogger().log(Level.INFO, inputItem.getType().name());
        Main.getInstance().getLogger().log(Level.INFO, outputItem.getType().name());

        var namespacedKey = new NamespacedKey(Main.getInstance(), key);
        var furnaceRecipe = new FurnaceRecipe(namespacedKey, outputItem, new RecipeChoice.ExactChoice(inputItem), recipe.getExperience(), recipe.getCookTime());

        Bukkit.addRecipe(furnaceRecipe);
    }
}
