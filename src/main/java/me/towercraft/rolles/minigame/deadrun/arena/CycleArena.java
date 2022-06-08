package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.event.BlockRemoveEvent;
import me.towercraft.rolles.minigame.deadrun.event.GameRestartEvent;
import me.towercraft.rolles.minigame.deadrun.event.LoosePlayerEvent;
import me.towercraft.rolles.minigame.deadrun.event.PlayerWinEvent;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

@AllArgsConstructor
public class CycleArena implements Listener {

    private final StateArena arena;
    private final YMLConfig config;
    private final DeadRun plugin;

    public void start() {
        Teleport.TeleportAllPlayers(config.getARENA_SPAWN());
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, () -> {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }, 50);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!DeadRun.spectator.getSpector(event.getPlayer()) && arena.getState().equals(GameState.PLAYING)) {
            Bukkit.getPluginManager().callEvent(new BlockRemoveEvent(event.getPlayer()));
        }
    }

    @EventHandler
    public void onLoose(PlayerMoveEvent event) {
        if (arena.getState().equals(GameState.PLAYING)) {
            if (event.getPlayer().getLocation().getY() < config.getLOOSE_ZONE()) {
                if (DeadRun.spectator.getNotSpectatorList().size() <= 3) {
                    arena.addPlayer( event.getPlayer(), DeadRun.spectator.getNotSpectatorList().size());
                }
                Bukkit.getPluginManager().callEvent(new LoosePlayerEvent(event.getPlayer(), DeadRun.ghostFactory.isGhost(event.getPlayer())));
            }
            if (DeadRun.spectator.getNotSpectatorList().size() == 1) {
                Player player = DeadRun.spectator.getNotSpectatorList().get(0);
                arena.addPlayer(player, DeadRun.spectator.getNotSpectatorList().size());
                Bukkit.getPluginManager().callEvent(new PlayerWinEvent(player));
                Bukkit.getPluginManager().callEvent(new GameRestartEvent());
            }

        }
    }
}
