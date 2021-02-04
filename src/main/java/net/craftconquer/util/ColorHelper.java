package net.craftconquer.util;

import net.craftconquer.item.ItemQuality;
import org.bukkit.ChatColor;

public class ColorHelper
{
    public static ChatColor getColorFromItemQuality(ItemQuality quality)
    {
        var color = ChatColor.WHITE;

        switch(quality)
        {
            case Trash:
                color = ChatColor.GRAY;
                break;

            case Common:
                color = ChatColor.WHITE;
                break;

            case Uncommon:
                color = ChatColor.GREEN;
                break;

            case Rare:
                color = ChatColor.BLUE;
                break;

            case Epic:
                color = ChatColor.LIGHT_PURPLE;
                break;

            case Legendary:
                color = ChatColor.GOLD;
                break;

            case Unknown:
                color = ChatColor.MAGIC;
                break;
        }

        return color;
    }
}
