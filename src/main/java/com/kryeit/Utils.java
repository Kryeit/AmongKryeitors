package com.kryeit;

import com.google.common.collect.Multiset;
import com.kryeit.miscellanous.GiveOutTasks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import java.util.Map.Entry;

import java.util.*;

public class Utils {


    public static String parseLocation(Location location) {
        return location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ();
    }

    public static Location parseString(String string) {
        String[] parts = string.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        int z = Integer.parseInt(parts[2]);
        return new Location(Bukkit.getWorld("world"), x, y, z);
    }

    public static String getTitleCommandSyntax(String text, String color) {
        return "title {\"text\":\"" + text + "\",\"color\":\"" + color + "\"}";
    }

    public static Player getClosestPlayer(String coordinates) {
        UUID closest = null;
        double distance = 100;
        Location location = Utils.parseString(coordinates);

        List<UUID> playersInGame = new ArrayList<>();
        playersInGame.addAll(AmongKryeitors.crewmates);
        playersInGame.addAll(AmongKryeitors.impostors);

        for (UUID player : playersInGame) {
            Location playerloc = Bukkit.getPlayer(player).getLocation();

            double aux = playerloc.distance(location);

            if (aux < distance) {
                distance = aux; // Update the closest distance
                closest = player; // Update the closest player UUID
            }
        }
        if(closest == null) return null;
        return Bukkit.getPlayer(closest);
    }

    public static String GetKeyFromValue(Map<String,String> map, String value) {

        String matching_key = null;

        for(Map.Entry<String,String> entry : map.entrySet()) {
            if(Objects.equals(entry.getValue(),value)) {
                matching_key = entry.getKey();
                break;
            }
        }
        return matching_key;

    }

    public static String GetClipboardFromTaskList(Collection<String> task_list) {
        //{
        //    Pages : [{
        //        Entries : [{
        //            Checked : 0b,
        //            Text : '{"text":"Task"}'}
        //        ]
        //    }]
        //}

        String end_closes = "\"}'}]}], Type : 1, PreviouslyOpenedPage : 0}";
        String start_args = "{Pages:[{Entries:[{Checked:0b,Text:'{\"text\":\"";
        String middle_separator = "\"}'},{Checked: 0b, Text: '{\"text\":\"";
        String page_separator = "\"}'}]}, {Entries: [{Checked : 0b, Text : '{\"text\":\"";
        StringBuilder command = new StringBuilder();
        command.append(start_args);
        String last_arg = task_list.toArray(new String[0])[task_list.size()-1];
        Collection<String> new_list = task_list;
        new_list.remove(last_arg);
        GiveOutTasks giveOutTasks = new GiveOutTasks();
        Map<String,String> all_tasks = new HashMap<>();
        all_tasks.putAll(giveOutTasks.primarytask);
        all_tasks.putAll(giveOutTasks.secondarytask);

        int index = 0;
        for(String element : new_list) {
            if(index != 14) {
                command.append(all_tasks.get(element + ";"));
                command.append(middle_separator);
                index++;
            } else {
                command.append(all_tasks.get(element + ";"));
                command.append(page_separator);
                index++;
            }
        }
        command.append(all_tasks.get(last_arg + ";"));
        command.append(end_closes);

        System.out.println(command.toString());
        return command.toString();
    }

}
