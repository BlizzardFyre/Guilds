package com.blizzardfyre.guilds.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.events.GuildDisbandEvent;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.MessageUtils;

public class Disband {

	public static void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "The console can't do that.");
			return;
		}

		if (!sender.hasPermission("guilds.user.disband")) {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
			return;
		}

		User user = GuildMain.getInstance().getUser(sender.getName());

		if (!user.hasGuild()) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "You do not have a guild.");
			return;
		}

		Guild guild = GuildMain.getInstance().getGuild(user.getGuild());

		if (!guild.getLeader().toString().equals(user.getUniqueId())) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "You are not the leader of your guild.");
			return;
		}

		if (args.length != 1) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "/guild disband [name]");
			return;
		}

		GuildDisbandEvent e = new GuildDisbandEvent(guild);

		if (!e.isCancelled()) {
			sender.sendMessage(guild.getPrefix() + ChatColor.RED + "Type your guild name in chat to confirm.");
			GuildMain.getInstance().addDeleter(user);
		}

	}

}
