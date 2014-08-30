package com.blizzardfyre.guilds.objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.blizzardfyre.guilds.GuildMain;

public class User {

	private UUID uuid;
	private File file;
	private YamlConfiguration yml;
	private int kills;
	private int deaths;
	private String guild;
	private ArrayList<String> perms = new ArrayList<String>();

	public User(Player player) {
		uuid = player.getUniqueId();
		file = new File(GuildMain.getInstance().getDataFolder() + File.separator + "players", uuid.toString() + ".yml");
		yml = YamlConfiguration.loadConfiguration(file);
		kills = yml.getInt("kills");
		deaths = yml.getInt("deaths");
		guild = yml.getString("guild");
		perms = (ArrayList<String>) yml.getStringList("perms");
	}

	public UUID getUniqueId() {
		return uuid;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public void addKill() {
		kills += 1;
		yml.set("kills", kills);
		saveYml();
	}

	public int getKills() {
		return kills;
	}

	public void addDeath() {
		deaths += 1;
		yml.set("deaths", deaths);
		saveYml();
	}

	public int getDeaths() {
		return kills;
	}

	public String getGuild() {
		return guild;
	}

	public void removeGuild() {
		if (guild.equalsIgnoreCase("none")) {
			return;
		}
		Guild tguild = GuildMain.getInstance().getGuild(getGuild());
		if (tguild == null) {
			return;
		}
		guild = "none";
		tguild.removeMember(this);
	}

	public void addGuild(String guild) {
		Guild tguild = GuildMain.getInstance().getGuild(guild);
		this.guild = tguild.getName();
		yml.set("guild", guild);
		try {
			yml.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (tguild.getLeader().toString().equalsIgnoreCase(getUniqueId().toString())) {
			return;
		}
		tguild.addMember(this);
	}

	public void addGuild(Guild guild) {
		guild.addMember(this);
		this.guild = guild.getName();
	}

	public void setGuild(String guild) {
		removeGuild();
		addGuild(guild);
	}

	public boolean hasGuild() {
		if (guild.equalsIgnoreCase("none")) {
			return false;
		}
		return true;
	}

	public boolean canDo(String action) {
		if (perms.contains(action)) {
			return true;
		}
		return false;
	}

	private void saveYml() {
		try {
			yml.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void registerPlayer(Player p) {
		File file = new File(GuildMain.getInstance().getDataFolder() + File.separator + "players", p.getUniqueId().toString() + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		yml.set("uuid", p.getUniqueId().toString());
		yml.set("kills", 0);
		yml.set("deaths", 0);
		yml.set("guild", "none");
		try {
			yml.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static ArrayList<User> loadOnlinePlayers() {
		ArrayList<User> players = new ArrayList<User>();
		for (Player player : Bukkit.getOnlinePlayers()) {
			players.add(new User(player));
		}
		return players;
	}

}
