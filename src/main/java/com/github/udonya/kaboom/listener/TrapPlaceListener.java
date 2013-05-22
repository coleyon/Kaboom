package com.github.udonya.kaboom.listener;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.config.entry.TrapsEntry;
import com.github.udonya.kaboom.config.yaml.TrapsYaml;
import com.github.udonya.kaboom.definitions.TrapType;

public class TrapPlaceListener implements Listener{
    private final Kaboom plugin;

    public TrapPlaceListener(Kaboom plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTrapPlace(BlockPlaceEvent event){
        if(!this.plugin.getEnabled().contains(event.getPlayer().getName())) return;
        if(!event.getPlayer().hasPermission("kaboom.enable")){
            event.getPlayer().sendMessage(ChatColor.RED.toString() + "You don't have permission for set traps.");
            return;
        }
        TrapType type = TrapType.getLandMineType(event.getBlock().getType());
        if(type == null) return;
        double x = event.getBlock().getLocation().getX();
        double y = event.getBlock().getLocation().getY();
        double z = event.getBlock().getLocation().getZ();
        String worldName = event.getBlock().getLocation().getWorld().getName();
        String ownerName = event.getPlayer().getName();
        TrapsYaml.getInstance().setTrap(new TrapsEntry(UUID.randomUUID().toString(), x, y, z, worldName, ownerName, type));
        event.getPlayer().sendMessage(ChatColor.DARK_GREEN.toString() + "Your trap was activated!");
    }
}
