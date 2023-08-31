package com.kryeit.events;

import com.kryeit.Utils;
import org.bukkit.Bukkit;

public class onCrewmatesWin {

    public void OnCrewmatesWin() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax("CREWMATES WIN","green"));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tag @a remove ingame");
    }

}
