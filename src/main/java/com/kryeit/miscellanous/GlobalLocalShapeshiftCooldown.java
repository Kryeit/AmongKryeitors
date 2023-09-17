package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.entity.Player;

import java.util.*;

public class GlobalLocalShapeshiftCooldown {

    public void gameSetup() {
        System.out.println("PlayerTimeMap setup");

        // Clear the existing data when setting up the game
        AmongKryeitors.playerTimeMapShapeshift.clear();
        AmongKryeitors.playersOver30SecondsShapeshift.clear();

        Player player = AmongKryeitors.shapeshifter;

        AmongKryeitors.playerTimeMapShapeshift.put(player, System.currentTimeMillis());
    }

    public void updatePlayerTime(Player player) {
        for(Map.Entry<Player, Long> entry : AmongKryeitors.playerTimeMapShapeshift.entrySet()) {
            System.out.println(entry.getKey().toString() + " " + entry.getValue() + toString());
        }
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - AmongKryeitors.playerTimeMapShapeshift.get(player);

        if (elapsedTime >= 30000) {
            if (!AmongKryeitors.playersOver30SecondsShapeshift.contains(player)) {
                AmongKryeitors.playersOver30SecondsShapeshift.add(player);
            }
        }
        System.out.println("Player Time Shapeshift Updated");

    }

    public void resetPlayerTime(Player player) {
        System.out.println("Player time reset");
        AmongKryeitors.playerTimeMapShapeshift.remove(player);
        AmongKryeitors.playerTimeMapShapeshift.put(player, System.currentTimeMillis());
        AmongKryeitors.playersOver30SecondsShapeshift.remove(player);
    }

    public List<Player> getPlayersOver30SecondsShapeshift() {
        // Ensure playersOver30Seconds is not null before returning
        return AmongKryeitors.playersOver30SecondsShapeshift != null ? AmongKryeitors.playersOver30SecondsShapeshift : new ArrayList<>();
    }
    public void ResetCooldown() {
        AmongKryeitors.playerTimeMapShapeshift.clear();
        AmongKryeitors.playersOver30SecondsShapeshift.clear();
    }
}
