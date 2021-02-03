package net.craftconquer.item;

import lombok.Getter;

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
}
