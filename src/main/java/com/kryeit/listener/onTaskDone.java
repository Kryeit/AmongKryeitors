package com.kryeit.listener;

import com.griefdefender.api.Core;
import com.griefdefender.api.GriefDefender;
import com.kryeit.claiming.ClaimUtils;
import com.kryeit.claiming.GetClosestPlayer;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class onTaskDone implements Listener{

    public class QuickHash extends HashMap<String, String> {
        public QuickHash(String... KeyValuePairs) {
            super(KeyValuePairs.length/2);
            for(int i=0; i<KeyValuePairs.length; i+=2)
                put(KeyValuePairs[i], KeyValuePairs[i+1]);
        }
    }


    Map<String,String> tasks = new QuickHash(
            "CaTrash","1780 14 -4374",
            "WPower","1748 11 -4352",
            "OTrash","1769 14 -4361",
            "OPower","1760 11 -4365",
            "NPower","1736 11 -4380",
            "NTarget","1731 12 -4383",
            "ShPower","1752 11 -4386",
            "ShWiring","1764 9 -4405",
            "CoPower","1769 11 -4400",
            "STrash","1784 12 -4412",
            "SWiring","1795 10 -4383",
            "APower","1783 11 -4377",
            "ACard","1755 11 -4373",
            "MScan","1812 9 -4374",
            "MAnomaly","1798 12 -4375",
            "UTarget","1848 11 -4347",
            "UPower","1845 11 -4362",
            "SPower","1824 11 -4372",
            "RSimon","1864 11 -4392",
            "RPin","1856 11 -4348",
            "LTarget","1848 11 -4400",
            "LPower","1845 11 -4385",
            "EPower","1814 11 -4377",
            "EWiring","1809 10 -4381"
    );

    Map<String,String> drainers = new QuickHash(
            "UGas","1844 10 -4349",
            "LGas","1844 10 -4398"
    );


    @EventHandler
    public void OnTaskDone(BlockRedstoneEvent event) {
        int poosx = event.getBlock().getLocation().getBlockX();
        int poosy = event.getBlock().getLocation().getBlockY();
        int poosz = event.getBlock().getLocation().getBlockZ();
        String finalpoos = poosx + " " + poosy + " " +poosz;
        if (event.getOldCurrent() == 0) {
                if (tasks.containsValue(finalpoos)) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+ GetClosestPlayer.getClosestPlayer(finalpoos)+ " title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
                }
        }
    }
    @EventHandler
    public void OnSpecialTaskDone (PlayerInteractEvent event) {
        int xdrain = event.getClickedBlock().getLocation().getBlockX();
        int ydrain = event.getClickedBlock().getLocation().getBlockY();
        int zdrain = event.getClickedBlock().getLocation().getBlockZ();
        String finaldrain = xdrain + " " + ydrain + " " + zdrain;
        if (drainers.containsValue(finaldrain)) {
            if (Objects.requireNonNull(event.getItem()).toString().equals("ItemStack{CREATE_ENCHANTMENT_INDUSTRY_INK_BUCKET x 1}")) {
                if (finaldrain.equals(("1844 10 -4349"))) {
                    System.out.println(Bukkit.getPlayer(GetClosestPlayer.getClosestPlayer("1844 10 -4349")).getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Bukkit.getPlayer(GetClosestPlayer.getClosestPlayer(("1844 10 -4349"))).getName() + " title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
                } else {
                    System.out.println(Bukkit.getPlayer(GetClosestPlayer.getClosestPlayer("1844 10 -4398")).getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Bukkit.getPlayer(GetClosestPlayer.getClosestPlayer(("1844 10 -4398"))).getName() + " title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
                }
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
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title "+GetClosestPlayer.getClosestPlayer(finaldrain) +" title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
            }
        }
    }

    Map<String,String> datas = new QuickHash(
            "CaData","1772 11 -4346",
            "WData","1758 11 -4342",
            "NData","1734 11 -4365",
            "CoData","1776 11 -4400",
            "EData","1816 11 -4380"
    );

    @EventHandler
    public void OnDataDownloaded (PlayerInteractEvent event) {
        String position = event.getClickedBlock().getLocation().getBlockX() + " " + event.getClickedBlock().getLocation().getBlockY() + " " + event.getClickedBlock().getLocation().getBlockZ();
        if (datas.containsValue(position)) {
            if (Objects.requireNonNull(event.getItem()).toString().contains("ItemStack{WRITTEN_BOOK x 1")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"TASK COMPLETE!\",\"color\":\"dark_green\"}");
            }
        }
    }
}

