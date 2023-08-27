package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.miscellanous.OxygenCooldown;
import org.bukkit.Bukkit;

public class onOxygenSabotage {

    public void OnOxygenSabotage() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a " + Utils.getTitleCommandSyntax("OXYGEN SABOTAGED!","red"));
        OxygenCooldown oxygenCooldown = new OxygenCooldown(Bukkit.getPluginManager().getPlugin("AmongKryeitors"));
        oxygenCooldown.startCooldown(60);
        AmongKryeitors.is_otwo_sabotaged = true; // Actually should be named is_oxygen_sabotaged
    }

}
