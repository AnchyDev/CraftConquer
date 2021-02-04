package net.craftconquer.crafting;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class CraftHandler
{
    @Getter
    @Setter
    private HashMap<String, CraftRecipe> recipes;

    public CraftHandler()
    {
        recipes = new HashMap<>();
    }

    public void registerRecipe(String id, CraftRecipe recipe)
    {
        recipes.put(id, recipe);
    }
}
