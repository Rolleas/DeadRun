package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Spectator;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

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
        Location location = event.getPlayer().getLocation();
        Block block = location.getBlock().getRelative(BlockFace.DOWN);
        System.out.println(location.getBlock().getRelative(BlockFace.DOWN).getType());
        if(config.getMaterials().contains(block.getType())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                block.setType(Material.AIR);
                block.getRelative(BlockFace.DOWN).setType(Material.AIR);
            }, 8);
        }
    }

    @EventHandler
    public void onLoose(PlayerMoveEvent event) {
        Location location = event.getPlayer().getLocation();
        arena.setCounter(arena.getCounter() - 1);
        if (location.getY() < config.getLOOSE_ZONE()) {
            Spectator.set(event.getPlayer(), config.getARENA_SPAWN());
        }
        if (arena.getCounter() == 1) {
            arena.setState(GameState.RESTARTING);
        }
    }
}
