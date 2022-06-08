package me.towercraft.rolles.minigame.deadrun.command;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DeadRunCommand extends CommandAbstract {

    private final YMLConfig config;

    public DeadRunCommand(DeadRun plugin, YMLConfig config) {
        super("dr", plugin);
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (Objects.equals(args[0], "spawn")) {
            if (sender.isOp()) {
                if (!config.getARENA_COMPLETE()) {
                    Player player = (Player) sender;
                    Location playerLocation = player.getLocation();
                    String location = String.format("%1s, %2s, %3s, %4s, %5s", playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), playerLocation.getPitch(), playerLocation.getYaw());
                    config.set("arena.arena-spawn", location);
                    config.set("arena.complete", true);
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Спавн арены " + ChatColor.GOLD + location + " установлен");
                } else {
                    Sender.broadcastMessage("&7Спавн уже установлен");
                }
            }
        }
    }
}
