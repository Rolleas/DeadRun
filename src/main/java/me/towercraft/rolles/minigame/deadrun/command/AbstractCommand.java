package me.towercraft.rolles.minigame.deadrun.command;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

public abstract class AbstractCommand implements CommandExecutor {
    public AbstractCommand(String command) {
        PluginCommand pluginCommand = DeadRun.getInstance().getCommand(command);
        if(pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
    }

    public abstract void execute(CommandSender commandSender, String label, String [] args);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        execute(commandSender, label, args);
        return true;
    }
}
