package com.github.udonya.landmine.config.access;

import java.util.List;

import com.github.udonya.landmine.config.entry.ConfigEntry;
import com.github.udonya.landmine.definitions.TrapType;

/**
 * Access methods for config.yml
 *
 * @author udonya
 *
 */
public interface ConfigAccess {
    public void setLandMine(ConfigEntry landMine);
    public ConfigEntry getLandMine(TrapType type);
    public List<TrapType> getLandMineList();
}
