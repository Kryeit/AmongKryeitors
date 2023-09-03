package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.global_variable_managers.ResetGame;
import org.bukkit.Bukkit;

public class onImpostorsWin {

    public void OnImpostorsWin() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax("IMPOSTORS WIN","red"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tag @a remove ingame");
        ResetGame resetGame = new ResetGame();
        resetGame.resetGame();
    }

    public void doImpostorsWin() {
        if(AmongKryeitors.impostors.toArray().length==AmongKryeitors.crewmates.toArray().length) OnImpostorsWin();
    }

}
