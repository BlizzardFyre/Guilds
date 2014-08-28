package com.blizzardfyre.guilds.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.objects.User;

public class ConnectionListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPlayedBefore()) {
			User.registerPlayer(e.getPlayer());
		}
		GuildMain.getInstance().addPlayer(new User(e.getPlayer()));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		GuildMain.getInstance().removePlayer(GuildMain.getInstance().getUser(e.getPlayer().getName()));
	}

}
