package com.blizzardfyre.guilds.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.blizzardfyre.guilds.GuildMain;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.MessageUtils;

public class BankDeposit {

	public static void run(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "The console can't do that.");
			return;
		}

		if (!sender.hasPermission("guilds.user.bank.deposit")) {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission.");
			return;
		}

		User user = GuildMain.getInstance().getUser(sender.getName());

		if (!user.hasGuild()) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "You do not have a guild.");
			return;
		}

		Guild guild = GuildMain.getInstance().getGuild(user.getGuild());

		if (!user.canDo("DEPOSIT")) {
			sender.sendMessage(guild.getPrefix() + ChatColor.DARK_RED + "Your guild leader has not given you permission.");
			return;
		}

		double d = 0;

		try {
			d = Double.parseDouble(args[2]);
		} catch (NumberFormatException ex) {
			sender.sendMessage(MessageUtils.getPrefix() + ChatColor.RED + "That is not a number");
			return;
		}

		guild.deposit(d);
		GuildMain.getInstance().getEcon().withdrawPlayer(Bukkit.getOfflinePlayer(user.getUniqueId()), d);
		for (User u : GuildMain.getInstance().getUsers()) {
			if (u.getGuild().equalsIgnoreCase(guild.getName())) {
				u.getPlayer().sendMessage(guild.getPrefix() + ChatColor.GREEN + sender.getName() + ChatColor.GOLD + " has deposited $" + ChatColor.GREEN + d + ChatColor.GOLD + " into the guild bank.");
			}
		}
	}

}
