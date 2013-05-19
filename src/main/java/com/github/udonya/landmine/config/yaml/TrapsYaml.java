package com.github.udonya.landmine.config.yaml;

import java.util.Set;
import org.bukkit.Material;

import com.github.udonya.landmine.config.access.TrapsAccess;
import com.github.udonya.landmine.config.entry.TrapsEntry;
import com.github.udonya.landmine.definitions.TrapType;

public class TrapsYaml extends AbstractYaml implements TrapsAccess{
    public static final TrapsYaml INSTANCE = new TrapsYaml();
    private static final String LM_ROOT = "LandMine";
    private static final String X = "X";
    private static final String Y = "Y";
    private static final String Z = "Z";
    private static final String OWNER = "Owner";
    private static final String TYPE = "Type";
    private static final String WORLD = "World";

    private TrapsYaml() {
        super("traps.yml", "resources");
    }

    public static TrapsYaml getInstance() {
        return INSTANCE;
    }

    @Override
    public void setTrap(TrapsEntry entry) {
        String key = LM_ROOT  + "." + entry.getId();
        if(!config().contains(key)) config().createSection(key);
        String entryRoot = key + ".";
        config().set(entryRoot + WORLD, entry.getWorldName());
        config().set(entryRoot + X, entry.getX());
        config().set(entryRoot + Y, entry.getY());
        config().set(entryRoot + Z, entry.getZ());
        config().set(entryRoot + OWNER, entry.getOwnerName());
        config().set(entryRoot + TYPE, entry.getType().getMaterial().toString());
        save();
    }

    @Override
    public TrapsEntry getTrap(String id) {
        String root = LM_ROOT + "." + id + ".";
        if(!config().contains(root)) return null;
        TrapType type = TrapType.getLandMineType(Material.getMaterial(config().getString(root + TYPE)));
        if(type == null) return null;;
        return new TrapsEntry(
                id,
                config().getDouble(root + X),
                config().getDouble(root + Y),
                config().getDouble(root + Z),
                config().getString(root + WORLD),
                config().getString(root + OWNER),
                type
        );
    }

    @Override
    public Set<String> getIDList() {
        if(!config().contains(LM_ROOT)) return null;
        Set<String> ids = config().getConfigurationSection(LM_ROOT).getKeys(false);
        return ids;
    }

    @Override
    public void delTrap(String id) {
        if(config().contains(LM_ROOT)) config().set(LM_ROOT + "." + id, null);
        save();
    }
}
