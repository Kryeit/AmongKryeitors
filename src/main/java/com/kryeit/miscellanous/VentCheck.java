package com.kryeit.miscellanous;


import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.Objects;

public class VentCheck implements Listener {


    List<String> vents = List.of("1836 10 -4349",
            "1858 10 -4365",
            "1856 10 -4377",
            "1826 10 -4379",
            "1836 10 -4398",
            "1815 10 -4382",
            "1819 10 -4370",
            "1778 10 -4388",
            "1758 10 -4401",
            "1757 10 -4377",
            "1736 10 -4378",
            "1736 10 -4367",
            "1758 10 -4344",
            "1775 10 -4358");

    @EventHandler
    public void OnVentClicked (PlayerInteractEvent event) {


        if(vents.contains(Utils.parseLocation(Objects.requireNonNull(event.getClickedBlock()).getLocation()))) {
            if(AmongKryeitors.impostors.contains(event.getPlayer().getUniqueId())) {

            } else if (AmongKryeitors.engineer.equals(event.getPlayer())) {

            } else {
                event.setCancelled(true);
            }
        }
    }

}


