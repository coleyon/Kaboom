package com.github.udonya.kaboom.listener;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.config.entry.TrapsEntry;
import com.github.udonya.kaboom.config.yaml.TrapsYaml;
import com.github.udonya.kaboom.lib.CommonLogics;

public class TrapActivateByPlayerListener implements Listener{
    private final Kaboom plugin;

    public TrapActivateByPlayerListener(Kaboom plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerTrappedLandMine(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.PHYSICAL)) return;
        CommonLogics.explodeLandMine(event.getClickedBlock(), event.getPlayer().getName());
    }

    @EventHandler
    public void onTrapDestroyed(BlockBreakEvent event){
        CommonLogics.removeLandmine(event);
    }

    @EventHandler
    public void onTrapPistonExtend(BlockPistonExtendEvent event){
        TrapsEntry entry;
        // direct push
        entry = CommonLogics.getSameEntry(event.getBlock().getRelative(event.getDirection(), 1));
        if(entry != null){
            TrapsYaml.getInstance().delTrap(entry.getId());
            return;
        }
        // relative push
        for (Block block : event.getBlocks()) {
            entry = CommonLogics.getSameEntry(block.getRelative(event.getDirection()));
            if(entry != null) TrapsYaml.getInstance().delTrap(entry.getId());
        }
    }
}
