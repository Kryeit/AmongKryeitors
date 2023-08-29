package com.kryeit.events;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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


        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Vote");

        ItemStack indicator = new ItemStack(Material.RED_WOOL);
        gui.setItem(26, indicator);

        for (UUID element : PlayersInGame) {
            ItemStack player_head = new ItemStack(Objects.requireNonNull(Bukkit.getPlayer(element).getInventory().getItem(39)));
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
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Vote")) {
            if (event.getClickedInventory() == event.getView().getTopInventory()) {
                if(event.getSlot()<25) {
                    if(event.getCurrentItem()!=null) {
                        if (event.getCurrentItem().equals(Material.RED_CONCRETE)) {
                            ItemStack new_vote = new ItemStack(Material.LIME_WOOL);
                            ItemMeta new_vote_meta = new_vote.getItemMeta();
                            new_vote_meta.setDisplayName(event.getCurrentItem().getItemMeta().getDisplayName());
                            new_vote.setItemMeta(new_vote_meta);

                            event.getInventory().setItem(26, new_vote);
                        } else {
                            ItemStack new_vote = new ItemStack(Material.LIME_WOOL);
                            ItemMeta new_vote_meta = new_vote.getItemMeta();
                            new_vote_meta.setDisplayName("Skip vote");
                            new_vote.setItemMeta(new_vote_meta);

                            event.getInventory().setItem(26, new_vote);
                        }
                    }
                } else if (event.getSlot()==26) {
                    if (!event.getCurrentItem().getItemMeta().getDisplayName().equals("Skip vote")) {
                        String chosen_player = event.getCurrentItem().getItemMeta().getDisplayName();
                        AmongKryeitors.vote_per_player.put(chosen_player, AmongKryeitors.vote_per_player.get(chosen_player) + 1);

                        event.getWhoClicked().closeInventory();
                        has_voted.add(event.getWhoClicked().getUniqueId());
                        HasVotingEnded();
                    } else {
                        event.getWhoClicked().closeInventory();
                        AmongKryeitors.vote_per_player.put("Skip", AmongKryeitors.vote_per_player.get("Skip")+1);
                        has_voted.add(event.getWhoClicked().getUniqueId());
                        HasVotingEnded();
                    }
                }

                event.setCancelled(true);
            }
        }
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
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " + Utils.getTitleCommandSyntax(highestScorer + "has been voted", "green"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a subtitle {\"text\":\"This player was an Impostor\",\"color\":\"green\"}");
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a " + Utils.getTitleCommandSyntax(highestScorer + "has been voted", "red"));
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a subtitle {\"text\":\"This player was not an Impostor\",\"color\":\"red\"}");
                    }
                }
            }
        }
    }

}
