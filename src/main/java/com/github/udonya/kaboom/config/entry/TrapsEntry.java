package com.github.udonya.kaboom.config.entry;

import com.github.udonya.kaboom.definitions.TrapType;

public class TrapsEntry implements Entry{
    private String uuid;
    private double x;
    private double y;
    private double z;
    private String worldName;
    private String ownerName;
    private TrapType type;

    public TrapsEntry(String uuid, double x, double y, double z, String worldName, String ownerName, TrapType type) {
        this.uuid = uuid;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
        this.ownerName = ownerName;
        this.type = type;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public String getWorldName() {
        return worldName;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public TrapType getType() {
        return type;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public void setType(TrapType type) {
        this.type = type;
    }
    public String getId() {
        return uuid;
    }
    public void setId(String id) {
        this.uuid = id;
    }
}
