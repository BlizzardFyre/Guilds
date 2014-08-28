package com.blizzardfyre.guilds.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			Help.run(sender, args);
			return true;
		}

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("version")) {
				Version.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("create")) {
				Create.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("disband")) {
				Disband.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("rename")) {
				Rename.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("bank")) {
				BankHandler.run(sender, args);
				return true;
			} else if (args[0].equalsIgnoreCase("derp")) {
				sender.sendMessage("uh");
				return true;
			}
			sender.sendMessage("t");
			return true;
		}

		return false;
	}

}
