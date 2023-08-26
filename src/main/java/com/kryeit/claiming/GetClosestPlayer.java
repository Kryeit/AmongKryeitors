package com.kryeit.claiming;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GetClosestPlayer {

    public static UUID getClosestPlayer(String coordinates) {
        int index = 0;
        UUID closest = null; // Initialize closest with a default value
        Double distance = 100.0;
        String[] parts = coordinates.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        int z = Integer.parseInt(parts[2]);

        double xd = x;
        double xy = y;
        double xz = z;

        while (index < AmongKryeitors.crewmates.toArray().length) {
            Location playerloc = Bukkit.getPlayer(AmongKryeitors.crewmates.get(index)).getLocation();

            double dist = playerloc.distance(new Location(Bukkit.getWorld("world"), xd, xy, xz));

            if (dist < distance) {
                distance = dist; // Update the closest distance
                closest = AmongKryeitors.crewmates.get(index); // Update the closest player UUID
            }

            index++;
        }
        index=0;
        while (index < AmongKryeitors.impostors.toArray().length) {
            Location playerloc = Bukkit.getPlayer(AmongKryeitors.crewmates.get(index)).getLocation();

            double dist = playerloc.distance(new Location(Bukkit.getWorld("world"), xd, xy, xz));

            if (dist < distance) {
                distance = dist; // Update the closest distance
                closest = AmongKryeitors.crewmates.get(index); // Update the closest player UUID
            }

            index++;
        }

        return closest;
    }
}
