package com.github.udonya.landmine.config.access;

import java.util.Set;
import com.github.udonya.landmine.config.entry.TrapsEntry;

public interface TrapsAccess {

    public void setTrap(TrapsEntry entry);
    public TrapsEntry getTrap(String id);
    public Set<String> getIDList();
    public void delTrap(String id);
}
