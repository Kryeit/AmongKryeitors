package com.kryeit.global_variable_managers;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PersonKickedOut {

    public void PersonKickedOut(Player player) {

        if(AmongKryeitors.crewmates.contains(player.getUniqueId())) {
            AmongKryeitors.crewmates.remove(player.getUniqueId());
        } else {
            AmongKryeitors.impostors.remove(player.getUniqueId());
        }

        if(AmongKryeitors.engineer.equals(player) && AmongKryeitors.engineer!=null) {
            AmongKryeitors.engineer.remove();
        } else if (AmongKryeitors.shapeshifter.equals(player) && AmongKryeitors.shapeshifter!=null) {
            AmongKryeitors.shapeshifter.remove();

        }

        if(AmongKryeitors.kill_cooldowns.keySet().contains(player)) {
            AmongKryeitors.kill_cooldowns.remove(player);
        } else if (AmongKryeitors.sabotage_cooldowns.keySet().contains(player)) {
            AmongKryeitors.sabotage_cooldowns.remove(player);

        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tag " + player.getName() + " remove ingame");

    }

}
