package com.kryeit.listener;

import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class onTaskDone implements Listener{

    public class QuickHash extends HashMap<String, String> {
        public QuickHash(String... KeyValuePairs) {
            super(KeyValuePairs.length/2);
            for(int i = 0; i < KeyValuePairs.length; i += 2)
                put(KeyValuePairs[i], KeyValuePairs[i + 1]);
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
            "AData","1780 11 -4375",
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

    List<String> taskignore = List.of("1762 11 -4380","1763 11 -4377","1758 11 -4372","1758 11 -4371","1762 11 -4379","1761 11 -4379","1764 11 -4379","1763 11 -4378","1764 11 -4378","1764 11 -4379","1763 11 -43780","1764 11 -4378","1764 11 -4379","1763 11 -4378","1764 11 -4378","1764 11 -4379","1763 11 -4379","1762 11 -4378","1761 11 -4378","1760 11 -4378","1760 11 -4379", "1761 11 -4377","1759 12 -4371");

    @EventHandler
    public void OnTaskDone(BlockRedstoneEvent event) {
        Location loc = event.getBlock().getLocation();
        String place = Utils.parseLocation(loc);
        if(!taskignore.contains(place)) {
            if (event.getOldCurrent() == 0) {
                System.out.println(place);
                if (tasks.containsValue(place)) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                            Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                }
            }
        }
    }
    @EventHandler
    public void OnSpecialTaskDone (PlayerInteractEvent event) {
        Location drainLoc = event.getClickedBlock().getLocation();
        String finalDrain = Utils.parseLocation(drainLoc);

        if (drainers.containsValue(finalDrain)) {
            if (Objects.requireNonNull(event.getItem()).toString().equals("ItemStack{CREATE_ENCHANTMENT_INDUSTRY_INK_BUCKET x 1}")) {
                if (finalDrain.equals(("1844 10 -4349"))) {
                    System.out.println(Utils.getClosestPlayer("1844 10 -4349").getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(("1844 10 -4349")).getName() + " " +
                            Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                } else {
                    System.out.println(Utils.getClosestPlayer("1844 10 -4398").getName());
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(("1844 10 -4398")).getName() + " " +
                            Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                }
            }
        }
    }
    @EventHandler
    public void OnBucketFill(PlayerInteractEvent event) {
        Location loc = event.getClickedBlock().getLocation();
        String finalDrain = Utils.parseLocation(loc);

        if (finalDrain.equals("1795 10 -4398")) {
            ItemStack item = event.getItem();
            if (item == null) return;
            if (item.toString().equals("ItemStack{BUCKET x 1}")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(finalDrain).getName() + " " +
                        Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
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
        Location loc = event.getClickedBlock().getLocation();
        String position = Utils.parseLocation(loc);

        if (datas.containsValue(position)) {
            if (Objects.requireNonNull(event.getItem()).toString().contains("ItemStack{WRITTEN_BOOK x 1")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"title @a " +
                        Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
            }
        }
    }
}

