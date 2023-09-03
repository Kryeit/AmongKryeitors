package com.kryeit.command;

import com.kryeit.global_variable_managers.ResetGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reset implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        ResetGame resetGame = new ResetGame();
        resetGame.resetGame();

        return true;
    }
}
