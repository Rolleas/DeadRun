package me.towercraft.rolles.minigame.deadrun.arena.lobby;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import me.towercraft.rolles.minigame.deadrun.notification.Message;
import me.towercraft.rolles.minigame.deadrun.notification.Sender;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import me.towercraft.rolles.minigame.deadrun.util.timer.Timer;
import org.bukkit.entity.Player;
import java.util.Objects;

@AllArgsConstructor
public class Lobby {
    private final StateArena arena;
    private final YMLConfig config;
    private final DeadRun plugin;

    public void check() {
        if (Objects.equals(arena.getPlayers().size(), config.getMAX_PLAYERS())) {
            arena.setState(GameState.STARTING);
            Timer.startGame(plugin, config, arena);
        }
    }

    public void fill(Player player) {
        if (arena.getPlayers().size() < config.getMAX_PLAYERS()) {
            Teleport.go(player, config.getARENA_SPAWN());
            arena.addPlayer(player, false);
            new Message().playerJoin(config, arena, player);
        }
        check();
    }

    public void remove(Player player) {
        if (arena.getState() == GameState.WAITING) {
            Sender.broadcastMessage(config.getPLAYER_LEAVE_MESSAGE().replace("<name>", player.getName()));
        }
        if (arena.getState() == GameState.PLAYING) {
            Sender.broadcastMessage(config.getPLAYER_LOOSE_MESSAGE().replace("<name>", player.getName()));
        }
    }
}
