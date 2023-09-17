package com.kryeit.listener;

import com.kryeit.events.onOxygenSabotage;
import com.kryeit.events.onReactorSabotage;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
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
                GlobalLocalSabotageCooldown globalLocalSabotageCooldown = new GlobalLocalSabotageCooldown();

                switch (event.getCurrentItem().getType()) {
                    case BEACON:
                        if(globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage().contains(event.getWhoClicked())) {
                            player.closeInventory();
                            onReactorSabotage OnReactorSabotage = new onReactorSabotage();
                            OnReactorSabotage.OnReactorSabotage(player);
                            globalLocalSabotageCooldown.resetPlayerTime( (Player) event.getWhoClicked());
                        }
                        break;
                    case REDSTONE_LAMP:
                        if(globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage().contains(event.getWhoClicked())) {
                            player.closeInventory();
                            onLightsSabotage.OnLightsSabotage(player);
                        }


                        break;
                    case GLASS:
                        if(globalLocalSabotageCooldown.getPlayersOver30SecondsSabotage().contains(event.getWhoClicked())) {
                            player.closeInventory();
                            onOxygenSabotage oxygenSabotage = new onOxygenSabotage();
                            oxygenSabotage.OnOxygenSabotage(player);
                        }

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
