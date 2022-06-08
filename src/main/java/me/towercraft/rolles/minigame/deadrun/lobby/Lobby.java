package me.towercraft.rolles.minigame.deadrun.lobby;

import de.dytanic.cloudnet.ext.bridge.bukkit.BukkitCloudNetHelper;
import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
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
        if (Objects.equals(DeadRun.spectator.getPlayers().size(), config.getMAX_PLAYERS())) {
            arena.setState(GameState.STARTING);
            //BukkitCloudNetHelper.changeToIngame(true);
            Timer.startGame(plugin, config, arena);
        }
    }

    public void fill(Player player) {
        if (DeadRun.spectator.getPlayers().size() < config.getMAX_PLAYERS()) {
            Teleport.go(player, config.getARENA_SPAWN());
            DeadRun.spectator.addPlayer(player, false);
            Sender.messageForListPlayers(config.getPLAYER_JOIN_MESSAGE()
                    .replace("<current>", String.valueOf(DeadRun.spectator.getPlayers().size()))
                    .replace("<max>", String.valueOf(config.getMAX_PLAYERS()))
                    .replace("<name>", String.valueOf(player.getName())));
        }
        check();
    }

    public void remove(Player player) {
        if (arena.getState() == GameState.WAITING) {
            Sender.messageForListPlayers(config.getPLAYER_LEAVE_MESSAGE().replace("<name>", player.getName()));
        }
        if (arena.getState() == GameState.PLAYING) {
            DeadRun.spectator.removePlayer(player);
        }
    }
}
