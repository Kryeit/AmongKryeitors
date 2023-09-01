package com.kryeit.command;

import com.kryeit.events.Voting;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Vote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Voting voting = new Voting();
        voting.StartVote();

        return false;
    }

}
