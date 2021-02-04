package net.craftconquer.item;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ItemRegistry
{
    @Getter
    private HashMap<String, Item> registry;

    public ItemRegistry()
    {
        registry = new HashMap<>();
    }

    public void register(String identifier, Item item)
    {
        item.setIdentifier(identifier);
        registry.put(identifier, item);
    }

    public Item getItem(String identifier)
    {
        return registry.get(identifier);
    }
    public boolean hasItem(String identifier)
    {
        return registry.containsKey(identifier);
    }

    public boolean isCustomItem(ItemStack item)
    {
        var itemMeta = item.getItemMeta();
        var localName = itemMeta.getLocalizedName();

        return hasItem(localName);
    }
}
