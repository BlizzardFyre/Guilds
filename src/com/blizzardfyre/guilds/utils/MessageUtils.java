package com.blizzardfyre.guilds.utils;

import org.bukkit.ChatColor;

import com.blizzardfyre.guilds.GuildMain;

public class MessageUtils {

	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', GuildMain.getInstance().getConfig().getString("prefix")) + " ";
	}

}
