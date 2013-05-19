package com.github.udonya.landmine.listener;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import com.github.udonya.landmine.LandMine;
import com.github.udonya.landmine.config.entry.TrapsEntry;
import com.github.udonya.landmine.config.yaml.TrapsYaml;
import com.github.udonya.landmine.definitions.TrapType;

public class TrapPlaceListener implements Listener{
    private final LandMine plugin;

    public TrapPlaceListener(LandMine plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onTrapPlace(BlockPlaceEvent event){
        TrapType type = TrapType.getLandMineType(event.getBlock().getType());
        if(type == null) return;
        double x = event.getBlock().getLocation().getX();
        double y = event.getBlock().getLocation().getY();
        double z = event.getBlock().getLocation().getZ();
        String worldName = event.getBlock().getLocation().getWorld().getName();
        String ownerName = event.getPlayer().getName();
        TrapsYaml.getInstance().setTrap(new TrapsEntry(UUID.randomUUID().toString(), x, y, z, worldName, ownerName, type));
    }
}
