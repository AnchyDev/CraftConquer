package net.craftconquer.main;

import lombok.Getter;
import net.craftconquer.command.CommandCraftConquer;
import net.craftconquer.command.CommandRegistry;
import net.craftconquer.command.subcommand.CommandAddItem;
import net.craftconquer.crafting.CraftHandler;
import net.craftconquer.crafting.CraftItem;
import net.craftconquer.crafting.CraftListener;
import net.craftconquer.crafting.CraftRecipe;
import net.craftconquer.item.Item;
import net.craftconquer.item.ItemRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin
{
    @Getter
    private static Main instance;

    private Logger logger;
    private FileConfiguration config;

    @Getter
    private ItemRegistry itemRegistry;

    @Getter
    private CommandRegistry commandRegistry;

    @Getter
    private CraftHandler craftHandler;

    @Override
    public void onEnable()
    {
        if(instance == null)
        {
            instance = this;
        }

        logger = this.getLogger();
        config = this.getConfig();

        itemRegistry = new ItemRegistry();
        commandRegistry = new CommandRegistry();
        craftHandler = new CraftHandler();

        this.getServer().getPluginCommand("craftconquer").setExecutor(new CommandCraftConquer());
        commandRegistry.registerSubCommand("additem", new CommandAddItem());

        this.getServer().getPluginManager().registerEvents(new CraftListener(), this);

        registerItems();
    }

    private void registerItems()
    {
        var customIngot = new Item();
        customIngot.setName("Black Steel");
        customIngot.setMaterial(Material.NETHERITE_INGOT);

        var helmet = new Item();
        helmet.setName("Recruit's Helmet");
        helmet.setMaterial(Material.NETHERITE_HELMET);

        var chest = new Item();
        chest.setName("Recruit's Chestplate");
        chest.setMaterial(Material.NETHERITE_CHESTPLATE);

        var legs = new Item();
        legs.setName("Recruit's Leggings");
        legs.setMaterial(Material.NETHERITE_LEGGINGS);

        var boots = new Item();
        boots.setName("Recruit's Boots");
        boots.setMaterial(Material.NETHERITE_BOOTS);

        var helmetRecipe = new CraftRecipe();
        helmetRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                null, null, null
        });
        helmetRecipe.setOutputItem(helmet);
        craftHandler.registerRecipe("cc:testcrafthelmet", helmetRecipe);

        var chestRecipe = new CraftRecipe();
        chestRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1)
        });
        chestRecipe.setOutputItem(chest);
        craftHandler.registerRecipe("cc:testcraftchest", chestRecipe);

        var legsRecipe = new CraftRecipe();
        legsRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1)
        });
        legsRecipe.setOutputItem(legs);
        craftHandler.registerRecipe("cc:testcraftlegs", legsRecipe);

        var bootsRecipe = new CraftRecipe();
        bootsRecipe.setRecipeMatrix(new CraftItem[] {
                null, null, null,
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1)
        });
        bootsRecipe.setOutputItem(boots);
        craftHandler.registerRecipe("cc:testcraftboots", bootsRecipe);

        itemRegistry.register("cc:testhelmet", helmet);
        itemRegistry.register("cc:testchest", chest);
        itemRegistry.register("cc:testlegs", legs);
        itemRegistry.register("cc:testboots", boots);

        itemRegistry.register("cc:blacksteel", customIngot);
    }
}
