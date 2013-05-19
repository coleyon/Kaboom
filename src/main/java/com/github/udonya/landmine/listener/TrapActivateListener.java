package com.github.udonya.landmine.listener;

import java.util.Set;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.github.udonya.landmine.LandMine;
import com.github.udonya.landmine.config.entry.TrapsEntry;
import com.github.udonya.landmine.config.yaml.ConfigYaml;
import com.github.udonya.landmine.config.yaml.TrapsYaml;
import com.github.udonya.landmine.definitions.TrapType;

public class TrapActivateListener implements Listener{
    private final LandMine plugin;

    public TrapActivateListener(LandMine plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDroppedOnPlate(PlayerInteractEvent event){
        if(!event.getAction().equals(Action.PHYSICAL)) return;
        TrapsEntry entry = getSameEntry(event.getClickedBlock());
        if(entry == null) return;

        double x = event.getPlayer().getLocation().getX();
        double y = event.getPlayer().getLocation().getY();
        double z = event.getPlayer().getLocation().getZ();
        float power;
        boolean setFire;
        boolean breakBlock;

        switch (event.getClickedBlock().getType()) {
        case WOOD_PLATE:
            power = ConfigYaml.getInstance().getLandMine(TrapType.Wood).getDamage();
            setFire = ConfigYaml.getInstance().getLandMine(TrapType.Wood).isSetFire();
            breakBlock = ConfigYaml.getInstance().getLandMine(TrapType.Wood).isBreakBlock();
            break;
        case STONE_PLATE:
            power = ConfigYaml.getInstance().getLandMine(TrapType.Stone).getDamage();
            setFire = ConfigYaml.getInstance().getLandMine(TrapType.Stone).isSetFire();
            breakBlock = ConfigYaml.getInstance().getLandMine(TrapType.Stone).isBreakBlock();
            break;
        default:
            return;
        }
        event.getClickedBlock().getWorld().createExplosion(x, y, z, power, setFire, breakBlock);
        event.getClickedBlock().breakNaturally();
        if(entry != null) TrapsYaml.getInstance().delTrap(entry.getId());
    }

    @EventHandler
    public void onTrapDestroyed(BlockBreakEvent event){
        removeTrap(event);
    }
    @EventHandler
    public void onTrapFaded(BlockFadeEvent event){
        removeTrap(event);
    }
    private void removeTrap(BlockEvent event){
        TrapsEntry entry = getSameEntry(event.getBlock());
        if(entry != null) TrapsYaml.getInstance().delTrap(entry.getId());
    }

    private TrapsEntry getSameEntry(Block block){
        Location clickedLoc = block.getLocation();
        Set<String> uuids = TrapsYaml.getInstance().getIDList();
        if(uuids == null) return null;
        for (String uuid : uuids) {
            TrapsEntry entry = TrapsYaml.getInstance().getTrap(uuid);
            if(entry == null) return null;
            Location entryLoc = this.plugin.getServer().getWorld(entry.getWorldName()).getBlockAt(
                    (int)entry.getX(),
                    (int)entry.getY(),
                    (int)entry.getZ()
            ).getLocation();
            if(!clickedLoc.equals(entryLoc)) continue;
            if(!block.getType().equals(entry.getType().getMaterial())) continue;
            return entry;
        }
        return null;
    }
}
