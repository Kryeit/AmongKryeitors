package com.kryeit.global_variable_managers;

import com.kryeit.AmongKryeitors;
import org.bukkit.entity.Player;

public class PersonKickedOut {

    public void PersonKickedOut(Player player) {

        if(AmongKryeitors.crewmates.contains(player)) {
            AmongKryeitors.crewmates.remove(player);
        } else {
            AmongKryeitors.impostors.remove(player);
        }

        if(AmongKryeitors.engineer.equals(player)) {
            AmongKryeitors.engineer = null;
        } else if (AmongKryeitors.shapeshifter.equals(player)) {
            AmongKryeitors.shapeshifter = null;

        }

        if(AmongKryeitors.kill_cooldowns.keySet().contains(player)) {
            AmongKryeitors.kill_cooldowns.remove(player);
        } else if (AmongKryeitors.sabotage_cooldowns.keySet().contains(player)) {
            AmongKryeitors.sabotage_cooldowns.remove(player);

        }

    }

}
