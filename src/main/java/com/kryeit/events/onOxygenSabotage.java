package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import com.kryeit.miscellanous.OxygenCooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class onOxygenSabotage {

    public void OnOxygenSabotage(Player player) {

        GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
        globalLocalSabotageCooldown.resetPlayerTime(player);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a " + Utils.getTitleCommandSyntax("OXYGEN SABOTAGED!","red"));
        OxygenCooldown oxygenCooldown = new OxygenCooldown(Bukkit.getPluginManager().getPlugin("AmongKryeitors"));
        oxygenCooldown.startCooldown(60);
        AmongKryeitors.is_otwo_sabotaged = true; // Actually should be named is_oxygen_sabotaged
    }

}
