package com.kryeit.miscellanous;

import com.kryeit.AmongKryeitors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class GiveOutTasks {

    Map<String,String> secondarytask = new QuickHash("AdData...","Admin : Upload Data","UpGas....","Upper Engine : Empty Gas",
            "LoGas....","Lower Engine : Empty Gas");
    Map<String, String> primarytask = new QuickHash("CaTrash..","Cafeteria : Empty Trash",
            "WePower..","Weapons : Accept Power",
            "O2Trash..","O2 : Empty Trash",
            "O2Power..","O2 : Accept Power",
            "NaPower..","Navigation : Accept Power",
            "NaTarget.","Navigation : Align Target",
            "ShPower..","Shields : Accept Power",
            "ShWiring.","Shields : Fix Wiring",
            "CoPower..","Communications : Accept Power",
            "StTrash..","Storage : Empty Trash",
            "StWiring.","Storage : Fix Wiring",
            "StGas....","Storage : Fill Gas",
            "AdPower..","Admin : Accept Power",
            "AdCard...","Admin : Swipe Card",
            "MeScan...","MedBay : Scan",
            "MeAnomaly","MedBay : Find anomaly",
            "UpTarget.","Upper Engine : Align Target",
            "UpPower..","Upper Engine : Accept Power",
            "SePower..","Security : Accept Power",
            "ReSimon..","Reactor : Copy sequence",
            "RePin....","Reactor : Sort spectrum",
            "LoTarget.","Lower Engine : Align Target",
            "LoPower..","Lower Engine : Accept Power",
            "ElPower..","Electrical : Accept Power",
            "ElWiring.","Electrical : Fix Wiring",
            "CaData...","Cafeteria : Download Data",
            "WeData...","Weapons : Download Data",
            "NaData...","Navigation : Download Data",
            "CoData...","Communications : Download Data",
            "ElData...","Electrical : Download Data");

    Map<String,String> player_task_list = new QuickHash();

    public void DistributeTasks() {

        Map<String,String> tasklist_perplayer = new QuickHash();

        List<UUID> PlayersInGame = new ArrayList<>();
        PlayersInGame.addAll(AmongKryeitors.crewmates);
        PlayersInGame.addAll(AmongKryeitors.impostors);
        
        for (UUID element : PlayersInGame) {
            player_task_list.put(Bukkit.getPlayer(element).getName(),GenerateTaskSet(primarytask,14,9));
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
        
        String[] split_tasks = tasklist.split(".");
        List<String> final_tasks = new ArrayList<>();
        
        for(String element : split_tasks) {
            final_tasks.add(element);
        }
        
        return final_tasks;
        
    }

    public String GenerateTaskSet(Map tasks, int task_number, int string_length) {

        Random random = new Random();
        Set key_set = tasks.keySet();
        String task_set = "";

        while (task_set.length() < task_number*string_length) {

            String next_task = (String) key_set.toArray()[random.nextInt(key_set.size())];
            if(!task_set.contains(next_task)) {
                task_set.concat(next_task);
            }
            if(next_task.contains("Data")) {
                task_set.concat("AdData...");
            } else if (next_task.contains("StGas")) {
                task_set.concat("UpGas....StGas....LoGas....");
            }

        }

        return task_set;

    }

    public boolean EraseTaskFromPlayer(Player player,String task) {
        if(player_task_list.get(player.getName()).contains(task)) {
            Collection<String> separated_tasks = SeparateTasks(player_task_list.get(player.getName()));
            int index = 0;
            for(String element : separated_tasks) {
                if(element.contains(task)) {
                    separated_tasks.remove(index);
                }
                index++;
            }
            String final_string = ReAssembleTasks(separated_tasks);
            player_task_list.remove(player);
            player_task_list.put(player.getName(), final_string);
            return true;
        } else {
            return false;
        }
    }

    public String ReAssembleTasks(Collection<String> task_list) {

        String assembled_tasks = "";

        for(String element : task_list) {
            assembled_tasks.concat(element);
        }
        return assembled_tasks;

    }

}
