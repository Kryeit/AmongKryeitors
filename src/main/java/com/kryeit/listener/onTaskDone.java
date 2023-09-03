package com.kryeit.listener;

import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import com.kryeit.miscellanous.GiveOutTasks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.kryeit.events.onCrewmatesWin;

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
            "WePower","1748 11 -4352",
            "O2Trash","1769 14 -4361",
            "O2Power","1760 11 -4365",
            "NaPower","1736 11 -4380",
            "NaTarget","1731 12 -4383",
            "ShPower","1752 11 -4386",
            "ShWiring","1764 9 -4405",
            "CoPower","1769 11 -4400",
            "StTrash","1784 12 -4412",
            "StWiring","1795 10 -4383",
            "AdPower","1783 11 -4377",
            "AdCard","1764 11 -4382",
            "AdData","1780 11 -4376",
            "MeScan","1812 9 -4374",
            "MeAnomaly","1798 12 -4375",
            "UpTarget","1848 11 -4347",
            "UpPower","1845 11 -4362",
            "SePower","1824 11 -4372",
            "ReSimon","1864 11 -4392",
            "RePin","1856 11 -4348",
            "LoTarget","1848 11 -4400",
            "LoPower","1845 11 -4385",
            "ElPower","1814 11 -4377",
            "ElWiring","1809 10 -4381"
    );

    Map<String,String> drainers = new QuickHash(
            "UpGas","1844 10 -4349",
            "LoGas","1844 10 -4398"
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
                    GiveOutTasks giveOutTasks = new GiveOutTasks();
                    boolean was_task_correct = giveOutTasks.EraseTaskFromPlayer(Utils.getClosestPlayer(place), Utils.GetKeyFromValue(tasks,Utils.parseLocation(event.getBlock().getLocation())));
                    if(was_task_correct) {
                        if(AreAllTAsksDone()) {
                            onCrewmatesWin onCrewmatesWin = new onCrewmatesWin();
                            onCrewmatesWin.OnCrewmatesWin();
                        }
                        if(Utils.GetKeyFromValue(tasks,Utils.parseLocation(event.getBlock().getLocation())).equals("AdData")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                                    Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "give " + Utils.getClosestPlayer(place).getName() + " book");
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                                Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                    } else {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                                Utils.getTitleCommandSyntax("WRONG TASK!", "red"));
                    }

                }
            }
        }
    }


    @EventHandler
    public void OnSpecialTaskDone (PlayerInteractEvent event) {
        if(event.getItem()==null) return;
        if(event.getClickedBlock().getType().equals(Material.AIR)) return;
        Location drainLoc = event.getClickedBlock().getLocation();
        String finalDrain = Utils.parseLocation(drainLoc);

        if (drainers.containsValue(finalDrain)) {
            if (Objects.requireNonNull(event.getItem()).toString().equals("ItemStack{CREATE_ENCHANTMENT_INDUSTRY_INK_BUCKET x 1}")) {

                GiveOutTasks giveOutTasks = new GiveOutTasks();
                boolean was_task_correct = giveOutTasks.EraseTaskFromPlayer(Utils.getClosestPlayer(Utils.parseLocation(drainLoc)), Utils.GetKeyFromValue(drainers,Utils.parseLocation(drainLoc)));
                if(was_task_correct) {
                    if(AreAllTAsksDone()) {
                        onCrewmatesWin onCrewmatesWin = new onCrewmatesWin();
                        onCrewmatesWin.OnCrewmatesWin();
                    }
                    if (finalDrain.equals(("1844 10 -4349"))) {
                        System.out.println(Utils.getClosestPlayer("1844 10 -4349").getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(("1844 10 -4349")).getName() + " " +
                                Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                    } else {
                        System.out.println(Utils.getClosestPlayer("1844 10 -4398").getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(("1844 10 -4398")).getName() + " " +
                                Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                    }
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(Utils.parseLocation(drainLoc)).getName() + " " +
                            Utils.getTitleCommandSyntax("WRONG TASK!", "red"));
                }

            }
        }
    }

    Map<String,String> fill = new QuickHash(
            "StGas","1795 10 -4398"
    );

    @EventHandler
    public void OnBucketFill(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if(event.getClickedBlock().getType().equals(Material.AIR)) return;
        Location loc = event.getClickedBlock().getLocation();
        String finalDrain = Utils.parseLocation(loc);

        if (finalDrain.equals("1795 10 -4398")) {
            ItemStack item = event.getItem();
            if (item.toString().equals("ItemStack{BUCKET x 1}")) {
                GiveOutTasks giveOutTasks = new GiveOutTasks();
                String place = Utils.parseLocation(event.getClickedBlock().getLocation());
                boolean was_task_correct = giveOutTasks.EraseTaskFromPlayer(Utils.getClosestPlayer(place), Utils.GetKeyFromValue(fill,Utils.parseLocation(event.getClickedBlock().getLocation())));
                if(was_task_correct) {
                    if(AreAllTAsksDone()) {
                        onCrewmatesWin onCrewmatesWin = new onCrewmatesWin();
                        onCrewmatesWin.OnCrewmatesWin();
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                            Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                            Utils.getTitleCommandSyntax("WRONG TASK!", "red"));
                }
            }
        }
    }

    Map<String,String> datas = new QuickHash(
            "CaData","1772 11 -4346",
            "WeData","1758 11 -4342",
            "NaData","1734 11 -4365",
            "CoData","1776 11 -4400",
            "ElData","1816 11 -4380"
    );

    @EventHandler
    public void OnDataDownloaded (PlayerInteractEvent event) {
        if(event.getItem()==null) return;
        if(event.getClickedBlock().getType().equals(Material.AIR)) return;
        Location loc = Objects.requireNonNull(event.getClickedBlock()).getLocation();
        String position = Utils.parseLocation(loc);

        if (datas.containsValue(position)) {
            System.out.println(event.getItem().toString());
            if (Objects.requireNonNull(event.getItem()).toString().contains("ItemStack{WRITTEN_BOOK x 1")) {
                String place = Utils.parseLocation(event.getClickedBlock().getLocation());
                GiveOutTasks giveOutTasks = new GiveOutTasks();
                boolean was_task_correct = giveOutTasks.EraseTaskFromPlayer(Utils.getClosestPlayer(place), Utils.GetKeyFromValue(datas,Utils.parseLocation(event.getClickedBlock().getLocation())));
                if(was_task_correct) {
                    if(AreAllTAsksDone()) {
                        onCrewmatesWin onCrewmatesWin = new onCrewmatesWin();
                        onCrewmatesWin.OnCrewmatesWin();
                    }
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                            Utils.getTitleCommandSyntax("TASK COMPLETE!", "dark_green"));
                } else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title " + Utils.getClosestPlayer(place).getName() + " " +
                            Utils.getTitleCommandSyntax("WRONG TASK!", "red"));
                }
            }
        }
    }

    public boolean AreAllTAsksDone() {

        GiveOutTasks giveOutTasks = new GiveOutTasks();
        Map<String, String> crewmates_list = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : AmongKryeitors.player_task_list.entrySet()) {
            if (AmongKryeitors.crewmates.contains(Bukkit.getPlayer(entry.getKey()).getUniqueId())) {
                crewmates_list.put(entry.getKey(), entry.getValue());
            }
        }

        boolean is_it = true;

        for (String value : crewmates_list.values()) {
            if (value != "") {
                is_it = false;
            }
        }

        if (is_it) {
            return true;
        } else {
            return false;
        }
    }


}

