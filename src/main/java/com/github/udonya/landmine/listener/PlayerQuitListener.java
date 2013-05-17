package com.github.udonya.landmine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.github.udonya.landmine.LandMine;

public class PlayerQuitListener implements Listener{
    /**
     * Refs of plugin instance
     */
    private final LandMine plugin;

    /**
     * Constructor
     * @param plugin
     */
    public PlayerQuitListener(LandMine plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Clean up clicked record.
     * @param event
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
    }

}
