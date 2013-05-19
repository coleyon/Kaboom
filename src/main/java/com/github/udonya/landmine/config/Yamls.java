package com.github.udonya.landmine.config;

import com.github.udonya.landmine.config.yaml.AbstractYaml;
import com.github.udonya.landmine.config.yaml.ConfigYaml;
import com.github.udonya.landmine.config.yaml.TrapsYaml;

/**
 * YAML形式のコンフィグファイル
 *
 * @author udonya
 *
 */
public enum Yamls {
    CONFIG,
    TRAPS;

    private String getString() {
        switch (this) {
        case CONFIG:
            return ConfigYaml.getInstance().getFileName();
        case TRAPS:
            return TrapsYaml.getInstance().getFileName();
        }
        return null;
    }

    private AbstractYaml createInstance() {
        switch (this) {
        case CONFIG:
            return ConfigYaml.getInstance();
        case TRAPS:
            return TrapsYaml.getInstance();
        }
        return null;
    }

    /**
     * 設定ファイル名
     *
     * @return
     */
    public String getFileName() {
        return this.getString();
    }

    /**
     * シングルトンインスタンス取得
     *
     * @return
     */
    public AbstractYaml getInstance() {
        return this.createInstance();
    }
}
