package net.craftconquer.itembuilder;

import net.craftconquer.item.Item;
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

        itemMeta.setDisplayName(item.getName());
        itemMeta.setLocalizedName(item.getIdentifier());

        buildItem.setItemMeta(itemMeta);

        return buildItem;
    }
}