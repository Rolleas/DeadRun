package me.towercraft.rolles.minigame.deadrun.arena.lobby;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.CycleArena;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.util.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.sql.Time;
import java.util.Objects;

@AllArgsConstructor
public class Lobby {
    private final StateArena arena;
    private final YMLConfig config;
    private final DeadRun plugin;

    public void fill(Player player) {
        if (arena.getCounter() < config.getMAX_PLAYERS() && arena.getState() == GameState.WAITING) {
            Teleport.go(player, config.getARENA_SPAWN());
            arena.setCounter(arena.getCounter() + 1);
            arena.addPlayer(player, false);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            Sender.broadcastMessage(config.getPLAYER_JOIN_MESSAGE()
                    .replace("<current>", String.valueOf(arena.getCounter()))
                    .replace("<max>", String.valueOf(config.getMAX_PLAYERS()))
                    .replace("<name>", String.valueOf(player.getName())));
        } else {
            player.kickPlayer("");
        }
        if (Objects.equals(arena.getCounter(), config.getMAX_PLAYERS())) {
            arena.setState(GameState.STARTING);
            Timer.start(plugin, config);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                new CycleArena(arena, config, plugin).start();
                arena.setState(GameState.PLAYING);
            }, 200);
        }

    }

    public void remove(Player player) {
        arena.setCounter(arena.getCounter() - 1);
        if (arena.getState() == GameState.WAITING) {
            Sender.broadcastMessage(config.getPLAYER_LEAVE_MESSAGE().replace("<name>", player.getName()));
        }
        if (arena.getState() == GameState.PLAYING) {
            Sender.broadcastMessage(config.getPLAYER_LOOSE_MESSAGE().replace("<name>", player.getName()));
        }
    }
}
