package com.blizzardfyre.guilds.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.MessageUtils;

public class Prefix {

	public static void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "The console can't do that.");
			return;
		}

		if (!sender.hasPermission("guilds.user.prefix")) {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
			return;
		}

		User user = GuildMain.getInstance().getUser(sender.getName());

		if (!user.hasGuild()) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "You do not have a guild.");
			return;
		}

		Guild guild = GuildMain.getInstance().getGuild(user.getGuild());

		if (!user.canDo("PREFIX")) {
			sender.sendMessage(guild.getPrefix() + ChatColor.DARK_RED + "Your guild leader has not given you permission.");
			return;
		}
	}

}
