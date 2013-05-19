package com.github.udonya.landmine;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.udonya.landmine.config.Yamls;
import com.github.udonya.landmine.listener.TrapActivateListener;

public class LandMine extends JavaPlugin{

    @Override
    public void onEnable() {
        super.onEnable();
        enablesConfigures();
        new TrapActivateListener(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void enablesConfigures() {
        for (Yamls yamlConfigs : Yamls.values()) {
            yamlConfigs.getInstance().init(this);
        }
    }
}
