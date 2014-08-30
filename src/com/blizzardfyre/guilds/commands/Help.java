package com.blizzardfyre.guilds.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.blizzardfyre.guilds.utils.MessageUtils;

public class Help {

	public static void run(CommandSender sender, String[] args) {
		String prefix = MessageUtils.getPrefix();
		sender.sendMessage(ChatColor.RED + "=============GuildsHelp===========");
		sender.sendMessage(prefix + ChatColor.RED + "/guild create");
		sender.sendMessage(prefix + ChatColor.RED + "/guild disband");
		sender.sendMessage(prefix + ChatColor.RED + "/guild rename");
		sender.sendMessage(prefix + ChatColor.RED + "/guild bank");
		sender.sendMessage(prefix + ChatColor.RED + "/guild version");
		sender.sendMessage(prefix + ChatColor.RED + "/guild help");
	}

}
