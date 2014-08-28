package com.blizzardfyre.guilds.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.objects.User;
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

	public User getCreater() {
		return GuildMain.getInstance().getUser(guild.getLeader());
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
