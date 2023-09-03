package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.global_variable_managers.ResetGame;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class onCrewmatesWin {

    public void OnCrewmatesWin() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax("CREWMATES WIN","green"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tag @a remove ingame");
        ResetGame resetGame = new ResetGame();
        resetGame.resetGame();
    }

    public void doCrewmatesWin() {
        if(AmongKryeitors.impostors.isEmpty()) OnCrewmatesWin();
    }

}
