package com.github.udonya.landmine.command.lm;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.udonya.landmine.LandMine;
import com.github.udonya.landmine.command.AbstractCommand;
import com.github.udonya.landmine.command.CmdOwner;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(String name, LandMine plugin) {
        super(name, plugin);
        owner = CmdOwner.valueOf(true, true);
        setDescription("Help command");
        setUsage("/lm help");
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
        sender.sendMessage(clrCmd + "How to input text to the sign.");
        return true;
    }
}
