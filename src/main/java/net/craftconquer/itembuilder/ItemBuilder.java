package net.craftconquer.itembuilder;

import net.craftconquer.item.Item;
import net.craftconquer.util.ColorHelper;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder
{
    private Item item;

    public ItemBuilder(Item item)
    {
        this.item = item;
    }

    public ItemStack build()
    {
        var buildItem = new ItemStack(item.getMaterial(), 1);
        var itemMeta = buildItem.getItemMeta();

        itemMeta.setDisplayName(ColorHelper.getColorFromItemQuality(item.getQuality()) + ChatColor.stripColor(item.getName()));
        itemMeta.setLocalizedName(item.getIdentifier());

        buildItem.setItemMeta(itemMeta);

        return buildItem;
    }
}
