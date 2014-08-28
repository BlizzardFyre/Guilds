package com.blizzardfyre.guilds.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;

public class LocationUtils {

	public static Chunk stringToChunk(String string) {
		String[] splits = string.split(";");
		return Bukkit.getWorld(splits[0]).getChunkAt(Integer.valueOf(splits[1]), Integer.valueOf(splits[2]));
	}

	public static String chunkToString(Chunk chunk) {
		return chunk.getWorld().getName() + ";" + chunk.getX() + ";" + chunk.getZ();
	}

	public static String locationToString(Location loc) {
		return loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch();
	}

	public static Location stringToLocation(String str) {
		String[] strar = str.split(";");
		Location newLoc = new Location(Bukkit.getWorld(strar[0]), Double.valueOf(strar[1]).doubleValue(), Double.valueOf(strar[2]).doubleValue(), Double.valueOf(strar[3]).doubleValue(), Float.valueOf(strar[4]).floatValue(), Float.valueOf(strar[5])
				.floatValue());
		return newLoc.clone();
	}

	public static ArrayList<String> convertChunks(ArrayList<Chunk> chunks) {
		ArrayList<String> schunks = new ArrayList<String>();
		for (Chunk chunk : chunks) {
			schunks.add(chunkToString(chunk));
		}
		return schunks;
	}

}
