package me.towercraft.rolles.minigame.deadrun.listener;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.event.BlockRemoveEvent;
import me.towercraft.rolles.minigame.deadrun.event.GameRestartEvent;
import me.towercraft.rolles.minigame.deadrun.event.LoosePlayerEvent;
import me.towercraft.rolles.minigame.deadrun.event.PlayerWinEvent;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.util.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.*;

public class ArenaListener implements Listener {

    private final HashMap<Player, Location> playerLocation = new HashMap<>();

    @EventHandler
    public void removeBlock(BlockRemoveEvent event) {
        Location location = event.getPlayer().getLocation();
        Block block = location.getBlock().getRelative(BlockFace.DOWN);
        if (DeadRun.config.getMaterials().contains(block.getType())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(DeadRun.plugin, () -> {
                block.setType(Material.AIR);
                block.getRelative(BlockFace.DOWN).setType(Material.AIR);
            }, 8);
        }
    }

    @EventHandler
    public void loosePlayer(LoosePlayerEvent event) {
        if (!event.getSpector()) {
            if (event.getPlayer().getLocation().getY() <= DeadRun.config.getLOOSE_ZONE()) {
                DeadRun.spectator.set(event.getPlayer());
                Sender.messageForListPlayers(DeadRun.config.getPLAYER_LOOSE_MESSAGE().replace("<name>", event.getPlayer().getName()));
                Teleport.go(event.getPlayer(), DeadRun.config.getARENA_SPAWN());
            }
        } else {
            Teleport.go(event.getPlayer(), DeadRun.config.getARENA_SPAWN());
        }
    }

    @EventHandler
    public void winPlayer(PlayerWinEvent event) {
        Sender.messageForListPlayers(DeadRun.config.getPLAYER_WIN_MESSAGE().replace("<name>", event.getPlayer().getName()));
        DeadRun.arena.setState(GameState.RESTARTING);
    }

    @EventHandler
    public void stopArena(GameRestartEvent event) {
        String message = DeadRun.config.getWIN_PLACES()
                .replace("<name1>", DeadRun.arena.getPlaces().get(1).getName())
                .replace("<name2>", DeadRun.arena.getPlaces().get(2).getName())
                .replace("<name3>", DeadRun.arena.getPlaces().get(3).getName());
        Arrays.stream(message.split(",")).forEach(Sender::messageForListPlayers);
        Timer.start(DeadRun.plugin, DeadRun.config);
        Timer.kickAll(DeadRun.plugin);
    }
}
