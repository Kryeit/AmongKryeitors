package com.kryeit.listener;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.griefdefender.lib.kyori.adventure.text.BlockNBTComponent;
import com.kryeit.AmongKryeitors;
import com.kryeit.claiming.ClaimUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.permissions.ServerOperator;

import java.util.List;
import java.util.Random;
import java.util.UUID;
public class onPlayerDeath implements Listener {

    @EventHandler
    public void OnDeath(PlayerDeathEvent event) {

        System.out.println("onPlayerDeath Event");

        Player dead = event.getEntity();
        AmongKryeitors.crewmates.remove(dead.getUniqueId());
        Location coos = event.getEntity().getLocation();
        int Xpos = (int) coos.getX();
        int Ypos = (int) coos.getY();
        int Zpos = (int) coos.getZ();
        Random random = new Random();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "setblock " +Xpos +" " + Ypos +" "+ Zpos + " minecraft:player_head[rotation=" + random.nextInt(9) + "]{ExtraType:\""+ event.getEntity().getName() + "\",SkullType:3}");
        String place = Xpos + " " + Ypos + " " + Zpos;
        AmongKryeitors.bodies.add(place);
        AmongKryeitors.currentDead.add(event.getEntity().getPlayer());
    }
}
