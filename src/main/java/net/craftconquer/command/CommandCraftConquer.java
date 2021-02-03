package net.craftconquer.command;

import net.craftconquer.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class CommandCraftConquer implements TabExecutor
{
    private CommandRegistry commandRegistry;

    public CommandCraftConquer()
    {
        commandRegistry = Main.getInstance().getCommandRegistry();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args)
    {
        var commands = commandRegistry.getSubCommands();

        if(args.length < 1)
        {
            return true;
        }

        if(commandRegistry.getSubCommands().size() < 1)
        {
            return true;
        }

        commands.forEach((command, executor) ->
        {
            if(args[0].equalsIgnoreCase(command))
            {
                executor.onCommand(sender, cmd, s, args);
            }
        });

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String s, String[] args)
    {
        if(args.length == 1)
        {
            if (commandRegistry.getSubCommands().size() < 1)
            {
                return null;
            }

            var commands = new ArrayList<String>();

            commandRegistry.getSubCommands().forEach((command, executor) ->
            {
                commands.add(command);
            });

            return commands;
        }
        else if(args.length > 1)
        {
            for(var entry : commandRegistry.getSubCommands().entrySet())
            {
                var subCommand = entry.getKey();

                if(args[0].equalsIgnoreCase(subCommand))
                {
                    return entry.getValue().onTabComplete(sender, cmd, s, args);
                }
            }
        }

        return new ArrayList<>();
    }
}
