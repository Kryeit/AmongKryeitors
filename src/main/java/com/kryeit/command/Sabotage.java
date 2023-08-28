package com.kryeit.command;

import com.kryeit.AmongKryeitors;
import com.kryeit.claiming.ClaimUtils;
import com.kryeit.miscellanous.GlobalLocalSabotageCooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Sabotage implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

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
            List<Player> players = globalLocalSabotageCooldown.getPlayersOver30Seconds();
            if (players != null) {
                if (globalLocalSabotageCooldown.getPlayersOver30Seconds().contains(sender)) {
                    cooldown.setType(Material.LIME_WOOL);
                } else {
                    cooldown.setType(Material.RED_WOOL);
                }
            }


            ItemStack[] menu_items = {reactor,filler,lights,filler,oxygen,filler,report,filler,cooldown};
            gui.setContents(menu_items);
            player.openInventory(gui);

            Plugin plugin = Bukkit.getPluginManager().getPlugin("AmongKryeitors");
            int taskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                Player playerWhoOpenedGUI = ((Player) sender).getPlayer(); // Get the player who opened the GUI
                List<Player> playersOver30Seconds = globalLocalSabotageCooldown.getPlayersOver30Seconds();

                if (playersOver30Seconds != null && playersOver30Seconds.contains(playerWhoOpenedGUI)) {
                    // The player who opened the GUI is in the list
                    // Now you can change the material of the 9th slot to Material.GREEN_WOOL

                    int slotIndex = 8; // 9th slot index (0-based)
                    gui.setItem(slotIndex, new ItemStack(Material.LIME_WOOL));
                } else {
                    globalLocalSabotageCooldown.updatePlayerTime(((Player) sender).getPlayer());
                }
            }, 0, 20); // Execute every 1 second (20 ticks)
        }

        return true;
    }

}
