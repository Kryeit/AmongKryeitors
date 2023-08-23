package com.kryeit.listener;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.kryeit.claiming.ClaimUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class OnPlayerAttack implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        System.out.println("EntityDamageByEntityEvent event");
        Entity attacked = event.getEntity();
        Entity attacker = event.getDamager();
        if (attacked instanceof Player victim && attacker instanceof Player perpetrator) {

            // victim is the one attacked
            // perpetrator is the one attacking

            if(ClaimUtils.isInside(victim) && ClaimUtils.isInside(perpetrator)) {

                // Do stuff

                if(false) {

                    // this cancels the damage
                    event.setCancelled(true);
                }
            }
        }
    }
}

//TODO : Make the GUI for sabotage, wich will include reactor sabotage, light sabotage and oxygen sabotage
//TODO : Make a task giving system that gives tasks randomly to each player.
//TODO : Make a way to see when a certain player completes a certain task
//TODO : Make the basics for each event listed in the odt file
//TODO : Make a vote GUI to vote
//TODO : Make the system that manages players, who is what and who is dead, etc...
//TODO : Make the startgame and endgame logic
//TODO : Make a diagram representing the different event logic and the order it will server as gamemaster

