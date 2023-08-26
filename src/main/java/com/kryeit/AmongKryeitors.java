package com.kryeit;

import com.kryeit.command.Sabotage;
import com.kryeit.command.StartGame;
import com.kryeit.listener.GUIPreventCLicking;
import com.kryeit.listener.OnPlayerAttack;
import com.kryeit.listener.onPlayerDeath;
import com.kryeit.listener.onTaskDone;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AmongKryeitors extends JavaPlugin {

    public static List<UUID> impostors = new ArrayList<>();
    public static List<UUID> crewmates = new ArrayList<>();
    private static AmongKryeitors instance;

    public static List<Player> currentDead = new ArrayList<>();

    public static List<String> bodies = new ArrayList<>();

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
        getServer().getPluginManager().registerEvents(new GUIPreventCLicking(), this);
    }
    public static AmongKryeitors getInstance() {return instance;}

}

