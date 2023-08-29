package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import com.kryeit.events.onImpostorsWin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReactorCooldown {

    private static BukkitTask cooldownTask;
    private Plugin plugin;

    public ReactorCooldown(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startCooldown(int cooldownSeconds) {
        List<UUID> PlayersInGame = new ArrayList<>();
        PlayersInGame.addAll(AmongKryeitors.crewmates);
        PlayersInGame.addAll(AmongKryeitors.impostors);
        System.out.println("Cooldown started");
        // Cancel any existing cooldown task
        if (cooldownTask != null) {
            cooldownTask.cancel();
        }

        int[] remainingCooldown = { cooldownSeconds };
        int tickInterval = 20; // 1 second (20 ticks)

        cooldownTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (remainingCooldown[0] <= 0) {
                cooldownTask.cancel();
                for (UUID players : PlayersInGame) {
                    Bukkit.getPlayer(players).setLevel(0);
                }
                onImpostorsWin onImpostorsWin = new onImpostorsWin();
                onImpostorsWin.OnImpostorsWin();
            } else {
                for(UUID players2 : PlayersInGame) {
                    Bukkit.getPlayer(players2).setLevel(remainingCooldown[0]);
                }
                remainingCooldown[0]--;
            }
        }, 0, tickInterval);
    }

    public void interruptCooldown() {
        if (cooldownTask != null) {
            cooldownTask.cancel();
            cooldownTask = null;
        }
    }
}
