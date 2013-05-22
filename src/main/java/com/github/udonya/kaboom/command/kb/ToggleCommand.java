package com.github.udonya.kaboom.command.kb;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.command.AbstractCommand;
import com.github.udonya.kaboom.command.CmdOwner;

public class ToggleCommand extends AbstractCommand {

    public ToggleCommand(String name, Kaboom plugin) {
        super(name, plugin);
        owner = CmdOwner.valueOf(true, true);
        setDescription("Toggle Enable Disable this plugin's function");
        setPermission("kaboom.enable");
        setUsage("/kb toggle");
    }

    @Override
    public boolean areCompatibleParameters(CommandSender sender, Command command, String s, String[] args) {
        if (args == null) return false;
        if (args.length != 1) return false;
        if (!args[0].equalsIgnoreCase("toggle")) return false;
        return true;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) return true;
        if(!sender.hasPermission("kaboom.enable")) {
            sender.sendMessage(Color.RED + "You don't have permission!");
            return true;
        }
        boolean isSuccess;
        if (this.plugin.getEnabled().contains(sender.getName())){
            isSuccess = this.plugin.getEnabled().remove(sender.getName());
            if(isSuccess) sender.sendMessage(clrCmd + "Kaboom was disabled.");
        }else{
            isSuccess = this.plugin.getEnabled().add(sender.getName());
            if(isSuccess) sender.sendMessage(clrCmd + "Kaboom was enabled.");
        }
        return isSuccess;
    }
}
