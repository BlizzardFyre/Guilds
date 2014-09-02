package com.blizzardfyre.guilds.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blizzardfyre.guilds.objects.Guild;

public class GuildDisbandEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Guild guild = null;

	private boolean cancelled = false;

	public GuildDisbandEvent(Guild guild) {
		this.guild = guild;
	}

	public Guild getGuild() {
		return guild;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean bool) {
		cancelled = bool;
	}

}
