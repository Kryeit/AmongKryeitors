package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.kryeit.events.onEmergencyMeeting.*;

public class onBodyReported {
    public static boolean OnBodyReported(Player player) {
        int index = 0;
        while (index < AmongKryeitors.bodies.toArray().length) {
            String[] parts = AmongKryeitors.bodies.get(index).split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int z = Integer.parseInt(parts[2]);
            double xd = x;
            double yd = y;
            double zd = z;
            Location lmfao = new Location(Bukkit.getWorld("world"), xd, yd, zd);
            if (player.getLocation().distance(lmfao) < 5) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"BODY REPORTED\",\"color\":\"gold\"}");
                onEmergencyMeeting.OnEmergencyMeeting(true);
                return true;
            }
            index++;
        } return false;
    }
}
