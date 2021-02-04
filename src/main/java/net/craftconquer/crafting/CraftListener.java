package net.craftconquer.crafting;

import net.craftconquer.itembuilder.ItemBuilder;
import net.craftconquer.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class CraftListener implements Listener
{
    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event)
    {
        var inventory = event.getInventory();

        if(inventory == null || inventory.getMatrix() == null || inventory.getMatrix().length < 1)
        {
            return;
        }

        var itemRegistry = Main.getInstance().getItemRegistry();

        boolean foundCustom = false;
        for(var item : inventory)
        {
            if(item == null)
            {
                continue;
            }

            if(itemRegistry.isCustomItem(item))
            {
                foundCustom = true;
                break;
            }
        }

        if(foundCustom)
        {
           inventory.setResult(new ItemStack(Material.AIR));

           var craftHandler = Main.getInstance().getCraftHandler();

           if(craftHandler.getRecipes().size() < 1)
           {
               return;
           }

           var recipe = getRecipeFromMatrix(inventory.getMatrix());

           if(recipe == null)
           {
               return;
           }

           inventory.setResult(new ItemBuilder(recipe.getOutputItem()).build());
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event)
    {
        if(event.getInventory().getType() !=  InventoryType.WORKBENCH)
        {
            return;
        }

        if(event.getSlotType() != InventoryType.SlotType.RESULT)
        {
            return;
        }

        if(event.getCursor().getType() != Material.AIR)
        {
            return;
        }

        var inventory = (CraftingInventory)event.getInventory();

        if(inventory.getResult() == null)
        {
            return;
        }

        var itemRegistery = Main.getInstance().getItemRegistry();

        if(!itemRegistery.isCustomItem(inventory.getResult()))
        {
            return;
        }

        if(event.getSlotType() == InventoryType.SlotType.RESULT)
        {
            event.setCancelled(true);

            if(event.getClick() == ClickType.LEFT)
            {
                var recipe = getRecipeFromMatrix(inventory.getMatrix());
                event.getWhoClicked().setItemOnCursor(inventory.getResult());

                for (int i = 0; i < recipe.getRecipeMatrix().length; i++)
                {
                    var craftItem = recipe.getRecipeMatrix()[i];
                    var inventoryItem = inventory.getMatrix()[i];

                    if (craftItem == null && inventoryItem == null)
                    {
                        continue;
                    }
                    if (inventoryItem.getAmount() > 1)
                    {
                        inventoryItem.setAmount(inventoryItem.getAmount() - craftItem.getAmount());
                    } else
                    {
                        inventoryItem.setAmount(0);
                    }
                }

                //Check to see if we can afford any more items, if not we remove the result to prevent duping.
                if (getRecipeFromMatrix(inventory.getMatrix()) == null)
                {
                    inventory.setResult(new ItemStack(Material.AIR));
                }
            }

            //Update the inventory to fix bugs regarding item disappears (visual).
            if(event.getWhoClicked() instanceof Player)
            {
                Player player = (Player)event.getWhoClicked();
                player.updateInventory();
            }
        }
    }

    private CraftRecipe getRecipeFromMatrix(ItemStack[] matrix)
    {
        var craftHandler = Main.getInstance().getCraftHandler();

        if(craftHandler.getRecipes().size() < 1)
        {
            return null;
        }

        for(var entry : craftHandler.getRecipes().entrySet())
        {
            var recipe = craftHandler.getRecipes().get(entry.getKey());

            int checksPassed = 0;

            if(recipe != null)
            {
                for (int i = 0; i < recipe.getRecipeMatrix().length; i++)
                {
                    var recipeItem = recipe.getRecipeMatrix()[i];
                    var craftingItem = matrix[i];

                    if((craftingItem == null && recipeItem != null) ||
                            craftingItem != null && recipeItem == null)
                    {
                        break;
                    }

                    if(craftingItem == null && recipeItem == null)
                    {
                        checksPassed += 1;
                        continue;
                    }

                    var craftingMeta = craftingItem.getItemMeta();
                    var craftingLocalName = craftingMeta.getLocalizedName();

                    var recipeItemName = recipeItem.getIdentifier();
                    var recipeItemCount = recipeItem.getAmount();

                    if(craftingItem.getAmount() < recipeItemCount)
                    {
                        break;
                    }

                    if (craftingLocalName.equalsIgnoreCase(recipeItemName))
                    {
                        checksPassed += 1;
                        continue;
                    }
                }

                if(checksPassed == 9)
                {
                    return recipe;
                }
            }
        }

        return null;
    }
}
