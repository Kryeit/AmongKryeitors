package com.kryeit.command;

import com.kryeit.AmongKryeitors;
import com.kryeit.claiming.ClaimUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int impostorAmount;
        List<Player> playersInGame = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (ClaimUtils.isInside(p)) playersInGame.add(p);
        }

        if (playersInGame.size() < 3) {
            AmongKryeitors.crewmates.add(playersInGame.get(0).getUniqueId());
            System.out.println(AmongKryeitors.crewmates);
        }
        if (playersInGame.size() <= 5) {
            impostorAmount = 1;
        } else if (playersInGame.size() <= 8) {
            impostorAmount = 2;
        } else impostorAmount = 3;

        List<Player> shuffledPlayers = new ArrayList<>(playersInGame);
        Random random = new Random();
        for (int i = shuffledPlayers.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Player temp = shuffledPlayers.get(i);
            shuffledPlayers.set(i, shuffledPlayers.get(j));
            shuffledPlayers.set(j, temp);
        }

        // Add the first 'impostorAmount' players to chosenPlayers
        for (int i = 0; i < impostorAmount; i++) {
            AmongKryeitors.impostors.add(shuffledPlayers.get(i).getUniqueId());
        }

        for (Player p : playersInGame) {
            if(!AmongKryeitors.impostors.contains(p.getUniqueId())) AmongKryeitors.crewmates.add(p.getUniqueId());
        }

        return true;
    }
}
