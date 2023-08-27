package com.kryeit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {


    public static String parseLocation(Location location) {
        return location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
    }

    public static Location parseString(String string) {
        String[] parts = string.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        int z = Integer.parseInt(parts[2]);
        return new Location(Bukkit.getWorld("world"), x, y, z);
    }

    public static String getTitleCommandSyntax(String text, String color) {
        return "title {\"text\":\"" + text + "\",\"color\":\"" + color + "\"}";
    }

    public static Player getClosestPlayer(String coordinates) {
        UUID closest = null;
        double distance = 100;
        Location location = Utils.parseString(coordinates);

        List<UUID> playersInGame = new ArrayList<>();
        playersInGame.addAll(AmongKryeitors.crewmates);
        playersInGame.addAll(AmongKryeitors.impostors);

        for (UUID player : playersInGame) {
            Location playerloc = Bukkit.getPlayer(player).getLocation();

            double aux = playerloc.distance(location);

            if (aux < distance) {
                distance = aux; // Update the closest distance
                closest = player; // Update the closest player UUID
            }
        }
        if(closest == null) return null;
        return Bukkit.getPlayer(closest);
    }
}
