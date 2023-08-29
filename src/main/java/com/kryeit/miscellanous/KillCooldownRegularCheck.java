package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KillCooldownRegularCheck {

    private static BukkitTask cooldownTask;
    private Plugin plugin = Bukkit.getPluginManager().getPlugin("AmongKryeitors");
    GlobalLocalKillCooldown globalLocalKillCooldown = new GlobalLocalKillCooldown();


    public void startCooldown() {

        cooldownTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
           for(UUID element : AmongKryeitors.impostors) {
               globalLocalKillCooldown.updatePlayerTime(Bukkit.getPlayer(element));
               if(globalLocalKillCooldown.getPlayersOver30Seconds().contains(Bukkit.getPlayer(element))) {
                   Bukkit.getPlayer(element).getInventory().setItem(0, new ItemStack(Material.NETHERITE_SWORD));
               }
           }
        }, 0, 20);
    }
}
