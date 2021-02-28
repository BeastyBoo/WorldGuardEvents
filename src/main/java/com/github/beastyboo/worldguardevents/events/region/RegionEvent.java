package com.github.beastyboo.worldguardevents.events.region;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.event.Event;

public abstract class RegionEvent extends Event {

    private final ProtectedRegion region;

    public RegionEvent(ProtectedRegion region) {
        this.region = region;
    }

    public ProtectedRegion getRegion() {
        return this.region;
    }

}


