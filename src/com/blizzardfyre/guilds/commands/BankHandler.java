package com.blizzardfyre.guilds.commands;

import org.bukkit.command.CommandSender;

import com.blizzardfyre.guilds.utils.MessageUtils;

public class BankHandler {

	public static void run(CommandSender sender, String[] args) {
		if (args.length == 1) {
			help(sender);
			return;
		}

		if (args[1].equalsIgnoreCase("deposit")) {
			BankDeposit.run(sender, args);
			return;
		} else if (args[1].equalsIgnoreCase("withdraw")) {
			BankWithdraw.run(sender, args);
			return;
		} else if (args[1].equalsIgnoreCase("balance")) {
			BankBalance.run(sender, args);
			return;
		} else {
			help(sender);
			return;
		}
	}

	private static void help(CommandSender sender) {
		sender.sendMessage(MessageUtils.getPrefix() + "/guild bank deposit");
		sender.sendMessage(MessageUtils.getPrefix() + "/guild bank withdraw");
		sender.sendMessage(MessageUtils.getPrefix() + "/guild bank balance");
	}

}
