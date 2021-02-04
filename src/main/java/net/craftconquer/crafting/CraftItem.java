package net.craftconquer.crafting;

import lombok.Getter;
import lombok.Setter;

public class CraftItem
{
    @Getter @Setter
    private String identifier;

    @Getter @Setter
    private int amount;

    public CraftItem(String identifier, int amount)
    {
        this.identifier = identifier;
        this.amount = amount;
    }
}
