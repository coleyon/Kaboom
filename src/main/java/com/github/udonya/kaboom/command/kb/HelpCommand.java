package com.github.udonya.kaboom.command.kb;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.command.AbstractCommand;
import com.github.udonya.kaboom.command.CmdOwner;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(String name, Kaboom plugin) {
        super(name, plugin);
        owner = CmdOwner.valueOf(true, true);
        setDescription("Help command");
        setUsage("/kb help");
    }

    @Override
    public boolean areCompatibleParameters(CommandSender sender, Command command, String s, String[] args) {
        if (args == null) return false;
        if (args.length != 1) return false;
        if (!args[0].equalsIgnoreCase("help")) return false;
        return true;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;
        sender.sendMessage(clrCmd + "How to set the trap.");
        sender.sendMessage(clrCmd + "  Place the block type is wood or stone plate.");
        sender.sendMessage(clrCmd + "How to toggle Enable/Disable this plugin at each player.");
        sender.sendMessage(clrCmd + "  ex. /kb toggle");
        sender.sendMessage(clrCmd + "  Default state is Disabled.");
        return true;
    }
}
