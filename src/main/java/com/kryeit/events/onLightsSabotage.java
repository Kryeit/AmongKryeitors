package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class onLightsSabotage {

    public static void OnLightsSabotage() {
        int index = 0;
        while(index< AmongKryeitors.crewmates.toArray().length) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give "+  Bukkit.getPlayer(AmongKryeitors.crewmates.get(index)).getName() + " blindness 5000 1 true");
            index++;
        }

    }
    public static void LightsFixed() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect clear @a");
    }
}
