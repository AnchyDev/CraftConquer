package net.craftconquer.main;

import net.craftconquer.command.CommandCraftConquer;
import net.craftconquer.command.CommandRegistry;
import net.craftconquer.command.subcommand.CommandAddItem;
import net.craftconquer.item.Item;
import net.craftconquer.item.ItemRegistry;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin
{
    private static Main instance;

    private Logger logger;
    private FileConfiguration config;

    private ItemRegistry itemRegistry;
    private CommandRegistry commandRegistry;

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

        this.getServer().getPluginCommand("craftconquer").setExecutor(new CommandCraftConquer());
        commandRegistry.registerSubCommand("additem", new CommandAddItem());

        registerItems();
    }

    public ItemRegistry getItemRegistry()
    {
        return itemRegistry;
    }

    public CommandRegistry getCommandRegistry()
    {
        return commandRegistry;
    }

    private void registerItems()
    {
        var item = new Item();
        item.setName("Test Item");
        item.setMaterial(Material.GRASS_BLOCK);
        itemRegistry.register("cc:testitem", item);
    }

    public static Main getInstance()
    {
        return instance;
    }
}
