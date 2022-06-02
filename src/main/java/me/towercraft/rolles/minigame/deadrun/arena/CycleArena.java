package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Spectator;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.util.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
        }, 20);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!arena.getSpector(event.getPlayer()) && arena.getState().equals(GameState.PLAYING)) {
            Location location = event.getPlayer().getLocation();
            Block block = location.getBlock().getRelative(BlockFace.DOWN);
            if (config.getMaterials().contains(block.getType())) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    block.setType(Material.AIR);
                    block.getRelative(BlockFace.DOWN).setType(Material.AIR);
                }, 8);
            }
        }
    }

    @EventHandler
    public void onLoose(PlayerMoveEvent event) {
        Location location = event.getPlayer().getLocation();
        if (location.getY() < config.getLOOSE_ZONE()) {
            arena.setCounter(arena.getCounter() - 1);
            arena.setSpectator(event.getPlayer(), true);
            //hidePlayer(event.getPlayer());
            Sender.broadcastMessage(config.getPLAYER_LOOSE_MESSAGE().replace("<name>", event.getPlayer().getName()));
            Spectator.set(plugin, arena, event.getPlayer(), config.getARENA_SPAWN());
        }
        if (arena.getCounter() == 1 && arena.getState() == GameState.PLAYING) {
            Player player = arena.getNotSpectatorList().get(0);
            Sender.broadcastMessage(config.getPLAYER_WIN_MESSAGE().replace("<name>", player.getName()));
            arena.setState(GameState.RESTARTING);
            //Timer.start(plugin, config);
            //Timer.kickAll(plugin);
        }
    }
}
