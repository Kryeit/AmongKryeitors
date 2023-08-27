package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class onPlayerDeath implements Listener {

    @EventHandler
    public void OnDeath(PlayerDeathEvent event) {
        Player dead = event.getEntity();
        AmongKryeitors.crewmates.remove(dead.getUniqueId());

        Location loc = event.getEntity().getLocation();
        String place = Utils.parseLocation(loc);

        Random random = new Random();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "setblock " + place + " minecraft:player_head[rotation="
                + random.nextInt(9) + "]{ExtraType:\"" + event.getEntity().getName()
                + "\",SkullType:3}");
        AmongKryeitors.corpses.add(place);
        AmongKryeitors.currentDead.add(event.getEntity().getPlayer());
    }
}
