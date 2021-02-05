package net.craftconquer.mining;

import net.craftconquer.itembuilder.ItemBuilder;
import net.craftconquer.main.Main;
import net.craftconquer.util.WeightBag;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.logging.Level;

public class MiningListener implements Listener
{
    private WeightBag<Material> blackenedOreWB;

    public MiningListener()
    {
        blackenedOreWB = new WeightBag<>();
        blackenedOreWB.AddItem(Material.AIR, 70);
        blackenedOreWB.AddItem(Material.COAL, 30);
    }

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event)
    {
        if(event.getBlock().getType() != Material.COAL_ORE)
        {
            return;
        }

        if(event.getPlayer().getGameMode() == GameMode.CREATIVE)
        {
            return;
        }

        var roll = blackenedOreWB.GetItem();

        if(roll != null && roll == Material.COAL)
        {
            var itemRegister = Main.getInstance().getItemRegistry();
            var blackenedOre = itemRegister.getItem("cc:blackenedore");
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemBuilder(blackenedOre).build());
        }
    }
}
