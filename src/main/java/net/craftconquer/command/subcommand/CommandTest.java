package net.craftconquer.command.subcommand;

import net.minecraft.server.v1_16_R3.Blocks;
import net.minecraft.server.v1_16_R3.ChatMessage;
import net.minecraft.server.v1_16_R3.Containers;
import net.minecraft.server.v1_16_R3.PacketPlayOutOpenWindow;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;

import java.util.ArrayList;
import java.util.List;

public class CommandTest implements SubCommand
{
    @Override
    public String getCommand()
    {
        return "test";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        if(sender instanceof Player)
        {
            var player = (Player)sender;
            var playerHandle = ((CraftPlayer)player).getHandle();

            playerHandle.playerConnection.sendPacket(new PacketPlayOutOpenWindow(4411, Containers.SMITHING, new ChatMessage("Ore Refiner")));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args)
    {
        return new ArrayList<>();
    }
}
