package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class GlobalShapeshiftTime {

    public void Shapeshifts() {
        AmongKryeitors.old_shapeshift_time = System.currentTimeMillis();
        BukkitTask cooldownTask;
        Plugin plugin = Bukkit.getPluginManager().getPlugin("AmongKryeitors");

        cooldownTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            boolean is_over = isOver(AmongKryeitors.old_shapeshift_time);
            if(is_over) {
                Come_back();
            }

        }, 0, 20);

    }

    public boolean isOver(Long time) {

        long elapsed_time = System.currentTimeMillis() - time;
        if(elapsed_time >= 30000) {
            return true;
        } else {
            return false;
        }

    }

    public void Come_back() {
        AmongKryeitors.shapeshifter.getInventory().setItem(39, AmongKryeitors.old_cap);
        GlobalLocalShapeshiftCooldown globalLocalShapeshiftCooldown = new GlobalLocalShapeshiftCooldown();
        globalLocalShapeshiftCooldown.resetPlayerTime(AmongKryeitors.shapeshifter);
    }

}
