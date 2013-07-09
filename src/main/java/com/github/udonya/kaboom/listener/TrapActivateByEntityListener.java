package com.github.udonya.kaboom.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import com.github.udonya.kaboom.Kaboom;
import com.github.udonya.kaboom.lib.CommonLogics;

public class TrapActivateByEntityListener implements Listener{
    private final Kaboom plugin;

    public TrapActivateByEntityListener(Kaboom plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow){
            CommonLogics.explodeLandMine(
                    ((Arrow)event.getEntity()).getLocation().getBlock(),
                    ((Arrow)event.getEntity()).getShooter().getType().getName()
            );
        }else{
            CommonLogics.explodeLandMine(event.getEntity().getLocation().getBlock(), event.getEntity().toString());
        }
    }

    @EventHandler
    public void onTrapPhysics(BlockPhysicsEvent event){
        switch (event.getBlock().getType()) {
        case WOOD_PLATE:
        case STONE_PLATE:
        case IRON_PLATE:
        case GOLD_PLATE:
            CommonLogics.explodeLandMine(event.getBlock(), event.getChangedType().name());
            return;
        default:
            break;
        }
    }

    @EventHandler
    public void onEntityTrappedLandMine(EntityInteractEvent event){
        CommonLogics.explodeLandMine(event.getBlock(), event.getEntity().toString());
    }
}
