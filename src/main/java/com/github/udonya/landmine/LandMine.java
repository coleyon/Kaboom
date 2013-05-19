package com.github.udonya.landmine;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.udonya.landmine.command.AbstractCommandHandler;
import com.github.udonya.landmine.command.lm.CommandHandler;
import com.github.udonya.landmine.config.Yamls;
import com.github.udonya.landmine.listener.TrapActivateListener;
import com.github.udonya.landmine.listener.TrapPlaceListener;

public class LandMine extends JavaPlugin{

    /**
     * Each player's plugin function enabled status.
     */
    private Set<String> enabled;

    private AbstractCommandHandler cmdExecutor;

    @Override
    public void onEnable() {
        super.onEnable();
        enabled = new HashSet<String>();
        enablesConfigures();
        new TrapPlaceListener(this);
        new TrapActivateListener(this);
        // register commands
        this.cmdExecutor = new CommandHandler(this);
        getCommand(this.cmdExecutor.getCmdName()).setExecutor(this.cmdExecutor);

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
    public Set<String> getEnabled() {
        return enabled;
    }

    public void setEnabled(Set<String> enabled) {
        this.enabled = enabled;
    }
}
