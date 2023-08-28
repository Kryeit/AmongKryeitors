package com.kryeit.listener;

import com.kryeit.miscellanous.GlobalLocalKillCooldown;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerKillPlayer implements Listener {

    @EventHandler
    public void playerKillplayer(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType().equals(EntityType.PLAYER)) {
            if(event.getEntity().getType().equals(EntityType.PLAYER)) {
                if (event.getEntity().isDead()) {
                    GlobalLocalKillCooldown globalLocalKillCooldown = new GlobalLocalKillCooldown();

                }
            }
        }
    }

}
