package com.github.udonya.landmine.config.yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;

import com.github.udonya.landmine.config.access.TrapsAccess;
import com.github.udonya.landmine.config.entry.TrapsEntry;
import com.github.udonya.landmine.definitions.TrapType;

public class TrapsYaml extends AbstractYaml implements TrapsAccess{
    public static final TrapsYaml INSTANCE = new TrapsYaml();
    private static final String LM_ROOT = "LandMine.";
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
        config().set(LM_ROOT , entry.getId().toString());
        String root = LM_ROOT + entry.getId().toString() + ".";
        config().set(root + WORLD, entry.getWorldName());
        config().set(root + X, entry.getX());
        config().set(root + Y, entry.getY());
        config().set(root + Z, entry.getZ());
        config().set(root + OWNER, entry.getOwnerName());
        config().set(root + TYPE, entry.getType());
    }

    @Override
    public TrapsEntry getTrap(UUID id) {
        String root = LM_ROOT + id.toString() + ".";
        if(!config().contains(root)) return null;
        return new TrapsEntry(config().getDouble(root + X),
                config().getDouble(root + Y),
                config().getDouble(root + Z),
                config().getString(root + WORLD),
                config().getString(root + OWNER),
                TrapType.getLandMineType(Material.valueOf(config().getString(root + TYPE)))
        );
    }

    @Override
    public List<UUID> getIDList() {
        List<String> ids = config().getStringList(LM_ROOT);
        List<UUID> uuids = new ArrayList<UUID>();
        for (String id : ids) {
            uuids.add(UUID.fromString(id));
        }
        return uuids;
    }
}
