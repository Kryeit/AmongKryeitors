package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class GlobalLocalSabotageCooldown {

    public void gameSetup() {
        System.out.println("PlayerTimeMap setup");

        for (UUID playerId : AmongKryeitors.impostors) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null) {
                AmongKryeitors.playerTimeMapSabotage.put(player, System.currentTimeMillis());
            }
        }
    }

    public void updatePlayerTime(Player player) {
        for(Map.Entry<Player, Long> entry : AmongKryeitors.playerTimeMapSabotage.entrySet()) {
            System.out.println(entry.getKey().toString() + " " + entry.getValue() + toString());
        }
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - AmongKryeitors.playerTimeMapSabotage.get(player);

        if (elapsedTime >= 30000) {
            if (!AmongKryeitors.playersOver30SecondsSabotage.contains(player)) {
                AmongKryeitors.playersOver30SecondsSabotage.add(player);
            }
        }
        System.out.println("Player Time Sabotage Updated");

    }

    public void resetPlayerTime(Player player) {
        System.out.println("Player time reset");
        AmongKryeitors.playerTimeMapSabotage.remove(player);
        AmongKryeitors.playerTimeMapSabotage.put(player, System.currentTimeMillis());
        AmongKryeitors.playersOver30SecondsSabotage.remove(player);
    }

    public List<Player> getPlayersOver30SecondsSabotage() {
        // Ensure playersOver30Seconds is not null before returning
        return AmongKryeitors.playersOver30SecondsSabotage != null ? AmongKryeitors.playersOver30SecondsSabotage : new ArrayList<>();
    }
    public void ResetCooldown() {
        AmongKryeitors.playersOver30SecondsSabotage.clear();
        AmongKryeitors.playerTimeMapSabotage.clear();
    }
}
