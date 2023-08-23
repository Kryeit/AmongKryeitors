package com.kryeit;

import com.kryeit.command.StartGame;
import com.kryeit.listener.OnPlayerAttack;
import com.kryeit.listener.onPlayerDeath;
import com.kryeit.listener.onTaskDone;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AmongKryeitors extends JavaPlugin {

    public static List<UUID> impostors = new ArrayList<>();
    public static List<UUID> crewmates = new ArrayList<>();
    private static AmongKryeitors instance;

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
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnPlayerAttack(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onTaskDone(), this);
    }
    public static AmongKryeitors getInstance() {return instance;}

}

