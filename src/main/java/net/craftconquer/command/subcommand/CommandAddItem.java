package net.craftconquer.command.subcommand;

import net.craftconquer.itembuilder.ItemBuilder;
import net.craftconquer.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandAddItem implements SubCommand
{
    @Override
    public String getCommand()
    {
        return "additem";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(sender instanceof Player)
        {
            var player = (Player)sender;

            if (args.length < 2)
            {
                player.sendMessage("No item identifier provided.");
                return;
            }

            String identifier = args[1];

            var registry = Main.getInstance().getItemRegistry();
            var item = registry.getItem(identifier.toLowerCase());

            if (item == null)
            {
                player.sendMessage("No item found with this identifier.");
                return;
            }

            var inventory = player.getInventory();

            player.getInventory().addItem(new ItemBuilder(item).build());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args)
    {
        if(args.length == 2)
        {
            var registry = Main.getInstance().getItemRegistry().getRegistry();
            var items = new ArrayList<String>();

            registry.forEach((indentifier, item) ->
            {
                items.add(indentifier);
            });

            return items;
        }

        return new ArrayList<>();
    }
}
