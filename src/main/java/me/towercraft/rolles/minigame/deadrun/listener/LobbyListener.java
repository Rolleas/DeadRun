package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.arena.lobby.Lobby;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class LobbyListener implements Listener {

    private final YMLConfig config;
    private final StateArena arena;
    private final DeadRun plugin;
    private final Lobby lobby;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (arena.getState() == GameState.WAITING) {
            event.setJoinMessage("");
            lobby.fill(event.getPlayer());
        } else {
            event.getPlayer().kickPlayer("");
        }
    }

    @EventHandler void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        lobby.remove(event.getPlayer());
    }
}
