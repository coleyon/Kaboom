package com.github.udonya.landmine.config.entry;

import java.util.UUID;
import com.github.udonya.landmine.definitions.TrapType;

public class TrapsEntry implements Entry{
    private UUID id;
    private double x;
    private double y;
    private double z;
    private String worldName;
    private String ownerName;
    private TrapType type;

    public TrapsEntry(double x, double y, double z, String worldName, String playerName, TrapType type) {
        this.id = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
        this.ownerName = playerName;
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
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}
