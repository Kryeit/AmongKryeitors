package com.kryeit.command;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.claiming.ClaimUtils;
import com.kryeit.miscellanous.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class StartGame implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int impostorAmount;
        List<Player> playersInGame = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (ClaimUtils.isInside(p)) playersInGame.add(p);
        }

        if (playersInGame.size() < 3) {
            for(Player element : playersInGame) {
                AmongKryeitors.crewmates.add(element.getUniqueId());
                System.out.println(AmongKryeitors.crewmates);
            }
        }
        if (playersInGame.size() <= 4) {
            impostorAmount = 1;
        } else if (playersInGame.size() <= 8) {
            impostorAmount = 2;
        } else impostorAmount = 3;

        Random random = new Random();

        int index = 0;
        while(index!=impostorAmount) {
            int new_person = random.nextInt(playersInGame.size());
            AmongKryeitors.impostors.add(playersInGame.get(new_person).getUniqueId());
            index++;
        }

        // Add the first 'impostorAmount' players to chosenPlayers

        for (Player p : playersInGame) {
            if(!AmongKryeitors.impostors.contains(p.getUniqueId())) AmongKryeitors.crewmates.add(p.getUniqueId());
        }
        AmongKryeitors.engineer = Bukkit.getPlayer(AmongKryeitors.crewmates.get(random.nextInt(AmongKryeitors.crewmates.size())));
        AmongKryeitors.shapeshifter = Bukkit.getPlayer(AmongKryeitors.impostors.get(random.nextInt(AmongKryeitors.impostors.size())));

        for(Player element : playersInGame) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"tag " + element.getName() + " add ingame");
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"effect give @a[tag=ingame] blindness 5000 1 true");
        for(Player player : playersInGame) {
            if(AmongKryeitors.engineer.equals(player)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName() + " " + Utils.getTitleCommandSyntax("ENGINEER","green"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName() + " " + "subtitle {\"text\":\"You are a crewmate, you can travel through vents, do your tasks and find the impostors\",\"color\":\"green\"}");
            } else if (AmongKryeitors.shapeshifter.equals(player)) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName() + " " + Utils.getTitleCommandSyntax("SHAPESHIFTER","red"));
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName() + " subtitle {\"text\":\"You can turn into anyone, kill everyone and win the game\",\"color\":\"red\"}");
            } else if(AmongKryeitors.impostors.contains(player.getUniqueId())) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName() + " " + Utils.getTitleCommandSyntax("IMPOSTOR","red"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " subtitle {\"text\":\"Kill everyone and win the game\",\"color\":\"red\"}");
            } else if (AmongKryeitors.crewmates.contains(player.getUniqueId())) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ player.getName()  + " " + Utils.getTitleCommandSyntax("CREWMATE","green"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " " + "subtitle {\"text\":\"Do your tasks and find the impostors\",\"color\":\"green\"}");
            }
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"effect clear @a[tag=ingame]");

        GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
        globalLocalSabotageCooldown.gameSetup();

        GlobalLocalKillCooldown globalLocalKillCooldown = new GlobalLocalKillCooldown();
        globalLocalKillCooldown.gameSetup();

        GlobalLocalShapeshiftCooldown globalLocalShapeshiftCooldown = new GlobalLocalShapeshiftCooldown();
        globalLocalShapeshiftCooldown.gameSetup();

        InventoryGUI inventoryGUI = new InventoryGUI();
        inventoryGUI.GameStartUp();

        GiveOutTasks giveOutTasks = new GiveOutTasks();
        giveOutTasks.DistributeTasks();

        KillCooldownRegularCheck killCooldownRegularCheck = new KillCooldownRegularCheck();
        killCooldownRegularCheck.startCooldown();

        EmergencyCooldown emergencyCooldown = new EmergencyCooldown();
        emergencyCooldown.StartCooldown();

        return true;
    }
}
