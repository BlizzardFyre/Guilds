package com.blizzardfyre.guilds;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.blizzardfyre.guilds.commands.CommandHandler;
import com.blizzardfyre.guilds.listeners.ConnectionListener;
import com.blizzardfyre.guilds.objects.Guild;
import com.blizzardfyre.guilds.objects.User;
import com.blizzardfyre.guilds.utils.FileUtils;

public class GuildMain extends JavaPlugin {

	private ArrayList<Guild> guilds = null;
	private ArrayList<User> users = new ArrayList<User>();

	@SuppressWarnings("deprecation")
	public void onEnable() {
		saveDefaultConfig();
		FileUtils.getGuildFolder();
		FileUtils.getPlayersFolder();
		loadGuilds();
		getCommand("guild").setExecutor(new CommandHandler());
		Bukkit.getPluginManager().registerEvents(new ConnectionListener(), this);
		for (Player p : Bukkit.getOnlinePlayers()) {
			users.add(new User(p));
		}
	}

	public static GuildMain getInstance() {
		return (GuildMain) Bukkit.getPluginManager().getPlugin("Guilds");
	}

	public ArrayList<Guild> getGuilds() {
		return guilds;
	}

	public Guild getGuild(String name) {
		for (Guild guild : getGuilds()) {
			if (guild.getName().equalsIgnoreCase(name)) {
				return guild;
			}
		}
		return null;
	}

	public void addGuild(Guild guild) {
		guilds.add(guild);
	}

	public void removeGuild(Guild guild) {
		guilds.remove(guild);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public User getUser(String name) {
		for (User player : getUsers()) {
			if (Bukkit.getPlayer(player.getUniqueId()).getName().equalsIgnoreCase(name)) {
				return player;
			}
		}
		return null;
	}

	public User getUser(UUID uuid) {
		for (User player : getUsers()) {
			if (player.getUniqueId().toString().equals(uuid.toString())) {
				return player;
			}
		}
		return null;
	}

	public void addPlayer(User player) {
		users.add(player);
	}

	public void removePlayer(User player) {
		users.remove(player);
	}

	public void loadGuilds() {
		guilds = Guild.loadGuilds();
	}

}
