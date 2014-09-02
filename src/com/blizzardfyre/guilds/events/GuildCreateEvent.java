package com.blizzardfyre.guilds.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blizzardfyre.guilds.objects.Guild;

public class GuildCreateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Guild guild = null;

	public GuildCreateEvent(Guild guild) {
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

}
