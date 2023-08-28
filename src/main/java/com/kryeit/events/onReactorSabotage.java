package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.listener.onReactorFixed;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import com.kryeit.miscellanous.ReactorCooldown;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class onReactorSabotage implements Listener {

    @EventHandler
    public void OnPressurePlates (BlockRedstoneEvent event) {
        if (AmongKryeitors.is_reactor_sabotaged) {
            if (event.getOldCurrent() == 0) {
                if (Utils.parseLocation(event.getBlock().getLocation()).equals("1864 18 -4370")) {
                    onReactorFixed OnReactorFixed = new onReactorFixed();
                    OnReactorFixed.OnReactorFixed();
                }
            }
        }
    }

    public void OnReactorSabotage(Player player) {

        GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
        globalLocalSabotageCooldown.resetPlayerTime(player);

        AmongKryeitors.is_reactor_sabotaged = true;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " +
                Utils.getTitleCommandSyntax("REACTOR SABOTAGED!", "red"));
        ReactorCooldown reactorCooldown = new ReactorCooldown(Bukkit.getPluginManager().getPlugin("AmongKryeitors"));
        reactorCooldown.startCooldown(30);
    }
}
