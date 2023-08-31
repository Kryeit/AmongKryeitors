package com.kryeit.listener;

import com.kryeit.Utils;
import com.kryeit.miscellanous.EmergencyCooldown;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class EmergencyButtons implements Listener {

    @EventHandler
    public void OnEmergencyButtons(BlockRedstoneEvent event) {
        if(Utils.parseLocation(event.getBlock().getLocation()).equals("1789 10 -4354")) {
            if(event.getOldCurrent() == 0) {
                EmergencyCooldown emergencyCooldown = new EmergencyCooldown();
                emergencyCooldown.CheckCooldown();
            }
        }
    }

}
