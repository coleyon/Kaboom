package com.github.udonya.landmine.config.yaml;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;

import com.github.udonya.landmine.config.access.ConfigAccess;
import com.github.udonya.landmine.config.entry.ConfigEntry;
import com.github.udonya.landmine.definitions.TrapType;

public class ConfigYaml extends AbstractYaml implements ConfigAccess {

    public static final ConfigYaml INSTANCE = new ConfigYaml();
    private static final String LM_ROOT = "LandMine.";
    private static final String DAMAGE = "Damage";
    private static final String SETFIRE = "SetFire";
    private static final String BREAKBLOCK = "BreakBlock";

    private ConfigYaml() {
        super("config.yml", "resources");
    }

    public static ConfigYaml getInstance() {
        return INSTANCE;
    }

    @Override
    public void setLandMine(ConfigEntry landMine) {
        String rootPath = LM_ROOT + landMine.getType().getMaterial().name() + ".";
        config().set(rootPath + DAMAGE, landMine.getDamage());
        config().set(rootPath + SETFIRE, landMine.isSetFire());
        config().set(rootPath + BREAKBLOCK, landMine.isBreakBlock());
        save();
    }

    @Override
    public ConfigEntry getLandMine(TrapType type) {
        String rootPath = LM_ROOT + type.getMaterial().name() + ".";
        return new ConfigEntry(type,
                (float) config().getDouble(rootPath + DAMAGE),
                config().getBoolean(rootPath + SETFIRE),
                config().getBoolean(rootPath + BREAKBLOCK));
    }

    @Override
    public List<TrapType> getLandMineList() {
        List<String> types = config().getStringList(LM_ROOT);
        List<TrapType> re = new ArrayList<TrapType>();
        for (String type : types) {
            re.add(TrapType.getLandMineType(Material.valueOf(type)));
        }
        return re;
    }
}
