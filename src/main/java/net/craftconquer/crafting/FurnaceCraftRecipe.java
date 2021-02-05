package net.craftconquer.crafting;

import lombok.Getter;
import lombok.Setter;
import net.craftconquer.item.Item;

public class FurnaceCraftRecipe
{
    @Getter @Setter
    private Item inputItem;

    @Getter @Setter
    private Item outputItem;

    @Getter @Setter
    private float experience;

    @Getter @Setter
    private int cookTime;
}
