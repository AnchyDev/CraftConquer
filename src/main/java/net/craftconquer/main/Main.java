package net.craftconquer.main;

import com.google.gson.Gson;
import lombok.Getter;
import net.craftconquer.command.CommandCraftConquer;
import net.craftconquer.command.CommandRegistry;
import net.craftconquer.command.subcommand.CommandAddItem;
import net.craftconquer.command.subcommand.CommandTest;
import net.craftconquer.config.CraftConquerConfig;
import net.craftconquer.crafting.*;
import net.craftconquer.item.Item;
import net.craftconquer.item.ItemQuality;
import net.craftconquer.item.ItemRegistry;
import net.craftconquer.itembuilder.ItemBuilder;
import net.craftconquer.mining.MiningListener;
import net.craftconquer.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
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
    private CraftRecipeRegistry craftRegistry;

    @Getter
    private CraftConquerConfig ccConfig;

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
        craftRegistry = new CraftRecipeRegistry();

        this.getServer().getPluginCommand("craftconquer").setExecutor(new CommandCraftConquer());
        commandRegistry.registerSubCommand("additem", new CommandAddItem());
        commandRegistry.registerSubCommand("test", new CommandTest());

        this.getServer().getPluginManager().registerEvents(new CraftListener(), this);
        this.getServer().getPluginManager().registerEvents(new MiningListener(), this);

        createConfigs();
        loadConfigs();
        registerItems();
        registerRecipes();
    }

    private void loadConfigs()
    {
        ccConfig = ConfigHelper.LoadConfig(ConfigHelper.ccConfig, CraftConquerConfig.class);
    }

    private void createConfigs()
    {
        if(Files.notExists(Path.of(ConfigHelper.ccDirectory)))
        {
            try
            {
                Files.createDirectories(Path.of(ConfigHelper.ccDirectory));
            } catch (IOException e)
            {
                this.getLogger().log(Level.SEVERE, "Failed to create '"+ConfigHelper.ccDirectory+"' directory.");
                e.printStackTrace();
            }
        }

        if(Files.notExists(Path.of(ConfigHelper.ccConfig)))
        {
            try
            {
                Files.createFile(Path.of(ConfigHelper.ccConfig));
            } catch (IOException e)
            {
                this.getLogger().log(Level.SEVERE, "Failed to create '"+ConfigHelper.ccConfig+"' directory.");
                e.printStackTrace();
            }
        }
    }

    private void registerItems()
    {
        var blackSteel = new Item();
        blackSteel.setName("Black Steel");
        blackSteel.setDescription("Obtained by smelting Blackened Ore.");
        blackSteel.setMaterial(Material.NETHERITE_INGOT);
        blackSteel.setQuality(ItemQuality.Common);

        var blackenedOre = new Item();
        blackenedOre.setName("Blackened Ore");
        blackenedOre.setDescription("Needs to be processed at a furnace.");
        blackenedOre.setMaterial(Material.COAL);
        blackenedOre.setQuality(ItemQuality.Common);

        var helmet = new Item();
        helmet.setName("Black Steel Helmet");
        helmet.setMaterial(Material.NETHERITE_HELMET);
        helmet.setQuality(ItemQuality.Uncommon);

        var chest = new Item();
        chest.setName("Black Steel Chestplate");
        chest.setMaterial(Material.NETHERITE_CHESTPLATE);
        chest.setQuality(ItemQuality.Uncommon);

        var legs = new Item();
        legs.setName("Black Steel Leggings");
        legs.setMaterial(Material.NETHERITE_LEGGINGS);
        legs.setQuality(ItemQuality.Uncommon);

        var boots = new Item();
        boots.setName("Black Steel Boots");
        boots.setMaterial(Material.NETHERITE_BOOTS);
        boots.setQuality(ItemQuality.Uncommon);

        itemRegistry.register("cc:testhelmet", helmet);
        itemRegistry.register("cc:testchest", chest);
        itemRegistry.register("cc:testlegs", legs);
        itemRegistry.register("cc:testboots", boots);

        itemRegistry.register("cc:blacksteel", blackSteel);
        itemRegistry.register("cc:blackenedore", blackenedOre);
    }

    private void registerRecipes()
    {
        var helmetRecipe = new CraftRecipe();
        helmetRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                null, null, null
        });
        helmetRecipe.setOutputItem(itemRegistry.getItem("cc:testhelmet"));
        craftRegistry.registerRecipe("cc:testcrafthelmet", helmetRecipe);

        var chestRecipe = new CraftRecipe();
        chestRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1)
        });
        chestRecipe.setOutputItem(itemRegistry.getItem("cc:testchest"));
        craftRegistry.registerRecipe("cc:testcraftchest", chestRecipe);

        var legsRecipe = new CraftRecipe();
        legsRecipe.setRecipeMatrix(new CraftItem[] {
                new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1), new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1)
        });
        legsRecipe.setOutputItem(itemRegistry.getItem("cc:testlegs"));
        craftRegistry.registerRecipe("cc:testcraftlegs", legsRecipe);

        var bootsRecipe = new CraftRecipe();
        bootsRecipe.setRecipeMatrix(new CraftItem[] {
                null, null, null,
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1),
                new CraftItem("cc:blacksteel", 1), null, new CraftItem("cc:blacksteel", 1)
        });
        bootsRecipe.setOutputItem(itemRegistry.getItem("cc:testboots"));

        var blackSteelRecipe = new FurnaceCraftRecipe();
        blackSteelRecipe.setInputItem(itemRegistry.getItem("cc:blackenedore"));
        blackSteelRecipe.setOutputItem(itemRegistry.getItem("cc:blacksteel"));
        blackSteelRecipe.setExperience(2);
        blackSteelRecipe.setCookTime(100);

        craftRegistry.registerRecipe("cc:testcraftboots", bootsRecipe);
        craftRegistry.registerFurnaceRecipe("cc_smeltblacksteel", blackSteelRecipe);
    }
}
