package com.github.beastyboo.worldguardevents.events;

import com.github.beastyboo.worldguardevents.WorldGuardUtil;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

public class EventFactory implements Listener {

    private void move(PlayerMoveEvent event, Reason reason) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            ProtectedRegion fromRegion = WorldGuardUtil.getProtectedRegion(from);
            ProtectedRegion toRegion = WorldGuardUtil.getProtectedRegion(to);
            if (fromRegion != null)
                if (fromRegion != toRegion) {
                    RegionLeaveEvent leaveEvent = new RegionLeaveEvent(player, fromRegion, reason);
                    Bukkit.getPluginManager().callEvent(leaveEvent);
                    event.setCancelled(leaveEvent.isCancelled());
                }
            if (toRegion != null)
                if (toRegion != fromRegion) {
                    RegionEnterEvent enterEvent = new RegionEnterEvent(player, toRegion, reason);
                    Bukkit.getPluginManager().callEvent(enterEvent);
                    event.setCancelled(enterEvent.isCancelled());
                }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        ProtectedRegion region = WorldGuardUtil.getProtectedRegion(player.getLocation());
        if (region != null) {
            RegionLeaveEvent event = new RegionLeaveEvent(player, region, Reason.QUIT);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ProtectedRegion region = WorldGuardUtil.getProtectedRegion(player.getLocation());
        if (region != null) {
            RegionEnterEvent event = new RegionEnterEvent(player, region, Reason.JOIN);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        ProtectedRegion region = WorldGuardUtil.getProtectedRegion(player.getLocation());
        if (region != null) {
            RegionLeaveEvent event = new RegionLeaveEvent(player, region, Reason.DEATH);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        ProtectedRegion region = WorldGuardUtil.getProtectedRegion(e.getRespawnLocation());
        if (region != null) {
            RegionEnterEvent event = new RegionEnterEvent(player, region, Reason.RESPAWN);
            Bukkit.getPluginManager().callEvent(event);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        move(e, Reason.MOVE);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        move(e, Reason.TELEPORT);
    }

}
