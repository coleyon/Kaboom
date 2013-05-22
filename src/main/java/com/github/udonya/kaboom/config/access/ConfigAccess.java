package com.github.udonya.kaboom.config.access;

import java.util.List;

import com.github.udonya.kaboom.config.entry.ConfigEntry;
import com.github.udonya.kaboom.definitions.TrapType;

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
