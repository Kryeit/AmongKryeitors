package com.kryeit;

import com.kryeit.command.Sabotage;
import com.kryeit.command.StartGame;
import com.kryeit.events.onReactorSabotage;
import com.kryeit.listener.*;
import com.kryeit.miscellanous.VentCheck;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class AmongKryeitors extends JavaPlugin {

    public static List<UUID> impostors = new ArrayList<>();
    public static List<UUID> crewmates = new ArrayList<>();
    public static List<Player> currentDead = new ArrayList<>();
    public static List<String> corpses = new ArrayList<>();

    public static World OVERWORLD = Bukkit.getWorld("world");
    private static AmongKryeitors instance;

    public static String current_pin;
    public static String current_pin2;

    public static Boolean is_otwo_sabotaged = false;

    public static Boolean is_reactor_sabotaged = false;
    public static Map<Player, Integer> kill_cooldowns = Map.of();
    public static Map<Player, Integer> sabotage_cooldowns = Map.of();

    public static Player engineer;
    public static Player shapeshifter;


    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        getCommand("startgame").setExecutor(new StartGame());
        getCommand("sabotage").setExecutor(new Sabotage());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnPlayerAttack(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onTaskDone(), this);
        getServer().getPluginManager().registerEvents(new SabotageGUI(), this);
        getServer().getPluginManager().registerEvents(new LightsGUI(), this);
        getServer().getPluginManager().registerEvents(new OxygenAdminGUI(), this);
        getServer().getPluginManager().registerEvents(new OxygenO2GUI(), this);
        getServer().getPluginManager().registerEvents(new onReactorSabotage(), this);
        getServer().getPluginManager().registerEvents(new VentCheck(), this);
        getServer().getPluginManager().registerEvents(new InventoryGUIClick(), this);
    }

    public static AmongKryeitors getInstance() {
        return instance;
    }

    public static class QuickHash extends HashMap<String, String> {
    }


}

