package com.github.udonya.landmine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.udonya.landmine.LandMine;

public class TrapPlaceListener  implements Listener{
    private final LandMine plugin;

    public TrapPlaceListener(LandMine plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(BlockPlaceEvent event){
    }
}
