package com.github.udonya.landmine.config.access;

import java.util.List;
import java.util.UUID;

import com.github.udonya.landmine.config.entry.TrapsEntry;

public interface TrapsAccess {

    public void setTrap(TrapsEntry entry);
    public TrapsEntry getTrap(UUID id);
    public List<UUID> getIDList();
}
