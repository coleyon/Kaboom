package com.github.udonya.kaboom.config.access;

import java.util.Set;

import com.github.udonya.kaboom.config.entry.TrapsEntry;

public interface TrapsAccess {

    public void setTrap(TrapsEntry entry);
    public TrapsEntry getTrap(String id);
    public Set<String> getIDList();
    public void delTrap(String id);
}
