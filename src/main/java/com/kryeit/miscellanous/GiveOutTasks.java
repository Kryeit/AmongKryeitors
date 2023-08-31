package com.kryeit.miscellanous;

import com.griefdefender.api.util.NBTUtil;
import com.griefdefender.lib.kyori.adventure.text.NBTComponentBuilder;
import com.kryeit.AmongKryeitors;
import com.kryeit.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.lang.reflect.Array;
import java.util.*;

public class GiveOutTasks {

    public Map<String,String> secondarytask = new QuickHash("AdData;","Admin : Upload Data","UpGas;","Upper Engine : Empty Gas",
            "LoGas;","Lower Engine : Empty Gas");
    public Map<String, String> primarytask = new QuickHash("CaTrash;","Cafeteria : Empty Trash",
            "WePower;","Weapons : Accept Power",
            "O2Trash;","O2 : Empty Trash",
            "O2Power;","O2 : Accept Power",
            "NaPower;","Navigation : Accept Power",
            "NaTarget;","Navigation : Align Target",
            "ShPower;","Shields : Accept Power",
            "ShWiring;","Shields : Fix Wiring",
            "CoPower;","Communications : Accept Power",
            "StTrash;","Storage : Empty Trash",
            "StWiring;","Storage : Fix Wiring",
            "StGas;","Storage : Fill Gas",
            "AdPower;","Admin : Accept Power",
            "AdCard;","Admin : Swipe Card",
            "MeScan;","MedBay : Scan",
            "MeAnomaly;","MedBay : Find anomaly",
            "UpTarget;","Upper Engine : Align Target",
            "UpPower;","Upper Engine : Accept Power",
            "SePower;","Security : Accept Power",
            "ReSimon;","Reactor : Copy sequence",
            "RePin;","Reactor : Sort spectrum",
            "LoTarget;","Lower Engine : Align Target",
            "LoPower;","Lower Engine : Accept Power",
            "ElPower;","Electrical : Accept Power",
            "ElWiring;","Electrical : Fix Wiring",
            "CaData;","Cafeteria : Download Data",
            "WeData;","Weapons : Download Data",
            "NaData;","Navigation : Download Data",
            "CoData;","Communications : Download Data",
            "ElData;","Electrical : Download Data");

    public void DistributeTasks() {

        Map<String,String> tasklist_perplayer = new QuickHash();

        List<UUID> PlayersInGame = new ArrayList<>();
        PlayersInGame.addAll(AmongKryeitors.crewmates);
        PlayersInGame.addAll(AmongKryeitors.impostors);
        System.out.println(PlayersInGame.toArray().length);

        for (UUID element : PlayersInGame) {
            String task_set = GenerateTaskSet(primarytask, 14);
            String player_name = Bukkit.getPlayer(element).getName();
            AmongKryeitors.player_task_list.put(player_name,task_set);
            Collection<String> separated_tasks = SeparateTasks(task_set);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"give " + Bukkit.getPlayer(element).getName() + " create:clipboard" + Utils.GetClipboardFromTaskList(separated_tasks));

        }
        for (Map.Entry<String, String> entry : AmongKryeitors.player_task_list.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }



    }
    public class QuickHash extends HashMap<String, String> {
        public QuickHash(String... KeyValuePairs) {
            super(KeyValuePairs.length/2);
            for(int i = 0; i < KeyValuePairs.length; i += 2)
                put(KeyValuePairs[i], KeyValuePairs[i + 1]);
        }
    }
    
    public Collection<String> SeparateTasks(String tasklist) {
        
        String[] split_tasks = tasklist.split(";");
        List<String> final_tasks = new ArrayList<>();
        
        for(String element : split_tasks) {
            final_tasks.add(element);
        }
        
        return final_tasks;
        
    }

    public String GenerateTaskSet(Map<String, String> tasks, int task_number) {
        Random random = new Random();
        Set<String> key_set = tasks.keySet();
        String[] key_set_string = key_set.toArray(new String[0]);

        StringBuilder task_set_builder = new StringBuilder(); // Use StringBuilder for efficient string concatenation
        
        int index = 0;

        while (index < task_number) {
            int randomIndex = random.nextInt(key_set_string.length);
            String next_task = key_set_string[randomIndex];

            if (!task_set_builder.toString().contains(next_task)) {
                task_set_builder.append(next_task);
            }

            if (next_task.contains("Data")) {
                task_set_builder.append("AdData;");
            } else if (next_task.contains("StGas")) {
                task_set_builder.append("UpGas;StGas;LoGas;");
            }
            index++;
        }

        return task_set_builder.toString();
    }


    public boolean EraseTaskFromPlayer(Player player,String task) {
        for (Map.Entry<String, String> entry : AmongKryeitors.player_task_list.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        System.out.println(player.getName());
        System.out.println(AmongKryeitors.player_task_list.get(player.getName()));
        if(AmongKryeitors.player_task_list.get(player.getName()).contains(task)) {
            Collection<String> separated_tasks = SeparateTasks(AmongKryeitors.player_task_list.get(player.getName()));
            Iterator<String> iterator = separated_tasks.iterator();
            while (iterator.hasNext()) {
                String element = iterator.next();
                if (element.contains(task)) {
                    iterator.remove();
                }
            }

            String final_string = ReAssembleTasks(separated_tasks);
            AmongKryeitors.player_task_list.put(player.getName(), final_string);
            return true;
        } else {
            return false;
        }
    }

    public String ReAssembleTasks(Collection<String> task_list) {
        StringBuilder assembled_tasks = new StringBuilder();

        for (String element : task_list) {
            if (!element.equals(task_list.toArray()[task_list.size() - 1])) {
                assembled_tasks.append(element).append(";");
            } else {
                assembled_tasks.append(element);
            }
        }

        return assembled_tasks.toString();
    }


}
