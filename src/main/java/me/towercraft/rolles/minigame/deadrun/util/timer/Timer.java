package me.towercraft.rolles.minigame.deadrun.util.timer;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.CycleArena;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class Timer {
    public static void start(DeadRun plugin, YMLConfig config) {
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, () -> {
            for (int counter = 10; counter > 0; counter--) {
                Sender.titleForListPlayers(String.valueOf(counter));
                if (counter == 10) {
                    Sender.broadcastMessage(config.getSTART_MESSAGE().replace("<time>", String.valueOf(counter)));
                }
                if (counter <= 5) {
                    Sender.broadcastMessage(config.getSTART_MESSAGE().replace("<time>", String.valueOf(counter)));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void kickAll(DeadRun plugin) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            players.forEach(player -> player.kickPlayer(""));
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
        }, 200);
    }

    public static void startGame(DeadRun plugin, YMLConfig config, StateArena arena) {
        start(plugin, config);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            new CycleArena(arena, config, plugin).start();
            arena.setState(GameState.PLAYING);
        }, 200);
    }
}
