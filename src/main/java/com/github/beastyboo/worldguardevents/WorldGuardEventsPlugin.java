package com.github.beastyboo.worldguardevents;

import org.bukkit.plugin.java.JavaPlugin;

public final class WorldGuardEventsPlugin extends JavaPlugin {

    private WorldGuardEvents core;

    @Override
    public void onEnable() {
        core = new WorldGuardEvents(this);
        core.load();

    }

    @Override
    public void onDisable() {
        core.save();
        core = null;
    }

}
