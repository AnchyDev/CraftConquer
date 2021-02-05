package net.craftconquer.itembuilder;

import net.craftconquer.item.Item;
import net.craftconquer.util.ColorHelper;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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

        var lore = new ArrayList<String>();

        if(!item.getDescription().isEmpty())
        {
            lore.add(ChatColor.GRAY + ChatColor.stripColor(item.getDescription()));
        }

        itemMeta.setLore(lore);

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);

        if(item.isGlowing())
        {
            itemMeta.addEnchant(Enchantment.LUCK, 0, true);
        }

        buildItem.setItemMeta(itemMeta);

        return buildItem;
    }
}
