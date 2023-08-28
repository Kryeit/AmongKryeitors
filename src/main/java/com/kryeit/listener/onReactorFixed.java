package com.kryeit.listener;

import com.kryeit.Utils;
import com.kryeit.miscellanous.ReactorCooldown;
import org.bukkit.Bukkit;

public class onReactorFixed {

    public static void OnReactorFixed() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " +
                Utils.getTitleCommandSyntax("REACTOR FIXED!", "green"));
        ReactorCooldown reactorCooldown = new ReactorCooldown(Bukkit.getPluginManager().getPlugin("AmongKryeitors"));
        reactorCooldown.interruptCooldown();
    }

}
