package com.kryeit.command;

import com.kryeit.AmongKryeitors;
import com.kryeit.claiming.ClaimUtils;
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

            ItemStack[] menu_items = {reactor,lights,oxygen,report};
            gui.setContents(menu_items);
            player.openInventory(gui);
        }

        return true;
    }
}
