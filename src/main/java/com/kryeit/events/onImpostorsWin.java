package com.kryeit.events;

import com.kryeit.Utils;
import org.bukkit.Bukkit;

public class onImpostorsWin {

    public void OnImpostorsWin() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a" + Utils.getTitleCommandSyntax("IMPOSTORS WIN","red"));
    }

}
