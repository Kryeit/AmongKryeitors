package com.kryeit.events;

import com.kryeit.Utils;
import org.bukkit.Bukkit;

public class onCrewmatesWin {

    public void OnCrewmatesWin() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " + Utils.getTitleCommandSyntax("CREWMATES WIN","green"));
    }

}
