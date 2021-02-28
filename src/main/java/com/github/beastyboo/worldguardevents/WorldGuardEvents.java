package com.github.beastyboo.worldguardevents;

import com.github.beastyboo.worldguardevents.events.EventFactory;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WorldGuardEvents {

    private final JavaPlugin plugin;
    private final Logger logger;

    public WorldGuardEvents(JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
    }

    protected void load() {
        if(getWorldGuard() == null || getWorldEdit() == null) {
            logger.warning("Please add WorldGuard and WorldEdit before using this plugin. Shutting down plugin");
            Bukkit.getPluginManager().disablePlugin(plugin);
        }

        Bukkit.getPluginManager().registerEvents(new EventFactory(), plugin);
        logger.info("Starting up WorldGuardEvents for 1.12.2");
    }

    protected void save() {
        logger.info("Closing WorldGuardEvents for 1.12.2");
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }

    private WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldEditPlugin) plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Logger getLogger() {
        return logger;
    }
}
