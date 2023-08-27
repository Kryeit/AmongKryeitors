package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class onBodyReported {
    public static boolean OnBodyReported(Player player) {
        int index = 0;
        while (index < AmongKryeitors.corpses.toArray().length) {
            Location loc = Utils.parseString(AmongKryeitors.corpses.get(index));

            if (player.getLocation().distance(loc) < 5) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " +
                        Utils.getTitleCommandSyntax("BODY REPORTED", "gold"));
                onEmergencyMeeting.OnEmergencyMeeting(true);
                return true;
            }
            index++;
        } return false;
    }
}
