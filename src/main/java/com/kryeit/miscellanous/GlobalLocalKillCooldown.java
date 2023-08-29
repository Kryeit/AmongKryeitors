package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class GlobalLocalKillCooldown {
    private static HashMap<Player, Long> playerTimeMap = new HashMap<>();
    private static List<Player> playersOver30Seconds = new ArrayList<>();

    public void gameSetup() {
        System.out.println("PlayerTimeMap setup");

        // Clear the existing data when setting up the game
        playerTimeMap.clear();
        playersOver30Seconds.clear();

        for (UUID playerId : AmongKryeitors.crewmates) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null) {
                playerTimeMap.put(player, System.currentTimeMillis());
            }
        }
    }

    public void updatePlayerTime(Player player) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - playerTimeMap.getOrDefault(player, currentTime);

        if (elapsedTime >= 30000) {
            if (!playersOver30Seconds.contains(player)) {
                playersOver30Seconds.add(player);
            }
        }

    }

    public void resetPlayerTime(Player player) {
        System.out.println("Player time reset");
        playerTimeMap.remove(player);
        playerTimeMap.put(player, System.currentTimeMillis());
        playersOver30Seconds.remove(player);
    }

    public List<Player> getPlayersOver30Seconds() {
        // Ensure playersOver30Seconds is not null before returning
        return playersOver30Seconds != null ? playersOver30Seconds : new ArrayList<>();
    }
}
