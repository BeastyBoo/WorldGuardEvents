package com.github.beastyboo.worldguardevents.events;

import com.github.beastyboo.worldguardevents.events.region.RegionEvent;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

public class RegionEnterEvent extends RegionEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;

    private final Reason reason;

    private final boolean cancellable;

    private boolean cancelled;

    public RegionEnterEvent(Player player, ProtectedRegion region, Reason reason) {
        super(region);
        this.player = player;
        this.reason = reason;
        this.cancellable = (reason != Reason.QUIT && reason != Reason.JOIN && reason != Reason.RESPAWN);
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Reason getReason() {
        return this.reason;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancellable() {
        return this.cancellable;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
