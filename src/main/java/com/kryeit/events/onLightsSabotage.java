package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class onLightsSabotage {

    public static void OnLightsSabotage(Player player) {

        GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
        globalLocalSabotageCooldown.resetPlayerTime(player);


        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a[tag=ingame] "+ Utils.getTitleCommandSyntax("LIGHTS SABOTAGED!","red"));
        int index = 0;
        while(index< AmongKryeitors.crewmates.toArray().length) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give "+  Bukkit.getPlayer(AmongKryeitors.crewmates.get(index)).getName() + " blindness 5000 1 true");
            index++;
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock 1817 14 -4386 minecraft:redstone_block");

    }
}
