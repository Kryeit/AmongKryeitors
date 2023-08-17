package com.kryeit;

import com.kryeit.command.StartGame;
import com.kryeit.listener.OnPlayerAttack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AmongKryeitors extends JavaPlugin {

    public static List<UUID> impostors = new ArrayList<>();
    public static List<UUID> crewmates = new ArrayList<>();

    @Override
    public void onEnable() {
        registerComamnds();
        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerComamnds() {
        getCommand("startgame").setExecutor(new StartGame());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new OnPlayerAttack(), this);
    }
}
