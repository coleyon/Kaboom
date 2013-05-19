package com.github.udonya.landmine.command.lm;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.udonya.landmine.LandMine;
import com.github.udonya.landmine.command.AbstractCommand;
import com.github.udonya.landmine.command.CmdOwner;

public class ToggleCommand extends AbstractCommand {

    public ToggleCommand(String name, LandMine plugin) {
        super(name, plugin);
        owner = CmdOwner.valueOf(true, true);
        setDescription("Toggle Enable Disable this plugin's function");
        setPermission("landmine.enable");
        setUsage("/lm toggle");
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
        if(!sender.hasPermission("landmine.enable")) {
            sender.sendMessage(Color.RED + "You don't have permission!");
            return true;
        }
        boolean isSuccess;
        if (this.plugin.getEnabled().contains(sender.getName())){
            isSuccess = this.plugin.getEnabled().remove(sender.getName());
            if(isSuccess) sender.sendMessage(clrCmd + "LandMine was disabled.");
        }else{
            isSuccess = this.plugin.getEnabled().add(sender.getName());
            if(isSuccess) sender.sendMessage(clrCmd + "LandMine was enabled.");
        }
        return isSuccess;
    }
}
