package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.command.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class onEmergencyMeeting {

    public static List<String> seats = List.of("1790 12 -4357",
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

    public static void OnEmergencyMeeting(Boolean reported) {

        if(!reported) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " +
                    Utils.getTitleCommandSyntax("EMERGENCY MEETING", "gold"));
        }

        List<UUID> playersInGame = new ArrayList<>();
        playersInGame.addAll(AmongKryeitors.crewmates);
        playersInGame.addAll(AmongKryeitors.impostors);
        Collections.shuffle(playersInGame);

        int index = 0;
        while(index<playersInGame.toArray().length) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp " + Bukkit.getPlayer(playersInGame.get(index)).getName() + " " + seats.get(index));
            index++;
        }

    }
}
