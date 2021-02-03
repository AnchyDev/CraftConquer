package net.craftconquer.command.subcommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand
{
    String getCommand();
    void onCommand(CommandSender sender, Command command, String s, String[] args);
    List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args);
}
