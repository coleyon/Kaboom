package com.github.udonya.kaboom.lib;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import com.github.udonya.kaboom.config.entry.TrapsEntry;
import com.github.udonya.kaboom.config.yaml.ConfigYaml;
import com.github.udonya.kaboom.config.yaml.TrapsYaml;
import com.github.udonya.kaboom.definitions.TrapType;

public class CommonLogics {
    private CommonLogics() {
    }

    public static void explodeLandMine(Block block, String targetName){
        TrapsEntry entry = CommonLogics.getSameEntry(block);
        if(entry == null) return;

        float power;
        boolean setFire;
        boolean breakBlock;

        switch (block.getType()) {
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
        case IRON_PLATE:
            power = ConfigYaml.getInstance().getLandMine(TrapType.Iron).getDamage();
            setFire = ConfigYaml.getInstance().getLandMine(TrapType.Iron).isSetFire();
            breakBlock = ConfigYaml.getInstance().getLandMine(TrapType.Iron).isBreakBlock();
            break;
        case GOLD_PLATE:
            power = ConfigYaml.getInstance().getLandMine(TrapType.Gold).getDamage();
            setFire = ConfigYaml.getInstance().getLandMine(TrapType.Gold).isSetFire();
            breakBlock = ConfigYaml.getInstance().getLandMine(TrapType.Gold).isBreakBlock();
            break;
        default:
            return;
        }
        block.getWorld().createExplosion(
                block.getLocation().getX(),
                block.getLocation().getY(),
                block.getLocation().getZ(),
                power,
                setFire,
                breakBlock
        );
        block.setType(Material.AIR);
        if(entry != null) TrapsYaml.getInstance().delTrap(entry.getId());
        String owner = entry.getOwnerName();
        if(Bukkit.getServer().getPlayer(owner) == null) return;
        if(!Bukkit.getServer().getPlayerExact(owner).isOnline()) return;
        Bukkit.getServer().getPlayerExact(owner).sendMessage(ChatColor.GOLD.toString() + "KaBooom!! " + targetName + " trapped your landmine!");
    }

    public static void removeLandmine(BlockEvent event){
        TrapsEntry entry = getSameEntry(event.getBlock());
        if(entry != null) TrapsYaml.getInstance().delTrap(entry.getId());
    }

    public static TrapsEntry getSameEntry(Block block){
        Location clickedLoc = block.getLocation();
        Set<String> uuids = TrapsYaml.getInstance().getIDList();
        if(uuids == null) return null;
        for (String uuid : uuids) {
            TrapsEntry entry = TrapsYaml.getInstance().getTrap(uuid);
            if(entry == null) return null;
            Location entryLoc = block.getLocation().getWorld().getBlockAt(
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
