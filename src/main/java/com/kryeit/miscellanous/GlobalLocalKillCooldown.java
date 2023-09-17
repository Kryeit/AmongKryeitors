package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GlobalLocalKillCooldown {

    public void gameSetup() {

        // Clear the existing data when setting up the game

        for (UUID playerId : AmongKryeitors.impostors) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null) {
                System.out.println("PlayerTimeMap setup");
                AmongKryeitors.playerTimeMapKill.put(player, System.currentTimeMillis());
            }
        }
    }

    public void updatePlayerTime(Player player) {

        for(Map.Entry<Player, Long> entry : AmongKryeitors.playerTimeMapKill.entrySet()) {
            System.out.println(entry.getKey().toString() + " " + entry.getValue() + toString());
        }

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - AmongKryeitors.playerTimeMapKill.get(player);

        if (elapsedTime >= 30000) {
            if (!AmongKryeitors.playersOver30SecondsKill.contains(player)) {
                AmongKryeitors.playersOver30SecondsKill.add(player);
            }
        }
        System.out.println("Player Time Kill Updated");

    }

    public void resetPlayerTime(Player player) {
        System.out.println("Player time reset");
        AmongKryeitors.playerTimeMapKill.remove(player);
        AmongKryeitors.playerTimeMapKill.put(player, System.currentTimeMillis());
        AmongKryeitors.playersOver30SecondsKill.remove(player);
        player.getInventory().setItem(0, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
    }

    public List<Player> getPlayersOver30SecondsKill() {
        // Ensure playersOver30Seconds is not null before returning
        return AmongKryeitors.playersOver30SecondsKill != null ? AmongKryeitors.playersOver30SecondsKill : new ArrayList<>();
    }
    public void ResetCooldown() {
        AmongKryeitors.playersOver30SecondsKill.clear();
        AmongKryeitors.playerTimeMapKill.clear();
    }
}
