package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.miscellanous.OxygenCooldown;
import org.bukkit.Bukkit;

public class onOxygenFixed {

    public static Boolean admin = false;
    public static Boolean o2 = false;

    public void OnOxygenFixed (int wich) {
        System.out.println(admin);
        System.out.println(o2);
        if(wich == 0) {
            admin = true;
        } else if (wich == 1) {
            o2 = true;
        }
        System.out.println(admin);
        System.out.println(o2);
        if(admin) {
            if (o2) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax("OXYGEN FIXED!","green"));
                OxygenCooldown oxygenCooldown = new OxygenCooldown(Bukkit.getPluginManager().getPlugin("AmongKryeitors"));
                oxygenCooldown.interruptCooldown();
                AmongKryeitors.is_otwo_sabotaged = false;
                AmongKryeitors.current_pin = "";
                AmongKryeitors.current_pin2 = "";
                admin = false;
                o2 = false;

            }
        }
    }
}
