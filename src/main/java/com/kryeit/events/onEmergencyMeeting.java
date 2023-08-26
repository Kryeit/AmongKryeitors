package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.command.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class onEmergencyMeeting {
    public static void OnEmergencyMeeting(Boolean reported) {
        StartGame startGame = new StartGame();
        if(!reported) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a title {\"text\":\"EMERGENCY MEETING\",\"color\":\"gold\"");
        }
        List<String> seats = List.of("1790 12 -4357",
                "1789 12 -4357",
                "1788 12 -4356",
                "1787 12 -4355",
                "1786 12 -4354",
                "1786 12 -4353",
                "1787 12 -4352",
                "1788 12 -4351",
                "1789 12 -4350",
                "1790 12 -4350",
                "1791 12 -4351",
                "1792 12 -4352",
                "1793 12 -4353",
                "1793 12 -4354",
                "1792 12 -4355",
                "1791 12 -4356");
        List<UUID> PlayersInGame = new ArrayList<>();
        for (UUID crewmate : AmongKryeitors.crewmates) {
            PlayersInGame.add(crewmate);
        }
        for(UUID impostor : AmongKryeitors.impostors) {
            PlayersInGame.add(impostor);
        }
        Collections.shuffle(PlayersInGame);
        int index = 0;
        while(index<PlayersInGame.toArray().length) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + Bukkit.getPlayer(PlayersInGame.get(index)).getName() + " " + seats.get(index));
            index++;
        }

    }
}
