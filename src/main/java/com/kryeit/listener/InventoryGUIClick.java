package com.kryeit.listener;


import com.kryeit.AmongKryeitors;
import com.kryeit.events.onBodyReported;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import com.kryeit.miscellanous.GlobalLocalShapeshiftCooldown;
import com.kryeit.miscellanous.GlobalShapeshiftTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class InventoryGUIClick implements Listener {

    private static BukkitTask taskId;
    private static BukkitTask taskId_two;


    @EventHandler
    public void onInventoryGUIClick(PlayerInteractEvent event) {
            if(event.getItem()==null) return;

            String item = Objects.requireNonNull(event.getItem()).toString();

            if (item.contains("ItemStack{RED_DYE x 1")) {
                System.out.println("RED DYE");
                onBodyReported.OnBodyReported((event.getPlayer()));
            } else if (item.contains("ItemStack{CLOCK x 1")) {
                System.out.println("CLOCK");
                Player sender = event.getPlayer();
                Player player = event.getPlayer();

                Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "Sabotage");

                ItemStack reactor = new ItemStack(Material.BEACON);
                ItemStack lights = new ItemStack(Material.REDSTONE_LAMP);
                ItemStack oxygen = new ItemStack(Material.GLASS);
                ItemStack report = new ItemStack(Material.REDSTONE);

                ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

                ItemStack cooldown = new ItemStack(Material.RED_WOOL);

                ItemMeta reactor_meta = reactor.getItemMeta();
                reactor_meta.setDisplayName(ChatColor.GOLD + "Reactor Sabotage");
                ItemMeta lights_meta = lights.getItemMeta();
                lights_meta.setDisplayName(ChatColor.YELLOW + "Lights Sabotage");
                ItemMeta oxygen_meta = oxygen.getItemMeta();
                oxygen_meta.setDisplayName(ChatColor.RED + "Oxygen Sabotage");
                ItemMeta report_meta = report.getItemMeta();
                report_meta.setDisplayName(ChatColor.GREEN + "Report Body");

                reactor.setItemMeta(reactor_meta);
                lights.setItemMeta(lights_meta);
                oxygen.setItemMeta(oxygen_meta);
                report.setItemMeta(report_meta);

                GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();
                List<Player> players = globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage();
                if (players != null) {
                    if (globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage().contains(sender)) {
                        cooldown.setType(Material.LIME_WOOL);
                    } else {
                        cooldown.setType(Material.RED_WOOL);
                    }
                }


                ItemStack[] menu_items = {reactor, filler, lights, filler, oxygen, filler, report, filler, cooldown};
                gui.setContents(menu_items);
                player.openInventory(gui);

                Plugin plugin = Bukkit.getPluginManager().getPlugin("AmongKryeitors");
                taskId = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    Player playerWhoOpenedGUI = event.getPlayer(); // Get the player who opened the GUI
                    List<Player> playersOver30Seconds = globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage();

                    if (playersOver30Seconds != null && playersOver30Seconds.contains(playerWhoOpenedGUI)) {
                        // The player who opened the GUI is in the list
                        // Now you can change the material of the 9th slot to Material.GREEN_WOOL

                        int slotIndex = 8; // 9th slot index (0-based)
                        gui.setItem(slotIndex, new ItemStack(Material.LIME_WOOL));
                    } else {
                        globalLocalSabotageCooldown.updatePlayerTime(event.getPlayer());
                    }
                }, 0, 20); // Execute every 1 second (20 ticks)
            } else if (item.contains("ItemStack{PLAYER_HEAD x 1")) {
                System.out.println("PLAYER HEAD");
                Player player = event.getPlayer();

                Inventory gui = Bukkit.createInventory(player, 18, ChatColor.GOLD + "Shapeshift");

                List<UUID> PlayersInGame = new ArrayList<>();
                PlayersInGame.addAll(AmongKryeitors.crewmates);
                PlayersInGame.addAll(AmongKryeitors.impostors);

                int index = 0;
                for (UUID element : PlayersInGame) {
                    if (!element.equals(event.getPlayer().getUniqueId())) {
                        ItemStack headItem = Bukkit.getPlayer(element).getInventory().getItem(39);
                        gui.setItem(index, headItem);
                        index++;
                    }
                }

                ItemStack cooldown = new ItemStack(Material.RED_WOOL);
                gui.setItem(17, cooldown);
                GlobalLocalShapeshiftCooldown globalLocalShapeshiftCooldown = new GlobalLocalShapeshiftCooldown();

                player.openInventory(gui);

                Plugin plugin = Bukkit.getPluginManager().getPlugin("AmongKryeitors");
                taskId_two = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                    Player playerWhoOpenedGUI = event.getPlayer(); // Get the player who opened the GUI
                    List<Player> playersOver30Seconds = globalLocalShapeshiftCooldown.getPlayersOver30SecondsShapeshift();

                    if (playersOver30Seconds != null && playersOver30Seconds.contains(playerWhoOpenedGUI)) {
                        // The player who opened the GUI is in the list
                        // Now you can change the material of the 9th slot to Material.GREEN_WOOL
                        System.out.println("wya");
                        int slotIndex = 17; // 9th slot index (0-based)
                        gui.setItem(slotIndex, new ItemStack(Material.LIME_WOOL));
                    } else {
                        System.out.println(playerWhoOpenedGUI.getName());
                        globalLocalShapeshiftCooldown.updatePlayerTime(playerWhoOpenedGUI); // Use the playerWhoOpenedGUI

                        System.out.println("shit");
                    }
                }, 0, 20); // Replace 0L and 20L with your desired delay and period

            }

    }

    @EventHandler
    public void ShapeshiftClick(InventoryClickEvent event) {
        if (event.getCurrentItem()==null) return;
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GOLD + "Shapeshift")) {
            if (event.getClickedInventory() == event.getView().getTopInventory()) {
                if(event.getCurrentItem()==null) return;
                if(event.getCurrentItem()==new ItemStack(Material.LIME_WOOL)) return;
                if(event.getCurrentItem()==new ItemStack(Material.RED_WOOL)) return;
                GlobalLocalShapeshiftCooldown globalLocalShapeshiftCooldown = new GlobalLocalShapeshiftCooldown();
                if (globalLocalShapeshiftCooldown.getPlayersOver30SecondsShapeshift().contains(event.getWhoClicked())) {

                    AmongKryeitors.old_cap = event.getWhoClicked().getInventory().getItem(39);
                    GlobalShapeshiftTime globalShapeshiftTime = new GlobalShapeshiftTime();
                    globalShapeshiftTime.Shapeshifts();

                    event.getWhoClicked().getInventory().setItem(39, event.getCurrentItem());
                    event.getWhoClicked().closeInventory();
                }
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void ItemDrop(PlayerDropItemEvent event) {
        switch (event.getItemDrop().getItemStack().getType()) {
            case CLOCK:
                event.setCancelled(true);
            case RED_DYE:
                event.setCancelled(true);
            case GRAY_STAINED_GLASS_PANE:
                event.setCancelled(true);
            case NETHERITE_SWORD:
                event.setCancelled(true);
            case PLAYER_HEAD:
                event.setCancelled(true);
        }

    }

    @EventHandler
    public void ItemClick(InventoryClickEvent event) {
        if(event.getCurrentItem()==null) return;
        if (AmongKryeitors.crewmates.contains(event.getWhoClicked().getUniqueId())) {
            switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
                case CLOCK:
                    event.setCancelled(true);
                case RED_DYE:
                    event.setCancelled(true);
                case GRAY_STAINED_GLASS_PANE:
                    event.setCancelled(true);
                case NETHERITE_SWORD:
                    event.setCancelled(true);
                case PLAYER_HEAD:
                    event.setCancelled(true);
            }
        } else if (AmongKryeitors.impostors.contains(event.getWhoClicked().getUniqueId())) {
            switch (Objects.requireNonNull(event.getCurrentItem()).getType()) {
                case CLOCK:
                    event.setCancelled(true);
                case RED_DYE:
                    event.setCancelled(true);
                case GRAY_STAINED_GLASS_PANE:
                    event.setCancelled(true);
                case NETHERITE_SWORD:
                    event.setCancelled(true);
                case PLAYER_HEAD:
                    event.setCancelled(true);
            }
        }


    }

}

