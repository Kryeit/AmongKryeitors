package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.global_variable_managers.PersonKickedOut;
import com.kryeit.miscellanous.EmergencyCooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.util.*;

public class Voting implements Listener {
    List<UUID> PlayersInGame = new ArrayList<>();

    Set<UUID> has_voted = null;

    public void StartVote() {

        PlayersInGame.addAll(AmongKryeitors.crewmates);
        PlayersInGame.addAll(AmongKryeitors.impostors);
        Collections.shuffle(PlayersInGame);

        for(UUID element : PlayersInGame) {
            AmongKryeitors.vote_per_player.put(Bukkit.getPlayer(element).getName(),0);
        }
        AmongKryeitors.vote_per_player.put("Skip", 0);


        Inventory gui = Bukkit.createInventory(null, 18, ChatColor.GREEN + "Vote");

        for (UUID element : PlayersInGame) {
            Player player = Bukkit.getPlayer(element);
            ItemStack player_head = player.getInventory().getItem(39).clone();
            ItemMeta player_head_meta = player_head.getItemMeta();
            player_head_meta.setDisplayName(Bukkit.getPlayer(element).getName());
            player_head.setItemMeta(player_head_meta);

            gui.addItem(player_head);
        }


        ItemStack skip = new ItemStack(Material.RED_CONCRETE);
        ItemMeta skip_meta = skip.getItemMeta();
        skip_meta.setDisplayName("Skip");
        skip.setItemMeta(skip_meta);
        gui.addItem(skip);

        for(UUID element : PlayersInGame) {
            Bukkit.getPlayer(element).openInventory(gui);
        }

    }


    @EventHandler
    public void OnVotingCasted(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Vote")) return;
        if (event.getClickedInventory() != event.getView().getTopInventory()) return;
        ItemStack item = event.getCurrentItem();
        if(item==null) return;
        if(item.getType().equals(Material.RED_CONCRETE)) {
            AmongKryeitors.vote_per_player.put("Skip", AmongKryeitors.vote_per_player.get("Skip") + 1);
            if(event.getWhoClicked() instanceof Player player) {
                player.closeInventory();
                HasVotingEnded();
            }
        } else {
            AmongKryeitors.vote_per_player.put(item.getItemMeta().getDisplayName(), AmongKryeitors.vote_per_player.get(item.getItemMeta().getDisplayName()) + 1);
            if(event.getWhoClicked() instanceof Player player) {
                player.closeInventory();
                HasVotingEnded();
            }
        }

        event.setCancelled(true);
    }

    public void HasVotingEnded() {
        if(has_voted.toArray().length == PlayersInGame.toArray().length) {

            String highestScorer = null;
            int highestScore = Integer.MIN_VALUE;
            boolean isTie = false;

            for (Map.Entry<String, Integer> entry : AmongKryeitors.vote_per_player.entrySet()) {
                String player = entry.getKey();
                int score = entry.getValue();

                if (score > highestScore) {
                    highestScore = score;
                    highestScorer = player;
                    isTie = false; // Reset tie flag as a new highest score was found
                } else if (score == highestScore) {
                    isTie = true; // Found a tie
                }
            }

            if (highestScorer != null) {
                if (isTie) {
                    //Tie
                } else if(highestScorer.equals("Skip")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " + Utils.getTitleCommandSyntax("VOTE SKIPPED","green"));
                } else {
                    if (AmongKryeitors.impostors.contains(Bukkit.getPlayer(highestScorer).getUniqueId())) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax(highestScorer + "has been voted", "green"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] subtitle {\"text\":\"This player was an Impostor\",\"color\":\"green\"}");
                        PersonKickedOut personKickedOut = new PersonKickedOut();
                        personKickedOut.PersonKickedOut(Bukkit.getPlayer(highestScorer));
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] " + Utils.getTitleCommandSyntax(highestScorer + "has been voted", "red"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a[tag=ingame] subtitle {\"text\":\"This player was not an Impostor\",\"color\":\"red\"}");
                        PersonKickedOut personKickedOut = new PersonKickedOut();
                        personKickedOut.PersonKickedOut(Bukkit.getPlayer(highestScorer));
                    }
                }
            }

            EmergencyCooldown emergencyCooldown = new EmergencyCooldown();
            emergencyCooldown.StartCooldown();
        }
    }

}
