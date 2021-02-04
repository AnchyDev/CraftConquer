package net.craftconquer.crafting;

import lombok.Getter;
import lombok.Setter;
import net.craftconquer.item.Item;

public class CraftRecipe
{
    @Getter
    @Setter
    private CraftItem[] recipeMatrix;

    @Getter
    @Setter
    private Item outputItem;
}
