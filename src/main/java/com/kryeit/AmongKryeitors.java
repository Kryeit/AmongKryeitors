package com.kryeit;

import com.kryeit.command.StartGame;
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

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerComamnds() {
        getCommand("startgame").setExecutor(new StartGame());
    }
}
