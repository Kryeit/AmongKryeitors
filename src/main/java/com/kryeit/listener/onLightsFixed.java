package com.kryeit.listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;

public class onLightsFixed {

    public static void OnLightsFixed() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax("LIGHTS FIXED","green"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect clear @a[tag=ingame]");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock 1817 14 -4386 air");
    }

}
