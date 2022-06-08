package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Spectator;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Map;

@AllArgsConstructor
public class ArenaListener implements Listener {

    private YMLConfig config;
    private DeadRun plugin;

    @EventHandler
    public void removeBlock(BlockRemoveEvent event) {
        Location location = event.getPlayer().getLocation();
        Block block = location.getBlock().getRelative(BlockFace.DOWN);
        if (config.getMaterials().contains(block.getType())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                block.setType(Material.AIR);
                block.getRelative(BlockFace.DOWN).setType(Material.AIR);
            }, 8);
        }
    }

    @EventHandler
    public void loosePlayer(LoosePlayerEvent event) {
        if (!event.getSpector()) {
            if(event.getPlayer().getLocation().getY() <= config.getLOOSE_ZONE()) {
                DeadRun.spectator.set(event.getPlayer());
                Sender.messageForListPlayers(config.getPLAYER_LOOSE_MESSAGE().replace("<name>", event.getPlayer().getName()));
                Teleport.go(event.getPlayer(), config.getARENA_SPAWN());
            }
        } else {
            Teleport.go(event.getPlayer(), config.getARENA_SPAWN());
        }
    }

    @EventHandler
    public void winPlayer(PlayerWinEvent event) {
        Sender.messageForListPlayers(config.getPLAYER_WIN_MESSAGE().replace("<name>", event.getPlayer().getName()));
        DeadRun.arena.setState(GameState.RESTARTING);
    }

    @EventHandler
    public void stopArena(GameRestartEvent event) {
        String message = config.getWIN_PLACES()
                .replace("<name1>", DeadRun.arena.getPlaces().get(1).getName())
                .replace("<name2>", DeadRun.arena.getPlaces().get(2).getName())
                .replace("<name3>", DeadRun.arena.getPlaces().get(3).getName());
        Arrays.stream(message.split(",")).forEach(Sender::messageForListPlayers);
        Timer.start(plugin, config);
        Timer.kickAll(plugin);
    }
}
