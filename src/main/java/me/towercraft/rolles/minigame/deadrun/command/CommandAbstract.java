package me.towercraft.rolles.minigame.deadrun.command;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CommandAbstract implements CommandExecutor {

    public CommandAbstract(String command, DeadRun plugin) {
        plugin.getCommand(command).setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, label, args);
        return true;
    }
}
