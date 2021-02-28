package com.github.beastyboo.worldguardevents;

import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;

import java.util.Comparator;

public class WorldGuardUtil {

    public static ProtectedRegion getProtectedRegion(Location location) {
        RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
        RegionManager regions = container.get(location.getWorld());

        ProtectedRegion region = null;
        if(regions != null) {
            ApplicableRegionSet fromRegions = regions.getApplicableRegions(location);
            region = fromRegions.getRegions().stream().max(Comparator.comparing(ProtectedRegion::getPriority)).orElse(null);
        }
        return region;
    }
}
