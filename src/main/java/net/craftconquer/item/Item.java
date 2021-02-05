package net.craftconquer.item;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

public class Item
{
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String identifier;

    @Getter @Setter
    private Material material;

    @Getter @Setter
    private ItemQuality quality;

    @Getter @Setter
    private boolean isGlowing;

    public Item()
    {
        this.quality = ItemQuality.Common;
        this.isGlowing = false;
        this.description = "";
    }
}
