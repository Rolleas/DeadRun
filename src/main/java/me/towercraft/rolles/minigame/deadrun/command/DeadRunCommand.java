package me.towercraft.rolles.minigame.deadrun.command;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.CreateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DeadRunCommand extends CommandAbstract{

    private CreateArena createArena;
    private final YMLConfig config;
    private final DeadRun plugin;

    public DeadRunCommand(DeadRun plugin, YMLConfig config) {
        super("dr", plugin);
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "/dr create <name> - " + ChatColor.GOLD + "создание арены");
            Bukkit.broadcastMessage(ChatColor.GREEN + "/dr spawn - " + ChatColor.GOLD + "установка спавна на арене");
            return;
        }

        if (Objects.equals(args[0], "create")) {
            createArena = new CreateArena();
            createArena.setArenaName(true);
            Bukkit.broadcastMessage(ChatColor.GREEN + "Арена " + ChatColor.GOLD + args[1] + " создана");
            return;
        }

        if (Objects.equals(args[0], "check")) {
            if (createArena == null) {
                Bukkit.broadcastMessage(ChatColor.RED + "Для начала создайте арену");
            } else if (createArena.getArenaSpawn()) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Арена настроена!");
                config.set("arena.complete", true);
            } else {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Арена спавн: " + ChatColor.GREEN + createArena.getArenaSpawn());
            }
            return;
        }

        if(Objects.equals(args[0], "spawn")) {
            if (createArena == null) {
                Bukkit.broadcastMessage(ChatColor.RED + "Для начала создайте арену");
            } else {
                Player player = (Player) sender;
                Location playerLocation = player.getLocation();
                String location = String.format("%1s, %2s, %3s, %4s, %5s", playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), playerLocation.getPitch(), playerLocation.getYaw());
                config.set("arena.arena-spawn", location);
                createArena.setArenaSpawn(true);
                Bukkit.broadcastMessage(ChatColor.GREEN + "Спавн арены " + ChatColor.GOLD + location +" установлен");
            }
        }
    }
}
