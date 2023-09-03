package com.kryeit.global_variable_managers;

import com.kryeit.AmongKryeitors;
import com.kryeit.miscellanous.GlobalLocalKillCooldown;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import com.kryeit.miscellanous.GlobalLocalShapeshiftCooldown;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ResetGame {

    public void resetGame() {
        AmongKryeitors.impostors.clear();
        AmongKryeitors.crewmates.clear();
        AmongKryeitors.currentDead.clear();
        AmongKryeitors.corpses.clear();

        AmongKryeitors.current_pin = "";
        AmongKryeitors.current_pin2="";

        AmongKryeitors.is_otwo_sabotaged = false;

        AmongKryeitors.is_reactor_sabotaged = false;
        AmongKryeitors.kill_cooldowns.clear();
        AmongKryeitors.sabotage_cooldowns.clear();

        AmongKryeitors.engineer.remove();
        AmongKryeitors.shapeshifter.remove();

        AmongKryeitors.player_task_list.clear();
        AmongKryeitors.vote_per_player.clear();

        AmongKryeitors.has_shapeshifted = false;


        AmongKryeitors.old_shapeshift_time = 0L;
        GlobalLocalKillCooldown globalLocalKillCooldown = new GlobalLocalKillCooldown();
        globalLocalKillCooldown.playerTimeMap.clear();
        globalLocalKillCooldown.playersOver30Seconds.clear();

        GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
        globalLocalSabotageCooldown.playerTimeMap.clear();
        globalLocalKillCooldown.playersOver30Seconds.clear();

        GlobalLocalShapeshiftCooldown globalLocalShapeshiftCooldown = new GlobalLocalShapeshiftCooldown();
        globalLocalShapeshiftCooldown.playerTimeMap.clear();
        globalLocalShapeshiftCooldown.playersOver30Seconds.clear();
    }

}
