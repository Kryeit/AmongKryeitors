package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.global_variable_managers.PersonKickedOut;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class onPlayerDeath implements Listener {

    @EventHandler
    public void OnDeath(PlayerDeathEvent event) {
        Player dead = event.getEntity();
        PersonKickedOut personKickedOut = new PersonKickedOut();
        personKickedOut.PersonKickedOut(dead);

        Location loc = event.getEntity().getLocation();
        String place = Utils.parseLocation(loc);

        int y_increment = 0;

        if(loc.getBlock().getType()!= Material.AIR) {
            y_increment++;
        }

        List<Integer> coos = new ArrayList<>();

        for(String element : place.split(" ")) {
            coos.add(Integer.parseInt(element));
        }
        Integer new_y = coos.get(1);
        new_y++;
        String new_place = coos.get(0).toString() + " " + new_y.toString() + " " + coos.get(2).toString();

        Random random = new Random();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "setblock " + new_place + " minecraft:player_head[rotation="
                + random.nextInt(9) + "]{ExtraType:\"" + event.getEntity().getName()
                + "\",SkullType:3}");
        AmongKryeitors.corpses.add(place);
    }
}
