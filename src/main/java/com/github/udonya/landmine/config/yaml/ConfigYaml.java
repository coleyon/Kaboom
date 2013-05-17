package com.github.udonya.landmine.config.yaml;

import com.github.udonya.landmine.config.access.ConfigAccess;

/**
 * config.yml
 *
 * @author mitchy
 *
 */
public class ConfigYaml extends AbstractYaml implements ConfigAccess {

    public static final ConfigYaml INSTANCE = new ConfigYaml();

    private ConfigYaml() {
        super("config.yml", "resources");
    }

    public static ConfigYaml getInstance() {
        return INSTANCE;
    }
}
