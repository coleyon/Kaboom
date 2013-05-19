package com.github.udonya.landmine.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.udonya.landmine.LandMine;

public class TrapActivateListener implements Listener{
    private final LandMine plugin;

    public TrapActivateListener(LandMine plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDroppedOnPlate(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.PHYSICAL)) return;

        double x = event.getPlayer().getLocation().getX();
        double y = event.getPlayer().getLocation().getY();
        double z = event.getPlayer().getLocation().getZ();
        float power;
        boolean setFire;
        boolean breakBlock;

        switch (event.getClickedBlock().getType()) {
        case WOOD_PLATE:
            power = 1.5f;
            setFire = false;
            breakBlock = false;
            break;
        case STONE_PLATE:
            power = 3.0f;
            setFire = false;
            breakBlock = true;
            break;
        default:
            return;
        }
        event.getClickedBlock().breakNaturally();
        event.getPlayer().getWorld().createExplosion(x, y, z, power, setFire, breakBlock);
    }
}
