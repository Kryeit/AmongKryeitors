package com.kryeit.listener;

import com.kryeit.Utils;
import com.kryeit.events.onOxygenSabotage;
import com.kryeit.events.onReactorSabotage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import com.kryeit.events.onBodyReported;
import com.kryeit.events.onLightsSabotage;

public class SabotageGUI implements Listener {

    @EventHandler
    public void OnGuiClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "Sabotage")) {
            if (event.getClickedInventory() == event.getView().getTopInventory()) {

                switch (event.getCurrentItem().getType()) {
                    case BEACON:
                        player.closeInventory();
                        onReactorSabotage OnReactorSabotage = new onReactorSabotage();
                        OnReactorSabotage.OnReactorSabotage();
                        break;
                    case REDSTONE_LAMP:
                        player.closeInventory();
                        onLightsSabotage.OnLightsSabotage();

                        break;
                    case GLASS:
                        player.closeInventory();
                        onOxygenSabotage oxygenSabotage = new onOxygenSabotage();
                        oxygenSabotage.OnOxygenSabotage();
                        break;
                    case REDSTONE:
                        player.closeInventory();
                        onBodyReported.OnBodyReported(((Player) event.getWhoClicked()).getPlayer());

                }

                event.setCancelled(true);
            }
        }
    }
}
