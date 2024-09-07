package com.kryeit;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.nucleoid.plasmid.game.GameType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AmongKryeitors implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        registerCommands();
        registerEvents();

        registerGame();
    }

    public void registerCommands() {}

    public void registerEvents() {
        // OnDisable
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            // Something when the mod is disabling
        });
    }

    public void registerGame() {
        GameType.register(
                new Identifier("amongkryeitors", "among_us"),
                AmongUsGameConfig.CODEC,
                AmongUsGame::open
        );
    }


}

