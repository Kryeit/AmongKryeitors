package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.command.StartGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import com.kryeit.events.onBodyReported;
import com.kryeit.events.onLightsSabotage;

public class GUIPreventCLicking implements Listener {

    @EventHandler
    public void OnGuiClick (InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Sabotage")) {
            if (event.getClickedInventory() == event.getView().getTopInventory())

                switch (event.getCurrentItem().getType()) {
                    case BEACON:
                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"REACTOR SABOTAGED!\",\"color\":\"red\"}");
                        break;
                    case REDSTONE_LAMP:
                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"LIGHTS SABOTAGED!\",\"color\":\"red\"}");

                        break;
                    case GLASS:
                        player.closeInventory();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"OXYGEN SABOTAGED!\",\"color\":\"red\"}");
                        break;
                    case REDSTONE:
                        player.closeInventory();
                        onBodyReported.OnBodyReported(((Player) event.getWhoClicked()).getPlayer());

                }

            event.setCancelled(true);
        }
    }
}
