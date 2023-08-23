package com.kryeit.listener;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.kryeit.claiming.ClaimUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

import java.util.List;
import java.util.Objects;

public class onTaskDone implements Listener{
    List<String> tasks = List.of("1780 14 -4374",
            "1748 11 -4352",
            "1769 14 -4361",
            "1760 11 -4365",
            "1736 11 -4380",
            "1731 12 -4383",
            "1752 11 -4386",
            "1764 9 -4405",
            "1769 11 -4400",
            "1784 12 -4412",
            "1795 10 -4383",
            "1783 11 -4377",
            "1780 11 -4375",
            "1755 11 -4373",
            "1812 9 -4374",
            "1798 12 -4375",
            "1848 11 -4347",
            "1845 11 -4362",
            "1824 11 -4372",
            "1864 11 -4392",
            "1856 11 -4348",
            "1848 11 -4400",
            "1845 11 -4385",
            "1814 11 -4377",
            "1809 10 -4381");

    @EventHandler
    public void OnTaskDone(BlockRedstoneEvent event) {
        int poosx = event.getBlock().getLocation().getBlockX();
        int poosy = event.getBlock().getLocation().getBlockY();
        int poosz = event.getBlock().getLocation().getBlockZ();
        String finalpoos = poosx + " " + poosy + " " +poosz;
        if (event.getOldCurrent() == 0) {
                if (tasks.contains(finalpoos)) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
                }
        }
    }
    @EventHandler
    public void OnSpecialTaskDone (PlayerInteractEvent event) {
        int xdrain = event.getClickedBlock().getLocation().getBlockX();
        int ydrain = event.getClickedBlock().getLocation().getBlockY();
        int zdrain = event.getClickedBlock().getLocation().getBlockZ();
        String finaldrain = xdrain + " " + ydrain + " " + zdrain;
        if (finaldrain.equals("1844 10 -4349") || finaldrain.equals("1844 10 -4398")) {
            if (Objects.requireNonNull(event.getItem()).toString().equals("ItemStack{CREATE_ENCHANTMENT_INDUSTRY_INK_BUCKET x 1}")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
            }
        }
    }
    @EventHandler
    public void OnBucketFill (PlayerInteractEvent event) {
        int xspout = event.getClickedBlock().getLocation().getBlockX();
        int yspout = event.getClickedBlock().getLocation().getBlockY();
        int zspout = event.getClickedBlock().getLocation().getBlockZ();
        String finaldrain = xspout + " " + yspout + " " + zspout;
        if(finaldrain.equals("1795 10 -4398")) {
            if(Objects.requireNonNull(event.getItem()).toString().equals("ItemStack{BUCKET x 1}")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
            }
        }
    }
    List<String> datas = List.of("1772 11 -4346",
            "1758 11 -4342",
            "1734 11 -4365",
            "1776 11 -4400",
            "1846 11 -4380");
    @EventHandler
    public void OnDataDownloaded (PlayerInteractEvent event) {
        String position = event.getClickedBlock().getLocation().getBlockX() + " " + event.getClickedBlock().getLocation().getBlockY() + " " + event.getClickedBlock().getLocation().getBlockZ();
        if (datas.contains(position)) {
            if (Objects.requireNonNull(event.getItem()).toString().contains("ItemStack{WRITTEN_BOOK x 1")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
            }
        }
    }
}

