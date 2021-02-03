package net.craftconquer.command;

import net.craftconquer.command.subcommand.SubCommand;

import java.util.HashMap;

public class CommandRegistry
{
    private HashMap<String, SubCommand> subCommands;

    public CommandRegistry()
    {
        subCommands = new HashMap<>();
    }

    public void registerSubCommand(String command, SubCommand executor)
    {
        subCommands.put(command, executor);
    }

    public HashMap<String, SubCommand> getSubCommands()
    {
        return subCommands;
    }
}
