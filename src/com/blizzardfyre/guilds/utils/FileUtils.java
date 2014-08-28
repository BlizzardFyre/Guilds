package com.blizzardfyre.guilds.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import com.blizzardfyre.guilds.GuildMain;

public class FileUtils {

	public static File getGuildFolder() {
		File folder = new File(GuildMain.getInstance().getDataFolder() + File.separator + "guilds");
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}

	public static File getPlayersFolder() {
		File folder = new File(GuildMain.getInstance().getDataFolder() + File.separator + "players");
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}

	public static YamlConfiguration getBaseGuildFile() {
		return YamlConfiguration.loadConfiguration(new File(GuildMain.getInstance().getDataFolder(), "guilds.yml"));
	}

}
