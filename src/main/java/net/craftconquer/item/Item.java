package net.craftconquer.item;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

public class Item
{
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String identifier;

    @Getter @Setter
    private Material material;

    @Getter @Setter
    private ItemQuality quality;

    public Item()
    {
        this.quality = ItemQuality.Common;
    }
}
