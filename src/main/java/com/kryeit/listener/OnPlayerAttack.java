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
