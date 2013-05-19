package com.github.udonya.landmine.config.entry;

import com.github.udonya.landmine.definitions.TrapType;

public class ConfigEntry implements Entry{

    private TrapType type;
    private float damage;
    private boolean setFire;
    private boolean breakBlock;

    public ConfigEntry(TrapType type, float damage, boolean setFire, boolean breakBlock) {
        this.type = type;
        this.damage = damage;
        this.setFire = setFire;
        this.breakBlock = breakBlock;
    }

    public TrapType getType() {
        return type;
    }

    public float getDamage() {
        return damage;
    }

    public boolean isSetFire() {
        return setFire;
    }

    public boolean isBreakBlock() {
        return breakBlock;
    }

    public void setType(TrapType type) {
        this.type = type;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setSetFire(boolean setFire) {
        this.setFire = setFire;
    }

    public void setBreakBlock(boolean breakBlock) {
        this.breakBlock = breakBlock;
    }
}
