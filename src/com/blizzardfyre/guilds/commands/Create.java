package com.blizzardfyre.guilds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.events.GuildCreateEvent;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.MessageUtils;

public class Create {

	public static void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "The console can't do that.");
			return;
		}

		if (!sender.hasPermission("guilds.user.create")) {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
			return;
		}

		if (GuildMain.getInstance().getUser(sender.getName()).hasGuild()) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "You are already in a guild.");
			return;
		}

		if (args.length < 2 || args.length > 4) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "/guild create [name]");
			return;
		}

		String name = "";
		for (String string : args) {
			if (string.equalsIgnoreCase("create")) {
				continue;
			}
			name += string + " ";
		}
		name = name.substring(0, name.length() - 1);

		if (Guild.exists(name)) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "A guild with that name already exists.");
			return;
		}

		User user = GuildMain.getInstance().getUser(sender.getName());

		Guild.createGuild(name, user);
		user.addGuild(name);
		GuildCreateEvent e = new GuildCreateEvent(GuildMain.getInstance().getGuild(name));
		Bukkit.getPluginManager().callEvent(e);

		sender.sendMessage(MessageUtils.getPrefix() + ChatColor.GOLD + "You have successfully made the guild " + ChatColor.BLUE + name + ChatColor.GOLD + ".");

	}

}
