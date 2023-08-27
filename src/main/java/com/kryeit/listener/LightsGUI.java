package com.kryeit.listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LightsGUI implements Listener {

    @EventHandler
    public void onLightsSabotageTask(PlayerInteractEvent event) {
        if (Utils.parseLocation(event.getClickedBlock().getLocation()).equals("1818 12 -4382") && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Inventory gui = Bukkit.createInventory(event.getPlayer(), 9, ChatColor.GREEN + "Fix Lights");

            Random random = new Random();
            ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

            ItemStack[] menu_items = new ItemStack[9];

            for (int i = 0; i < 9; i++) {
                if (i % 2 == 0) { // Place "no_one" on even-indexed slots
                    if (random.nextBoolean()) {
                        menu_items[i] = new ItemStack(Material.RED_STAINED_GLASS_PANE);
                    } else {
                        menu_items[i] = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                    }
                } else {
                    menu_items[i] = filler;
                }
            }

            gui.setContents(menu_items);
            event.getPlayer().openInventory(gui);
        }
    }

    @EventHandler
    public void onLightsGUIClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.GREEN + "Fix Lights")) {
            if (event.getClickedInventory() == event.getView().getTopInventory()) {
                switch (event.getCurrentItem().getType()){
                    case GRAY_STAINED_GLASS_PANE -> {
                        break;
                    }
                    case GREEN_STAINED_GLASS_PANE -> {
                        event.getInventory().setItem(event.getSlot(), new ItemStack(Material.RED_STAINED_GLASS_PANE));
                        break;
                    }
                    case RED_STAINED_GLASS_PANE -> {
                        event.getInventory().setItem(event.getSlot(), new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
                        break;
                    }

                }
                event.setCancelled(true);
            }
            ItemStack greenStainedGlass = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            boolean allItemsMatch = true;

            for (int i = 0; i <= 8; i += 2) {
                if (!event.getInventory().getItem(i).equals(greenStainedGlass)) {
                    allItemsMatch = false;
                    break;
                }
            }

            if (allItemsMatch) {
                player.closeInventory();
                onLightsFixed.OnLightsFixed();
            }
        }

    }
}
