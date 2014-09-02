package com.blizzardfyre.guilds.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.MessageUtils;

public class ChatListener implements Listener {

	@EventHandler
	public void onTalk(AsyncPlayerChatEvent e) {
		if (GuildMain.getInstance().hasDeleter(e.getPlayer().getUniqueId())) {
			User user = GuildMain.getInstance().getUser(e.getPlayer().getUniqueId());
			if (e.getMessage().contains(user.getGuild())) {
				Guild.deleteGuild(user.getGuild());
				GuildMain.getInstance().removeDeleter(user.getUniqueId());
				e.getPlayer().sendMessage(MessageUtils.getPrefix() + ChatColor.GOLD + "Guild deleted.");
			} else {
				GuildMain.getInstance().removeDeleter(user.getUniqueId());
				e.getPlayer().sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "Delete aborted.");
			}
		}
	}

}
