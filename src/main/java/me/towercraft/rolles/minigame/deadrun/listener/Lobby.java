package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.CycleArena;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.util.teleport.TeleportAbstract;
import me.towercraft.rolles.minigame.deadrun.util.timer.Timer;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

@AllArgsConstructor
public class Lobby implements Listener {

    private final YMLConfig config;
    private final StateArena arena;
    private final DeadRun plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        if (arena.getState() == GameState.WAITING) {
            if (arena.getNumbersOfPlayers() < config.getMAX_PLAYERS()) {
                TeleportAbstract.go(event.getPlayer(), config.getARENA_SPAWN());
                arena.addPlayer(event.getPlayer());
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        config.getPLAYER_JOIN_MESSAGE()
                                .replace("<name>", event.getPlayer().getName())
                                .replace("<n>", String.valueOf(arena.getNumbersOfPlayers()))
                                .replace("<s>", String.valueOf(config.getMAX_PLAYERS()))
                ));
            }

            if (arena.getPlayers().size() == config.getMAX_PLAYERS()) {
                arena.setState(GameState.STARTING);
                new Timer(plugin, config).start(arena.getPlayers());
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    arena.setState(GameState.PLAYING);
                    new CycleArena(arena, config, plugin).start();
                }, 200);

            }
        }
        else {
            event.getPlayer().kickPlayer("");
        }
    }

    @EventHandler void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        List<Player> players = arena.getPlayers();
        players.remove(event.getPlayer());
        arena.setPlayers(players);
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                config.getPLAYER_LEAVE_MESSAGE()
                        .replace("<name>", event.getPlayer().getName())
                        .replace("<n>", String.valueOf(players.size()))
                        .replace("<s>", String.valueOf(config.getMAX_PLAYERS()))
        ));
    }
}
